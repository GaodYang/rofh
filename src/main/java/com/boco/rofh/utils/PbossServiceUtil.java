package com.boco.rofh.utils;

import java.net.URL;
import java.rmi.RemoteException;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.pboss.webservice.SF4RESSoapBindingStub;
import com.pboss.webservice.SfRmSVImplServiceLocator;

@Service
public class PbossServiceUtil {

	@Value("${pbossWsServiceUrl}")
	private String WSURL ;
	
	private final static String[] STAFF = {"99999","AUTO_STAFF","系统自动"};
	
	private SF4RESSoapBindingStub bindingStub;
	
	private final static Logger logger = Logger.getLogger(PbossServiceUtil.class);
	
	public String sendSuccess(Long taskId , String xml , String regionId) throws RemoteException{
		
		xml = WebServiceConstant.XML_HEAD + RofhUtil.replaceBlank(xml);
		return bindingStub.finishRmTaskAsyn(taskId, STAFF, null, "资源回单", xml, regionId);
	}
	
	public String sendError(Long taskId , String xml ,String excpCode, String desc, String regionId) throws RemoteException{
		
		xml = WebServiceConstant.XML_HEAD + RofhUtil.replaceBlank(xml);
		return bindingStub.finishRmTaskAsyn(taskId, STAFF, excpCode, desc , xml, regionId);
	}
	
	
	@PostConstruct
	void init(){
		
		try {
			URL url = new URL(WSURL);
			SfRmSVImplServiceLocator Locator = new SfRmSVImplServiceLocator();
			bindingStub = new SF4RESSoapBindingStub(url, Locator);
			
		} catch (Exception e) {
			
			logger.error("pboss服务无法连接" , e);
		}
	}
	
	public String[] status(){
		
		String[] str = new String[2];
		str[0] = WSURL;
		str[1] = bindingStub == null ? "ERROE" : "OK";
		return str;
	}
}
