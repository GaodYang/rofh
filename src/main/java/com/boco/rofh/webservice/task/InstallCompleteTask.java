package com.boco.rofh.webservice.task;

import org.springframework.stereotype.Service;

import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.entity.RofhProductSf;

@Service
public class InstallCompleteTask extends AbstractCompleteTask{

	@Override
	protected void doBusiness(RofhBean rofhBean) {
		
		RofhProductAttemp attempProduct = (RofhProductAttemp)rofhBean.getProduct();
		RofhProductSf sfProduct = this.moveAttemp2SfProduct(attempProduct);
		
		this.installComplete(sfProduct);
	}

}
