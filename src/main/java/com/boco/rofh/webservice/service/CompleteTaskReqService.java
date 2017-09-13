package com.boco.rofh.webservice.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.webservice.pojo.ConfigTaskReq;
import com.boco.rofh.webservice.task.AbstractCompleteTask;
import com.boco.rofh.webservice.task.InstallCompleteTask;
import com.boco.rofh.webservice.task.MoveCompleteTask;
import com.boco.rofh.webservice.task.RemoveCompleteTask;

/**
 * 资源报俊
 * @author gaoyang
 *
 */
@Service
public class CompleteTaskReqService extends BaseRofhWebService<ConfigTaskReq,Object>{
	
	
	private Map<String,AbstractCompleteTask> completeTasks;
	
	@Autowired
	private InstallCompleteTask installCompleteTask;
	@Autowired
	private RemoveCompleteTask removeCompleteTask;
	@Autowired
	private MoveCompleteTask moveCompleteTask;
	
	@Override
	protected Object doBusiness(final ConfigTaskReq req) {
		
		completeTasks.get(req.getReqInfo().getActCode()).doTask(req, getRegionId());
		
		return null;
		
	}

	@PostConstruct
	private void init(){
		
		completeTasks = new HashMap<String,AbstractCompleteTask>();
		completeTasks.put("INSTALL", installCompleteTask);
		completeTasks.put("UNINSTALL", removeCompleteTask);
		completeTasks.put("MOVE", moveCompleteTask);
	}
	
}
