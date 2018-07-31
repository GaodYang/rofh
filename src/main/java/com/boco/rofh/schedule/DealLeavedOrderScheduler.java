package com.boco.rofh.schedule;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.boco.rofh.redis.RedisTaskKey;
import com.boco.rofh.redis.RedisTaskManager;
import com.boco.rofh.webservice.pojo.ConfigTaskReq;
import com.boco.rofh.webservice.service.ConfigTaskReqService;
import com.boco.rofh.webservice.task.ThreadPoolManager;

/**
 * 
 * @author gaoyang 2017年11月6日
 *
 * @Description: 处理遗漏工单
 *
 */
@Component
public class DealLeavedOrderScheduler {

	private static final Logger logger = LoggerFactory.getLogger(DealLeavedOrderScheduler.class);

	@Autowired
	private RedisTaskManager manager;

	@Autowired
	private ConfigTaskReqService configService;
	
	@Value("${server.port}")
	private String port;

	@Scheduled(cron = "0 0 1 * * ?")
	public void doTask() {

		logger.debug("start DealLeavedOrderScheduler..." + port);
		if(!"9067".equals(port)){
			return;
		}

		try {	
			Map<RedisTaskKey, Set<ConfigTaskReq>> map = manager.getFullOrders();

			logger.debug("execute tasks :" + map);

			if (map != null) {
	
				map.keySet().forEach(key -> {
	
					ThreadPoolManager.getInstance().addTask(this.getClass(), new Runnable() {
						@Override
						public void run() {
	
							map.get(key).forEach(config -> {
	
								configService.runTask(config, key.getRegionId());
							});
						}
					});
	
					manager.remove(key.toKey());
				});
			}
		} catch (Exception e) {
			
			logger.error("DealLeavedOrderScheduler error",e);
		}

		logger.debug("end DealLeavedOrderScheduler...");
	}
}
