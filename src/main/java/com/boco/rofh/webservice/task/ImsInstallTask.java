package com.boco.rofh.webservice.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhProduct;

@Service
public class ImsInstallTask extends AbstractInstallTask{

	private static final Logger logger = LoggerFactory
			.getLogger(ImsInstallTask.class);
	
	public boolean occupyPort(RofhProduct rofhProduct){
	
		return true;
	}
	
	protected void createAttempPonWay(RofhProduct rofhProduct) {

	}
	
	@Override
	public void doBusiness(RofhBean rofhBean) {
		
		getBroadResource(rofhBean);
	}
	
	@Override
	protected void setProperties(RofhProduct product) {
		
		product.setAccessMode(WebServiceConstant.AccessMode.IMS);
		product.setProductType(WebServiceConstant.ProductType.IMS业务);
		product.setBusinessType(WebServiceConstant.BusinessType.IMS);
	}
	
	
}
