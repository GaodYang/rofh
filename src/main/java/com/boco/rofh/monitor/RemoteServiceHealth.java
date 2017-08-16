package com.boco.rofh.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.boco.rofh.utils.ActiveServiceUtil;
import com.boco.rofh.utils.PbossServiceUtil;

@Component("远程服务")
public class RemoteServiceHealth implements HealthIndicator {

	@Autowired
	PbossServiceUtil pbossServiceUtil;
	
	@Autowired
	ActiveServiceUtil activeServiceUtil;
	
	@Override
	public Health health() {
		
		String[] pboss = pbossServiceUtil.status();
		String[] actv = activeServiceUtil.status();
		
		Builder healthBuilder = new Health.Builder()
				.withDetail(pboss[0],pboss[1]) 
				.withDetail(actv[0], actv[1]);
		
		if("OK".equals(pboss[1]) && "OK".equals(actv[1])){
			return healthBuilder.up().build();
		}
		
		return healthBuilder.down().build();
	}
}
