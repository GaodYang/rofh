package com.boco.rofh.webservice.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.entity.RofhProductSf;
import com.boco.rofh.utils.TerminalServiceUtil;

@Service
public class RemoveCompleteTask extends AbstractCompleteTask{

	@Autowired
	TerminalServiceUtil terminalServiceUtil;
	
	@Override
	protected void doBusiness(RofhBean rofhBean) {
		
		RofhProductAttemp attempProduct = (RofhProductAttemp) rofhBean.getProduct();
		List<RofhProductSf> sfProducts = sfProductDao.findByProductCodeAndAccount(attempProduct.getProductCode(),attempProduct.getAccountName());
		
		for(RofhProductSf sfProduct : sfProducts){
			this.removeComplete(sfProduct);
			this.moveSf2HisProduct(sfProduct);
		}
		
		this.moveAttemp2HisProduct(attempProduct);
		
		//向终端系统发送消息
		if(WebServiceConstant.ProductType.IPTV业务.equals(attempProduct.getProductType())
				|| (WebServiceConstant.ProductType.宽带业务.equals(attempProduct.getProductType())
				&& WebServiceConstant.AccessMode.FTTH.equals(attempProduct.getAccessMode()))) {
			
			ThreadPoolManager.getInstance().addTask(TerminalServiceUtil.class, new Runnable() {
				@Override
				public void run() {
					
					terminalServiceUtil.send(rofhBean);
				}
			});
		}
		
	}
}
