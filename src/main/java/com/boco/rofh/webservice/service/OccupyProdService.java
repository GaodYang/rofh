package com.boco.rofh.webservice.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.dao.AddressDao;
import com.boco.rofh.dao.ProductDao;
import com.boco.rofh.entity.RofhFullAddress;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.exception.UserException;
import com.boco.rofh.utils.RofhBeanWapper;
import com.boco.rofh.webservice.pojo.GetResourceReq;
import com.boco.rofh.webservice.task.AbstractInstallTask;
import com.boco.rofh.webservice.task.ApInstallTask;
import com.boco.rofh.webservice.task.CttInstallTask;
import com.boco.rofh.webservice.task.FttbInstallTask;
import com.boco.rofh.webservice.task.FtthInstallTask;

/**
 * 4.6 业务资源预占接口
 * @author gaoyang
 *
 */
@Service
public class OccupyProdService extends BaseRofhWebService<GetResourceReq,Object>{

	private static final Logger logger = LoggerFactory
			.getLogger(OccupyProdService.class);
	
	private Map<String,AbstractInstallTask> taskMap = new HashMap<String,AbstractInstallTask>();
	
	@Autowired
	private ProductDao<RofhProductAttemp> productDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private FttbInstallTask fttbInstallTask;
	@Autowired
	private FtthInstallTask ftthInstallTask;
	@Autowired
	private ApInstallTask apInstallTask;
	@Autowired
	private CttInstallTask cttInstallTask;
	@Autowired
	private RofhBeanWapper beanWapper;
	
	private static LinkedList<String> IDList = new LinkedList<String>(); 
	
	@Override
	protected Object doBusiness(GetResourceReq getResourceReq) {
		
		String accountName = getResourceReq.getAccount();
		if(isExistId(accountName)) {
			
			throw new UserException(accountName + "正在处理中！");
		}
		try {
			
			//判断重复
			if(productDao.isExistByAccount(accountName) != null){
				
				//throw new UserException("用户" + rofhProduct.getAccountName() + "正在开通中！");
				return null;
			}
			//请求信息入库
			RofhProductAttemp rofhProduct = new RofhProductAttemp();
			rofhProduct.setAccountName(accountName);
			//设置地址信息
			RofhFullAddress fullAddress = addressDao.findByObjectId(new BigDecimal(getResourceReq.getStdAddrId()));
			if (fullAddress != null) {
				rofhProduct.setRelatedCoverageAddrCuid(fullAddress.getCuid());
				rofhProduct.setBusinessProvinceD(fullAddress.getProvince());
				rofhProduct.setBusinessCityD(fullAddress.getCity());
				rofhProduct.setBusinessCountyD(fullAddress.getCounty());
			}else{
				
				throw new UserException(getResourceReq.getStdAddrId() + "地址不可用！");
			}
			
			//进行预占
			String type = beanWapper.getProdSerMap().get(getResourceReq.getProdSrvCode());
			type = StringUtils.isEmpty(type) ? "FTTB" : type;
			
			boolean flag = taskMap.get(type).occupyPort(rofhProduct);
			if(!flag){
				
				throw new UserException(getResourceReq.getStdAddrId() + "没有空闲端口！");
			}
			
			//设置默认属性
			rofhProduct.setBusinessType(WebServiceConstant.BusinessType.宽带);
			rofhProduct.setRelatedBmclasstypeCuid("ROFH_PRODUCT");
			rofhProduct.setCellName(addressDao.getVillageById(rofhProduct.getRelatedCoverageAddrCuid()));
			rofhProduct.setDataSource("1");
			rofhProduct.setPreemptTime(new Date());
			
			productDao.save((RofhProductAttemp)rofhProduct);
			
			return null;
		}finally {
			
			removeId(accountName);
		}
	}
	
	public Map<String, AbstractInstallTask> getTaskMap() {
		return taskMap;
	}



	public void setTaskMap(Map<String, AbstractInstallTask> taskMap) {
		this.taskMap = taskMap;
	}
	
	@Transactional
	@Override
	public String doProcess(String xml, String regionId) {
		
		return super.doProcess(xml, regionId);
	}
	
	@PostConstruct
	private void init(){
		
		taskMap.put("FTTB", fttbInstallTask);
		taskMap.put("FTTH", ftthInstallTask);
		taskMap.put("WBS", apInstallTask);
		taskMap.put("CTT", cttInstallTask);
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
}
