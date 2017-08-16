package com.boco.rofh.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.webservice.service.ActiveNetService;

@Service
public class ActiveServiceImpl implements IActiveService{

	@Autowired
	ActiveNetService activeNetService;
	
	private static final Logger logger = LoggerFactory.getLogger(ActiveServiceImpl.class);
	
	@Override
	public String inform(String system, String msgType, String msgBody) {
		
		logger.info("=====激活接口===结果通知==开始==");
		logger.info("调用参数：system=" + system + ",msgType=" + msgType + ",msgBody=" + msgBody);
		long startTime = System.currentTimeMillis();
		String returnStr = activeNetService.getInform(system,msgType,msgBody);
		logger.info("=====激活接口===结果通知==结束==，花费时间："+ (System.currentTimeMillis() - startTime) + " 毫秒");
		return returnStr;
	}
}
