package com.boco.rofh.webservice;

import com.boco.rofh.utils.SpringUtil;

public class ActiveService implements IActiveService{

	private IActiveService activeService;
	
	public ActiveService() {
		
		activeService = SpringUtil.getBean(IActiveService.class);
	}
	
	@Override
	public String inform(String system, String msgType, String msgBody) {
		
		return activeService.inform(system, msgType, msgBody);
	}
	
}
