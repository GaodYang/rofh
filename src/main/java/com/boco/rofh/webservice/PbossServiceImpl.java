package com.boco.rofh.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.webservice.service.CancelTaskReqService;
import com.boco.rofh.webservice.service.CompleteTaskReqService;
import com.boco.rofh.webservice.service.ConfigTaskReqService;

@Service
public class PbossServiceImpl implements IPbossService{

	@Autowired
	private ConfigTaskReqService configTaskReqService;
	
	@Autowired
	private CompleteTaskReqService completeTaskReqService;
	
	@Autowired
	private CancelTaskReqService cancelTaskReqService;
	
	@Override
	public String configTaskReq(String sReceiveMsg, String sRegionId) {
		
		return configTaskReqService.doProcess(sReceiveMsg, sRegionId);
	}
	
	@Override
	public String completeTaskReq(String sReceiveMsg, String sRegionId) {
		
		return completeTaskReqService.doProcess(sReceiveMsg, sRegionId);
	};
	
	@Override
	public String cancelTaskReq(String sReceiveMsg, String sRegionId) {
		
		return cancelTaskReqService.doProcess(sReceiveMsg,sRegionId);
	}
}
