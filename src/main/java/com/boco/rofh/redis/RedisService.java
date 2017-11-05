package com.boco.rofh.redis;

import java.util.HashSet;
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
	private RedisTemplate<RedisKey,ConfigTaskReq> redisTemplate;
	
	private static final RedisKey KEY_PARTTEN = new RedisKey("[0-9]*","[1-9]");
	
	private static final int MAX_NO = 10;
	
	
	@Resource(name = "redisTemplate")
	private ZSetOperations<RedisKey,ConfigTaskReq> zSetOperations;
	
	public void addTask(RedisKey key,ConfigTaskReq req){
		
		zSetOperations.add(key, req,req.getReqInfo().getPriority());
	}
	
	public Set<ConfigTaskReq> getTask(RedisKey key){
		
		return zSetOperations.reverseRange(key, 0, MAX_NO);
	}
	
	public Long isFull(RedisKey key){
		
		return zSetOperations.zCard(key) - key.getNo();
	}
	
	public void remove(RedisKey key){
		
		redisTemplate.delete(key);
	}
	
	public Set<ConfigTaskReq> getTaskAndRemove(RedisKey key){
		
		Set<ConfigTaskReq> set = this.getTask(key);
		this.remove(key);
		return set;
	}
	
	public Set<RedisKey> getKeys(){
		
		return redisTemplate.keys(KEY_PARTTEN);
	}
	
	public Set<RedisKey> getKeyByOrderId(String orderId){
		
		return redisTemplate.keys(new RedisKey(orderId, "[1-9]"));
	}
	
	
}
