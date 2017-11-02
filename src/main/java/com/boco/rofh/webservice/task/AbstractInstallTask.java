package com.boco.rofh.webservice.task;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.constant.WebServiceConstant.DataSource;
import com.boco.rofh.constant.WebServiceConstant.ProducrAction;
import com.boco.rofh.dao.AddressDao;
import com.boco.rofh.dao.CustomerDao;
import com.boco.rofh.entity.PonWayAttemp;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhCustomer;
import com.boco.rofh.entity.RofhProduct;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.entity.RofhProductSf;
import com.boco.rofh.exception.UserException;
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
			throw new UserException("宽带账号："+ product.getAccountName() +",不存在！");
		}
		
		if(StringUtils.isNotBlank(product.getProductAction()) && !rofhBean.getAction().equals(product.getProductAction())){
			
			throw new UserException("操作有误，当前产品状态为：" + ProducrAction.getName(product.getProductAction()) + "！");
		}

		// 添加客户信息
		this.addRofhCustomer(rofhBean);
		//添加订单
	//	this.addOrder(rofhBean);
		//新pon_way
		//如果有pon_way就不操作
		PonWayAttemp pw = attempPonWayDao.findByProductCuid(rofhBean.getProduct().getCuid());
		if(pw == null){
			
			this.createAttempPonWay(rofhBean.getProduct());
		}
		
		//更新产品信息
		this.updateProduct(rofhBean);
	
		if (WebServiceConstant.AccessMode.FTTH.equals(rofhBean.getProduct().getAccessMode())) {
			
			String str = activeNetService.netActivate(rofhBean, true);
			rofhBean.setActiveMsg(str);
			if (str == null){
				
				try {
					Thread.sleep(60*1000);
				} catch (InterruptedException e) {
					
					return ;
				}
				rofhBean.setActiveMsg("激活超时！");
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
	 *//*
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
	}*/
	
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
		rofhProduct.setProductAction(rofhBean.getAction());
		
		attempProductDao.save((RofhProductAttemp)rofhProduct);
	}
	
	/**
	 * 获取宽带资源信息
	 * @param attempProduct
	 */
	protected void getBroadResource(RofhBean rofhBean) {
		
		RofhProduct attempProduct = rofhBean.getProduct();
		
		if(attempProduct.getCuid() == null){
			
			String dataSource = DataSource.ATTEMP.name();
			//装机不是重发，可能是一起装，可能是加装
			//一起装
			RofhProduct product  ;
			
			List<RofhProductAttemp> attempList = attempProductDao.findByProductCodeAndAccount(rofhBean.getMainCode(), rofhBean.getMainNumber());
			if(attempList != null && attempList.size() > 0){
				
				product = attempList.get(0);
			}else{
				//加装
				List<RofhProductSf> sfList = sfProductDao.findByProductCodeAndAccount(rofhBean.getMainCode(), rofhBean.getMainNumber());
				if(sfList != null && sfList.size() > 0){
					
					dataSource = DataSource.SF.name();
					product = sfList.get(0);
				}else{
					
					//没有数据
					throw new UserException("没有宽带数据！");
				}
				
			}
			
			product.setSaleType(attempProduct.getSaleType());
			product.setAccountName(attempProduct.getAccountName());
			product.setProductCode(attempProduct.getProductCode());
			BeanUtils.copyProperties(product, attempProduct);
			attempProduct.setRelatedSheetCuid(product.getCuid());
			attempProduct.setRelatedOrderCuid("");
			attempProduct.setDataSource(dataSource);
			attempProduct.setEndTime(null);
			attempProduct.setFinishTime(null);
			attempProduct.setCuid(null);
			this.setProperties(attempProduct);
		}
		
		rofhBean.getCustomer().setCuid(attempProduct.getRelatedCustomerCuid());
		/*//添加订单
		this.addOrder(rofhBean);*/
		//更新产品信息
		this.updateProduct(rofhBean);
		
		String xml = finishMsgBuilder.buildFinishMsg(rofhBean);
		finishRmTaskAsynService.sendXmlToPboss(rofhBean.getOrder().getCrmTaskId(),xml,rofhBean.getRegionId());
		
		
	}
	
	protected void setProperties(RofhProduct product) {
		
	}
	
}
