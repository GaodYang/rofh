package com.boco.rofh.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.boco.rofh.webservice.pojo.ConfigTaskReq;

@Service
public class ConfigReqPool {

	private Map<String,Set<ConfigTaskReq>> reqMap = new HashMap<>();
	
	
	private void put(String id,ConfigTaskReq taskReq){
		
		if(reqMap.containsKey(id)){
			
			reqMap.get(id).add(taskReq);
		}else{
			
			Set<ConfigTaskReq> list = new TreeSet<>();
			list.add(taskReq);
			reqMap.put(id, list);
		}
	}
	
	public Set<ConfigTaskReq> getTask(ConfigTaskReq configTaskReq,String regionId){
		
		int num = configTaskReq.getReqInfo().getSubOrderNum();
		String id = configTaskReq.getReqInfo().getSrcOrderGrpId();
		synchronized (id.intern()) {
			
			this.put(id, configTaskReq);
			
			Set<ConfigTaskReq> list = reqMap.get(id);
			if(list != null && list.size() >= num){
				
				this.remove(id);
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
	
	public Map<String,Set<ConfigTaskReq>> getMap(){
		
		return this.reqMap;
	}

	public void removeTask(ConfigTaskReq req, String regionId) {
		
		return ;
	}
}
