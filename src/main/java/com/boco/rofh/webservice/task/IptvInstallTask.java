package com.boco.rofh.webservice.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.boco.rofh.entity.RofhProduct;

@Service
public class IptvInstallTask extends AbstractInstallTask{

	private static final Logger logger = LoggerFactory
			.getLogger(IptvInstallTask.class);
	
	public boolean occupyPort(RofhProduct rofhProduct){
	
		return true;
	}
	
	protected void createAttempPonWay(RofhProduct rofhProduct) {


		rofhProduct.setCuid(null);

	}
	
	
}
