package com.boco.rofh.utils;

import java.net.URL;
import java.rmi.RemoteException;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.boco.nais.core.web.msgreceiver.MessageReceiverImplServiceLocator;
import com.boco.nais.core.web.msgreceiver.MessageReceiverImplServiceSoapBindingStub;

@Component
public class ActiveServiceUtil {

	@Value("${activeWsServiceUrl}")
	private String WSURL ;
	
	private MessageReceiverImplServiceSoapBindingStub bindingStub;
	
	private final static Logger logger = Logger.getLogger(ActiveServiceUtil.class);
	
	
	public String send(String msg) throws RemoteException{

		return bindingStub.process("IRMS", "SVC_FULFILL_CFG", msg);
	}
	
	@PostConstruct
	private void init(){
		
		try {
			URL url = new URL(WSURL);
			MessageReceiverImplServiceLocator implServiceLocator = new MessageReceiverImplServiceLocator();
			bindingStub = new MessageReceiverImplServiceSoapBindingStub(url,implServiceLocator);
		} catch (Exception e) {
			
			logger.error("激活服务无法连接",e);
		}
		
	}
	
	public String[] status(){
		
		String[] str = new String[2];
		str[0] = WSURL;
		str[1] = bindingStub == null ? "ERROE" : "OK";
		return str;
	}

}
