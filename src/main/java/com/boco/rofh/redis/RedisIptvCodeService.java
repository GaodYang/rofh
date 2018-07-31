package com.boco.rofh.redis;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 * @author gaoyang  2017年11月3日
 *
 * @Description: redis处理数据
 *
 */
@Service
public class RedisIptvCodeService {
	
	@Resource(name = "redisTemplate")
	private HashOperations<String,String,String> hashOperations;
	
	private static final String KEY = "iptv_code";
	
	public void add(String id,String code){
		
		hashOperations.put(KEY, id, code);
	}
	
	public String get(String id){
		
		if(StringUtils.isBlank(id)) {
			return null;
		}
		
		return hashOperations.get(KEY, id);
	}
	
	
	public void remove(String id){
		
		hashOperations.delete(KEY, id);
	}
	
}
