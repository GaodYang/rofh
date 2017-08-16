package com.boco.rofh.webservice.task;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhOrder;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.entity.RofhProductSf;
import com.boco.rofh.webservice.pojo.ConfigTaskReq;

@Service
public class RemoveTask extends AbstractResourceTask{

	private static final Logger logger = LoggerFactory
			.getLogger(RemoveTask.class);
	
	
	@Override
	protected void doBusiness(RofhBean rofhBean) {
		
		this.copySf2AttempProduct(rofhBean);
		//进行回单
		//getPonWayBO().getPonWayByProductCuid(productCuid);
		String xml = finishMsgBuilder.buildFinishMsg(rofhBean);
		
		finishRmTaskAsynService.sendXmlToPboss(rofhBean.getOrder().getCrmTaskId(),xml,rofhBean.getRegionId());
	}
	
	private void execute(RofhBean rofhBean){
		
		String accountName = rofhBean.getProduct().getAccountName();
		
		if(isExistId(accountName)){
			
			finishRmTaskAsynService.sendErrorXmlToPboss(rofhBean.getOrder().getCrmTaskId(), "port.used", "宽带账号："+ accountName +",正在处理中！",rofhBean.getRegionId());
			return ;
		}
		
		List<RofhProductSf> productSfList = sfProductDao.findByProductCode(rofhBean.getProduct().getProductCode());
		if(productSfList == null || productSfList.size() == 0){
			
			logger.error("用户账号：" + rofhBean.getProduct().getProductCode() + "，不存在！");
			finishRmTaskAsynService.sendErrorXmlToPboss(rofhBean.getOrder().getCrmTaskId(), "port.used", "用户账号：" + rofhBean.getProduct().getProductCode() + "，不存在！",rofhBean.getRegionId());
			return;
		}
		
		RofhProductSf productSf = productSfList.get(0);
		for(RofhProductSf product : productSfList){
			
			if(product.getAccountName().equals(accountName)){
				productSf = product;
			}
		}
		
		rofhBean.setProduct(productSf);
		
		try{	
			this.doBusiness(rofhBean);
		}catch(Exception e){
			
			logger.error("Task error !",e);
		}finally{
			removeId(accountName);
		}
		
	}
	
	@Override
	public void doTask(ConfigTaskReq req,String regionId){
		
		RofhBean rofhBean = beanWapper.Wapper2RofhBean(req,regionId) ;
		RofhProductAttemp attempProduct = (RofhProductAttemp) rofhBean.getProduct();
		//验证
		if(!StringUtils.isEmpty(attempProduct.getCuid())){
			logger.error("用户宽带号：" + attempProduct.getAccountName() + ",正在开通中！");
			finishRmTaskAsynService.sendErrorXmlToPboss(rofhBean.getOrder().getCrmTaskId(), "port.used", "宽带账号："+ attempProduct.getAccountName() +",正在开通中！",regionId);
			return ;
		}
		
		this.execute(rofhBean);
	}
	
	private void copySf2AttempProduct(RofhBean rofhBean){
		
		RofhProductSf sfProduct = (RofhProductSf) rofhBean.getProduct();
		RofhProductAttemp attempProduct = new RofhProductAttemp();
		BeanUtils.copyProperties(sfProduct, attempProduct);
		attempProduct.setCuid(null);
		
		RofhOrder order = rofhBean.getOrder();
		order.setRelGroupCustomerCuid(attempProduct.getRelatedCustomerCuid());
		orderDao.save(order);
		attempProduct.setRelatedOrderCuid(order.getCuid());
		attempProduct.setCreateTime(new Date());
		attempProduct.setFinishTime(null);
		attempProduct.setProductAction(WebServiceConstant.ProductAction.拆机);
		attempProductDao.save(attempProduct);
		rofhBean.setProduct(attempProduct);
		
	}
}
