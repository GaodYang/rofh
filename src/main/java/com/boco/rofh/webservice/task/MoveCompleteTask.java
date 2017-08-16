package com.boco.rofh.webservice.task;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.entity.RofhProductSf;

@Service
public class MoveCompleteTask extends AbstractCompleteTask{

	@Override
	protected void doBusiness(RofhBean rofhBean) {
		
		RofhProductAttemp attempProduct = (RofhProductAttemp) rofhBean.getProduct();
		List<RofhProductSf> sfProducts = sfProductDao.findByProductCode(attempProduct.getProductCode());
		
		for(RofhProductSf sfProduct : sfProducts){
			this.removeComplete(sfProduct);
			
			this.moveSf2HisProduct(sfProduct);
		}
		this.installComplete(moveAttemp2SfProduct(attempProduct));
		
	}
}
