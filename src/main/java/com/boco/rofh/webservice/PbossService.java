package com.boco.rofh.webservice;

import com.boco.rofh.utils.SpringUtil;

public class PbossService implements IPbossService{

	private IPbossService pbossService;
	
	public PbossService() {
		
		pbossService = SpringUtil.getBean(IPbossService.class);
	}

	@Override
	public String configTaskReq(String sReceiveMsg, String sRegionId) {

		return pbossService.configTaskReq(sReceiveMsg, sRegionId);
	}

	@Override
	public String completeTaskReq(String sReceiveMsg, String sRegionId) {

		return pbossService.completeTaskReq(sReceiveMsg, sRegionId);
	}

	@Override
	public String cancelTaskReq(String sReceiveMsg, String sRegionId) {

		return pbossService.cancelTaskReq(sReceiveMsg, sRegionId);
	}
	
	
}
