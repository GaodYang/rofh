package com.boco.rofh.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.rofh.webservice.pojo.ConfigTaskReq;

public class ConfigReqPool {

	private ConfigReqPool() {
	}

	private Map<String,List<ConfigTaskReq>> reqMap = new HashMap<>();
	
	private static final ConfigReqPool instance = new ConfigReqPool();
	
	public static ConfigReqPool getInstance(){
		
		return instance;
	}
	
	private void put(String id,ConfigTaskReq taskReq){
		
		if(reqMap.containsKey(id)){
			
			reqMap.get(id).add(taskReq);
		}else{
			
			List<ConfigTaskReq> list = new ArrayList<>();
			list.add(taskReq);
			reqMap.put(id, list);
		}
	}
	
	public List<ConfigTaskReq> getTask(String id,int num,ConfigTaskReq configTaskReq){
		
		synchronized (id.intern()) {
			
			this.put(id, configTaskReq);
			
			List<ConfigTaskReq> list = reqMap.get(id);
			if(list != null && list.size() == num){
				
				Collections.sort(list);
				return list;
			}
			return null;
		}
	}
	
	public void remove(String id){
		
		if(reqMap.containsKey(id)){
			
			reqMap.remove(id);
		}
	}
	
	public Map<String,List<ConfigTaskReq>> getMap(){
		
		return this.reqMap;
	}
}
