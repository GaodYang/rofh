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
		if("INSTALL".equals(rofhBean.getAction())){
			product.setProductAction(WebServiceConstant.ProductAction.装机);
		}else{
			product.setProductAction(WebServiceConstant.ProductAction.移机);
			
		}
		
		String xml = "";
		try {
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
				
			xml = finishMsgBuilder.buildFinishMsg(rofhBean);
			finishRmTaskAsynService.sendXmlToPboss(rofhBean.getOrder().getCrmTaskId(),xml,rofhBean.getRegionId());
			
		} catch (Exception e) {
			
			logger.error("当前宽带账户："+rofhBean.getProduct().getAccountName()+",装机异常!",e);
			finishRmTaskAsynService.sendErrorXmlToPboss(rofhBean.getOrder().getCrmTaskId(), "port.used", e.getMessage(),rofhBean.getRegionId());
			return;
		}
		
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
	private void addRofhCustomer(RofhBean rofhBean) {
		
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
	private void addOrder(RofhBean rofhBean) {
		
		RofhOrder order = rofhBean.getOrder();
		order.setRelGroupCustomerCuid(rofhBean.getCustomer().getCuid());
		String cuid = rofhBean.getProduct().getRelatedOrderCuid();
		if(StringUtils.isNotBlank(cuid)){
			RofhOrder rOrder = orderDao.findOne(cuid);
			if(rOrder != null){
				if(order.getViceId() == null || order.getViceId().equals(rOrder.getViceId())){
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
	private void updateProduct(RofhBean rofhBean) {
		
		RofhProduct rofhProduct = rofhBean.getProduct();

		rofhProduct.setRelatedOrderCuid(rofhBean.getOrder().getCuid());
		rofhProduct.setProductStatus(WebServiceConstant.ProductStatus.工程);
		rofhProduct.setBusinessState(WebServiceConstant.BusinessState.未归档);
		rofhProduct.setCreateTime(new Date());
			
		attempProductDao.save((RofhProductAttemp)rofhProduct);
	}
	
}
