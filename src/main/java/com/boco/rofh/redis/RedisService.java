package com.boco.rofh.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.boco.rofh.webservice.pojo.ConfigTaskReq;

/**
 * 
 * @author gaoyang  2017年11月3日
 *
 * @Description: redis处理数据
 *
 */
@Service
public class RedisService {

	@Autowired
	private RedisTemplate<String,ConfigTaskReq> redisTemplate;
	
	private static final String KEY_PARTTEN = "[0-9]*\\:[0-9]*\\:[1-9]";
	
	private static final int MAX_NO = 10;
	
	
	@Resource(name = "redisTemplate")
	private ZSetOperations<String,ConfigTaskReq> zSetOperations;
	
	public void addTask(RedisKey key,ConfigTaskReq req){
		
		zSetOperations.add(key.toKey(), req,req.getReqInfo().getPriority());
	}
	
	public Set<ConfigTaskReq> getTask(RedisKey key){
		
		return zSetOperations.reverseRange(key.toKey(), 0, MAX_NO);
	}
	
	public boolean isFull(RedisKey key){
		
		return zSetOperations.zCard(key.toKey()) >= key.getNo();
	}
	
	public void remove(RedisKey key){
		
		redisTemplate.delete(key.toKey());
	}
	
	public void remove(String key){
		
		redisTemplate.delete(key);
	}
	
	public Set<ConfigTaskReq> getTaskAndRemove(RedisKey key){
		
		Set<ConfigTaskReq> set = this.getTask(key);
		this.remove(key);
		return set;
	}
	
	public List<RedisKey> getKeys(){
		
		return this.getKeyByOrderId("[0-9]*");
	}
	
	public List<RedisKey> getKeyByOrderId(String orderId){
		
		List<RedisKey> keys = new ArrayList<>();
		Set<String> skey = redisTemplate.keys("[0-9]*\\:"+orderId+"\\:[1-9]");
		if(skey != null){
			
			skey.forEach( key -> keys.add(RedisKey.fromKey(key)) );
		}
		return keys;
	}
	
	
}
