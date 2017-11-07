package com.boco.rofh.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;

import com.boco.rofh.utils.ConfigReqPool;
import com.boco.rofh.webservice.pojo.ConfigTaskReq;

public class RedisTaskManager extends ConfigReqPool{

	
	@Autowired
	private RedisService redisService;
	
	@Override
	public Map<String, Set<ConfigTaskReq>> getMap() {
		return super.getMap();
	}
	
	@Override
	public Set<ConfigTaskReq> getTask(String id, int num, ConfigTaskReq configTaskReq) {
		
		Set<ConfigTaskReq> result = null;
		if(num == 1){
			
			Set<RedisKey> keys = redisService.getKeyByOrderId(id);
			if(keys != null && keys.size() > 0){
				
				result = redisService.getTaskAndRemove(keys.iterator().next());
			}else{
				
				result = new TreeSet<>();
			}
			result.add(configTaskReq);
			
		}else{
			
			RedisKey key = new RedisKey(id, num + "");
			redisService.addTask(key, configTaskReq);
			if(redisService.isFull(key)) {
				
				result = redisService.getTaskAndRemove(key);
			}
		}
		
		return result;
	}
	
	public List<Set<ConfigTaskReq>> getFullOrders(){
		
		List<Set<ConfigTaskReq>> list = new ArrayList<>();
		Set<RedisKey> keys = redisService.getKeys();
		if(keys != null){
			
			keys.forEach( key -> {
				
				if(redisService.isFull(key)){
					
					list.add(redisService.getTaskAndRemove(key));
				}
			} );
		}
		
		return list;
	}
		
}
