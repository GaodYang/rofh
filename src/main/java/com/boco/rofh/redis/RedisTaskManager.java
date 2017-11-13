package com.boco.rofh.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.schedule.DealLeavedOrderScheduler;
import com.boco.rofh.utils.ConfigReqPool;
import com.boco.rofh.webservice.pojo.ConfigTaskReq;

@Service
public class RedisTaskManager extends ConfigReqPool{

	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private DealLeavedOrderScheduler dealLeavedOrderScheduler;
	
	private static final Object PRESENT = new Object();
	
	private ConcurrentHashMap<String,Object> taskMap = new ConcurrentHashMap<>();
	
	private static final Logger logger = LoggerFactory.getLogger(RedisTaskManager.class);
	
	@Override
	public Set<ConfigTaskReq> getTask(ConfigTaskReq configTaskReq,String regionId) {
		
		Set<ConfigTaskReq> result = null;
		
		int num = configTaskReq.getReqInfo().getSubOrderNum();
		String id = configTaskReq.getReqInfo().getSrcOrderGrpId();
		if(num == 1){
			
			List<RedisKey> keys = redisService.getKeyByOrderId(id);
			if(keys != null && keys.size() > 0){
				
				result = redisService.getTaskAndRemove(keys.get(0));
			}else{
				
				result = new TreeSet<>();
			}
			result.add(configTaskReq);
			
		}else{
			
			RedisKey key = new RedisKey(regionId,id, num);
			redisService.addTask(key, configTaskReq);
			if(!taskMap.containsKey(key.toKey()) && redisService.isFull(key)) {
				
				result = redisService.getTask(key);
				taskMap.put(key.toKey(), PRESENT);
			}
		}
		
		return result;
	}
	
	public Map<RedisKey,Set<ConfigTaskReq>> getFullOrders(){
		
		Map<RedisKey, Set<ConfigTaskReq>> map = new HashMap<>();
		List<RedisKey> keys = redisService.getKeys();
		if(keys != null){
			
			keys.forEach( key -> {
				
				if(redisService.isFull(key)){
					
					map.put(key,redisService.getTask(key));
				}
			} );
		}
		
		return map;
	}
	
	@Override
	public Map<String, Set<ConfigTaskReq>> getMap() {
		
		Map<String, Set<ConfigTaskReq>> map = new HashMap<>();
		List<RedisKey> keys = redisService.getKeys();
		keys.forEach( key -> {
			
			map.put(key.toKey(),redisService.getTask(key));
		} );
		return map;
	}
	
	@Override
	public void remove(String id) {
		
		redisService.remove(id);
	}
	
	@Override
	public void removeTask(ConfigTaskReq req, String regionId) {
		
		RedisKey key = new RedisKey(regionId, req.getReqInfo().getSrcOrderGrpId(), req.getReqInfo().getSubOrderNum());
		String id = key.toKey();
		this.remove(id);
		taskMap.remove(id);
	}
	
	@PostConstruct
	private void init() throws InterruptedException {
		
		logger.info("处理redis数据。。。");
		dealLeavedOrderScheduler.doTask();
	}
		
}
