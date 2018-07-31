package com.boco.rofh.webservice.task;

import java.util.Date;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.dao.CpeDao;
import com.boco.rofh.dao.OnuDao;
import com.boco.rofh.dao.OrderDao;
import com.boco.rofh.dao.PonWayDao;
import com.boco.rofh.dao.ProductDao;
import com.boco.rofh.dao.PtpDao;
import com.boco.rofh.dao.WbsDao;
import com.boco.rofh.entity.AnOnu;
import com.boco.rofh.entity.AnOnuAttemp;
import com.boco.rofh.entity.PonWay;
import com.boco.rofh.entity.PonWayAttemp;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhOrder;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.entity.RofhProductHis;
import com.boco.rofh.entity.RofhProductSf;
import com.boco.rofh.utils.FinishMsgBuilder;
import com.boco.rofh.utils.RofhBeanWapper;
import com.boco.rofh.webservice.pojo.ConfigTaskReq;
import com.boco.rofh.webservice.service.FinishRmTaskAsynService;

/**
 * 工单受理
 * @author gaoyang
 *
 */
@Service
public abstract class AbstractResourceTask {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractResourceTask.class);
	
	private static LinkedList<String> IDList = new LinkedList<String>(); 
	
	@Autowired
	protected FinishRmTaskAsynService finishRmTaskAsynService;
	
	@Autowired
	protected ProductDao<RofhProductAttemp> attempProductDao;
	
	@Autowired
	protected ProductDao<RofhProductSf> sfProductDao;
	
	@Autowired
	protected ProductDao<RofhProductHis> hisProductDao;
	
	@Autowired
	protected PonWayDao<PonWayAttemp> attempPonWayDao;
	
	@Autowired
	protected PonWayDao<PonWay> ponWayDao;
	
	@Autowired
	protected PtpDao ptpDao;
	
	@Autowired
	protected OnuDao<AnOnuAttemp> attempOnuDao;
	
	@Autowired
	protected OnuDao<AnOnu> onuDao;
	
	@Autowired
	protected CpeDao cpeDao;
	
	@Autowired
	protected WbsDao wbsDao;
	
	@Autowired
	protected OrderDao orderDao;
	
	@Autowired
	protected RofhBeanWapper beanWapper;
	
	@Autowired
	protected FinishMsgBuilder finishMsgBuilder;
	
	
	protected void execute(RofhBean rofhBean){
		
		String accountName = rofhBean.getProduct().getAccountName();
		
		logger.info("try add " + accountName);
		if(isExistId(accountName)){
			
			finishRmTaskAsynService.sendErrorXmlToPboss(rofhBean.getOrder().getCrmTaskId(), "port.used", "宽带账号："+ accountName +",正在处理中！",rofhBean.getRegionId());
			return ;
		}
		try{	
			this.addOrder(rofhBean);
			this.doBusiness(rofhBean);
		}catch(Exception e){
			
			logger.error("Task error !",e);
			finishRmTaskAsynService.sendErrorXmlToPboss(rofhBean.getOrder().getCrmTaskId(), "port.used", e.getMessage(),rofhBean.getRegionId());
		}finally{
			removeId(accountName);
		}
		
	}
	
	protected abstract  void doBusiness(RofhBean rofhBean);
	
	public void doTask(ConfigTaskReq req,String regionId){
		
		RofhBean rofhBean = beanWapper.Wapper2RofhBean(req,regionId) ;
		
		this.execute(rofhBean);
	}

	
	synchronized public boolean isExistId(String id){
		
		if(IDList.contains(id)){
			
			return true;
		}
		
		IDList.add(id);
		logger.debug(id + " added");
		return false;
	}
	
	public void removeId(String id){
		
		logger.info("removing : " + id +"@" + IDList);
		IDList.remove(id);
		logger.info("removed : " + id);
	}
	
	/**
	 * 添加订单信息
	 * @param processBean
	 */
	protected void addOrder(RofhBean rofhBean) {
		
		RofhOrder order = rofhBean.getOrder();
	//	order.setRelGroupCustomerCuid(rofhBean.getCustomer().getCuid());
		RofhOrder sfOrder = orderDao.findFirstByCrmSheetNoAndCrmTaskId(order.getCrmSheetNo(),order.getCrmTaskId());
		if(sfOrder != null){
			
			order.setCreateTime(sfOrder.getCreateTime());
			order.setCuid(sfOrder.getCuid());
		}else{
			
			order.setCreateTime(new Date());
		}
		order.setLastModifyTime(new Date());
		orderDao.save(order);
	}
	

}
