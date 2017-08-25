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
import com.boco.rofh.entity.RofhProduct;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.entity.RofhProductSf;

@Service
public class RemoveTask extends AbstractResourceTask{

	private static final Logger logger = LoggerFactory
			.getLogger(RemoveTask.class);
	
	
	@Override
	protected void doBusiness(RofhBean rofhBean) {
		
		RofhProduct attempProduct = rofhBean.getProduct();
		if(StringUtils.isNotEmpty(attempProduct.getCuid())){
			
			if(!WebServiceConstant.ProductAction.拆机.equals(attempProduct.getProductAction())){
				
				logger.error("用户宽带号：" + attempProduct.getAccountName() + ",正在开通中！");
				finishRmTaskAsynService.sendErrorXmlToPboss(rofhBean.getOrder().getCrmTaskId(), "port.used", "宽带账号："+ attempProduct.getAccountName() +",正在开通中！",rofhBean.getRegionId());
				return ;
			}
		}else{
			
			List<RofhProductSf> productSfList = sfProductDao.findByProductCode(attempProduct.getProductCode());
			if(productSfList == null || productSfList.size() == 0){
				
				logger.error("用户账号：" + rofhBean.getProduct().getProductCode() + "，不存在！");
				finishRmTaskAsynService.sendErrorXmlToPboss(rofhBean.getOrder().getCrmTaskId(), "port.used", "用户账号：" + rofhBean.getProduct().getProductCode() + "，不存在！",rofhBean.getRegionId());
				return;
			}
			
			RofhProductSf productSf = productSfList.get(0);
			for(RofhProductSf product : productSfList){
				
				if(product.getAccountName().equals(attempProduct.getAccountName())){
					productSf = product;
				}
			}
			
			rofhBean.setProduct(productSf);
			
			this.copySf2AttempProduct(rofhBean);
		}
		//进行回单
		String xml = finishMsgBuilder.buildFinishMsg(rofhBean);
		
		finishRmTaskAsynService.sendXmlToPboss(rofhBean.getOrder().getCrmTaskId(),xml,rofhBean.getRegionId());
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
