package com.boco.rofh.utils;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.boco.active.MessageReceiver;
import com.boco.active.MessageReceiverImplService;

@Component
public class ActiveServiceUtil {

	@Value("${activeWsServiceUrl}")
	private String WSURL ;
	
	private static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";
	
	private MessageReceiverImplService messageReceiverImplService;
	
	private final static Logger logger = Logger.getLogger(ActiveServiceUtil.class);
	
	private MessageReceiver getService(){
		
		if(messageReceiverImplService == null){
			init();
		}
		return messageReceiverImplService.getMessageReceiverImplPort();
	} 
	
	public String send(String msg){

		msg = XML_HEAD + RofhUtil.replaceBlank(msg);
		return getService().process("IRMS", "SVC_FULFILL_CFG", msg);
	}
	
	@PostConstruct
	private void init(){
		
		try {
			URL url = new URL(WSURL);
			messageReceiverImplService = new MessageReceiverImplService(url);
		} catch (MalformedURLException e) {
			
			logger.error("激活服务无法连接",e);
		}
		
	}
	
	public String[] status(){
		
		String[] str = new String[2];
		str[0] = WSURL;
		str[1] = messageReceiverImplService == null ? "ERROE" : "OK";
		return str;
	}

}
