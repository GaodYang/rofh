package com.boco.rofh.webservice.task;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.dao.AddressDao;
import com.boco.rofh.dao.CustomerDao;
import com.boco.rofh.entity.PonWayAttemp;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhCustomer;
import com.boco.rofh.entity.RofhOrder;
import com.boco.rofh.entity.RofhProduct;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.mapper.ResourceMapper;
import com.boco.rofh.mapper.RofhConfigMapper;
import com.boco.rofh.webservice.service.ActiveNetService;
import com.boco.rofh.webservice.service.FinishRmTaskAsynService;

@Service
public abstract class AbstractInstallTask extends AbstractResourceTask{

	private static final Logger logger = LoggerFactory.getLogger(AbstractInstallTask.class);
	
	@Autowired
	protected ResourceMapper resourceMapper;
	
	@Autowired
	protected AddressDao addressDao;
	
	@Autowired
	protected RofhConfigMapper configMapper;
	
	@Autowired
	protected FinishRmTaskAsynService finishRmTaskAsynService;
	
	@Autowired
	protected CustomerDao customerDao;
	
	@Autowired
	private ActiveNetService activeNetService;
	
	@Override
	public void doBusiness(RofhBean rofhBean) {
		
		RofhProduct product = rofhBean.getProduct();
		//验证
		if(StringUtils.isEmpty(product.getCuid())){
			
			logger.error("用户宽带号：" + product.getAccountName() + ",不存在！");
			finishRmTaskAsynService.sendErrorXmlToPboss(rofhBean.getOrder().getCrmTaskId(), "port.config.wait", "宽带账号："+ product.getAccountName() +",不存在！",rofhBean.getRegionId());
			return ;
		}

		// 添加客户信息
		this.addRofhCustomer(rofhBean);
		//添加订单
		this.addOrder(rofhBean);
		//新pon_way
		//如果有pon_way就不操作
		PonWayAttemp pw = attempPonWayDao.findByProductCuid(rofhBean.getProduct().getCuid());
		if(pw == null){
			
			this.createAttempPonWay(rofhBean.getProduct());
		}
		
		//更新产品信息
		this.updateProduct(rofhBean);
	
		if (WebServiceConstant.AccessMode.FTTH.equals(rofhBean.getProduct().getAccessMode())) {
			
			String str = activeNetService.netActivate(rofhBean, "1", "1");
			rofhBean.setActiveMsg(str);
			if (str == null){
				
				return ;
			}
		}
			
		String xml = finishMsgBuilder.buildFinishMsg(rofhBean);
		finishRmTaskAsynService.sendXmlToPboss(rofhBean.getOrder().getCrmTaskId(),xml,rofhBean.getRegionId());
			
		
	}
	/**
	 * 根据标准地址，进行端口的预占
	 * @param addrId
	 * @return
	 */

	public abstract boolean occupyPort(RofhProduct rofhProduct);
	
	protected abstract void createAttempPonWay(RofhProduct rofhProduct) ;
	
	/**
	 * 添加客户信息
	 * @param processBean
	 */
	protected void addRofhCustomer(RofhBean rofhBean) {
		
		RofhCustomer customer = rofhBean.getCustomer();
		RofhCustomer hisCustomer = customerDao.findByCustomerCode(customer.getCustomerCode());
				
		if (hisCustomer == null) {
			
			customer.setProvince(rofhBean.getProduct()
					.getBusinessProvinceD());
			customer.setCity(rofhBean.getProduct()
					.getBusinessCityD());
			customer.setCounty(rofhBean.getProduct()
					.getBusinessCountyD());
			customer.setCreateTime(new Date());
		}else{
			customer.setCuid(hisCustomer.getCuid());
		}
		customer.setLastModifyTime(new Date());
		customerDao.save(customer);
		
		rofhBean.getProduct().setRelatedCustomerCuid(customer.getCuid());
	}
	
	/**
	 * 添加订单信息
	 * @param processBean
	 */
	protected void addOrder(RofhBean rofhBean) {
		
		RofhOrder order = rofhBean.getOrder();
		order.setRelGroupCustomerCuid(rofhBean.getCustomer().getCuid());
		String cuid = rofhBean.getProduct().getRelatedOrderCuid();
		if(StringUtils.isNotBlank(cuid)){
			RofhOrder rOrder = orderDao.findOne(cuid);
			if(rOrder != null){
				if(order.getCrmSheetNo().equals(rOrder.getCrmSheetNo())){
					order.setCuid(cuid);
				}
			}
			
		}
		orderDao.save(order);
	}
	
	/**
	 * 更新产品信息
	 * @param processBean
	 */
	protected void updateProduct(RofhBean rofhBean) {
		
		RofhProduct rofhProduct = rofhBean.getProduct();

		rofhProduct.setRelatedOrderCuid(rofhBean.getOrder().getCuid());
		rofhProduct.setProductStatus(WebServiceConstant.ProductStatus.工程);
		rofhProduct.setBusinessState(WebServiceConstant.BusinessState.未归档);
		rofhProduct.setCreateTime(new Date());
			
		if("INSTALL".equals(rofhBean.getAction())){
			rofhProduct.setProductAction(WebServiceConstant.ProductAction.装机);
		}else{
			rofhProduct.setProductAction(WebServiceConstant.ProductAction.移机);
			
		}
		
		attempProductDao.save((RofhProductAttemp)rofhProduct);
	}
	
}
