package com.boco.rofh.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ServiceMonitor {

	private static final Logger logger = LoggerFactory.getLogger(ServiceMonitor.class);
	
/*	@Autowired
	private GaugeService gaugeService;*/

	
	@Around("execution(* com.boco.rofh.webservice.*Impl.*(..))")
	public Object latencyService(ProceedingJoinPoint pjp) throws Throwable {
		
	 long start = System.currentTimeMillis();
	 Object obj = pjp.proceed();
	 long end = System.currentTimeMillis();
	 
	 String name = pjp.getSignature().getName();
	 
	 long spend = end - start;
	// gaugeService.submit("接口" + name, spend);
	 
	 logger.info("返回报文：" + obj);
	 logger.info(name + "接口耗时：" + spend + "ms");
	 return obj;
	}
}