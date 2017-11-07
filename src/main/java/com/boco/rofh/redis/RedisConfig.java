package com.boco.rofh.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.boco.rofh.webservice.pojo.ConfigTaskReq;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author gaoyang  2017年11月3日
 *
 * @Description: redis配置
 *
 */
@Configuration
public class RedisConfig {

	@Bean
	public RedisTemplate<String, ConfigTaskReq> redisTemplate(RedisConnectionFactory connectionFactory) {

		RedisTemplate<String, ConfigTaskReq> redisTemplate = new RedisTemplate<>();
		Jackson2JsonRedisSerializer<ConfigTaskReq> taskSerializer = new Jackson2JsonRedisSerializer<>(
				ConfigTaskReq.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		taskSerializer.setObjectMapper(mapper);
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(taskSerializer);
		return redisTemplate;
	}
}
