package com.boco.rofh.webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.webservice.pojo.ConfigTaskReq;
import com.boco.rofh.webservice.task.CancelResourceTask;
import com.boco.rofh.webservice.task.ThreadPoolManager;

/**
 * 资源撤单
 * @author gaoyang
 */
@Service
public class CancelTaskReqService extends BaseRofhWebService<ConfigTaskReq,Object>{


	@Autowired
	private CancelResourceTask cancelResourceTask;
	
	@Override
	protected Object doBusiness(final ConfigTaskReq req) {
		
		final String regionId = getRegionId();
		ThreadPoolManager.getInstance().addTask(this.getClass(), new Runnable() {
			public void run() {
				
					cancelResourceTask.doTask(req, regionId);
					
		}});
		
		return null;
	}
	
	
	
}
