package com.boco.rofh.controller;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boco.rofh.redis.RedisTaskManager;
import com.boco.rofh.utils.ConfigReqPool;
import com.boco.rofh.webservice.pojo.ConfigTaskReq;

@Controller
@RequestMapping("/config")
public class ConfigController {

	@Resource(type = RedisTaskManager.class)
	private ConfigReqPool pool;
	
	@RequestMapping("/list")
	public @ResponseBody Map<String,Set<ConfigTaskReq>> list(){
		
		return pool.getMap();
	}
}
