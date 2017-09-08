package com.boco.rofh.webservice.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhProduct;

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
		
		getBroadResource(rofhBean);
	}
	
	@Override
	protected void setProperties(RofhProduct product) {
		
		product.setAccessMode(WebServiceConstant.AccessMode.IPTV);
		product.setProductType(WebServiceConstant.ProductType.IPTV业务);
		product.setBusinessType(WebServiceConstant.BusinessType.IPTV);
	}
	
	
}
