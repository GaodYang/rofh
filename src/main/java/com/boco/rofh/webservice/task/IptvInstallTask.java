package com.boco.rofh.webservice.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.constant.WebServiceConstant.DataSource;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhProduct;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.entity.RofhProductSf;
import com.boco.rofh.exception.UserException;
import com.boco.rofh.utils.RofhUtil;

@Service
public class IptvInstallTask extends AbstractInstallTask{

	private static final Logger logger = LoggerFactory
			.getLogger(IptvInstallTask.class);
	
	public boolean occupyPort(RofhProduct rofhProduct){
	
		return true;
	}
	
	protected void createAttempPonWay(RofhProduct rofhProduct) {

	}
	
	@Override
	public void doBusiness(RofhBean rofhBean) {
		
		RofhProduct attempProduct = rofhBean.getProduct();
		
		if(attempProduct.getCuid() == null){
			
			String dataSource = DataSource.ATTEMP.name();
			//装机不是重发，可能是一起装，可能是加装
			//一起装
			RofhProduct product  ;
			List<RofhProductAttemp> attempList = attempProductDao.findByProductCodeAndAccount(attempProduct.getProductCode(), attempProduct.getAccountName());
			if(attempList != null){
				
				product = attempList.get(0);
			}else{
				//加装
				List<RofhProductSf> sfList = sfProductDao.findByProductCodeAndAccount(attempProduct.getProductCode(), attempProduct.getAccountName());
				if(sfList != null){
					
					dataSource = DataSource.SF.name();
					product = sfList.get(0);
				}else{
					
					//没有数据
					throw new UserException("没有宽带数据！");
				}
				
			}
			
			product.setStbMac(attempProduct.getStbMac());
			product.setSaleType(attempProduct.getSaleType());
			BeanUtils.copyProperties(product, attempProduct);
			attempProduct.setRelatedSheetCuid(product.getCuid());
			attempProduct.setAccessMode(WebServiceConstant.AccessMode.IPTV);
			attempProduct.setProductType(WebServiceConstant.ProductType.IPTV业务);
			attempProduct.setBusinessType(WebServiceConstant.BusinessType.IPTV);
			attempProduct.setRelatedOrderCuid("");
			attempProduct.setDataSource(dataSource);
			attempProduct.setCuid(null);
		}
		
		attempProduct.setAccountName(RofhUtil.getAccountPrefix(WebServiceConstant.BusinessType.IPTV) + attempProduct.getAccountName());
		
		rofhBean.getCustomer().setCuid(attempProduct.getRelatedCustomerCuid());
		//添加订单
		this.addOrder(rofhBean);
		//更新产品信息
		this.updateProduct(rofhBean);
		
		String xml = finishMsgBuilder.buildFinishMsg(rofhBean);
		finishRmTaskAsynService.sendXmlToPboss(rofhBean.getOrder().getCrmTaskId(),xml,rofhBean.getRegionId());
		
	}
	
	
}
