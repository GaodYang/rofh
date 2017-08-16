package com.boco.rofh.utils;

import java.net.URL;

import javax.annotation.PostConstruct;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pboss.webservice.ArrayOfXsdString;
import com.pboss.webservice.ObjectFactory;
import com.pboss.webservice.SfRmSVImpl;
import com.pboss.webservice.SfRmSVImplService;

@Service
public class PbossServiceUtil {

	@Value("${pbossWsServiceUrl}")
	private String WSURL ;
	
	private String nameSpace;
	
	private ArrayOfXsdString staff ;
	
	private SfRmSVImplService implService;
	
	private static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";
	
	private final static Logger logger = Logger.getLogger(PbossServiceUtil.class);

	private SfRmSVImpl getService(){
		
		if(implService == null){
			init();
		}
		return implService.getPort(new QName("SfRmSVImpl"), SfRmSVImpl.class);
	}
	
	public String sendSuccess(Long taskId , String xml , String regionId){
		
		xml = XML_HEAD + RofhUtil.replaceBlank(xml);
		return getService().finishRmTaskAsyn(taskId, staff, null, "资源回单", xml, regionId);
	}
	
	public String sendError(Long taskId , String xml ,String excpCode, String desc, String regionId){
		
		xml = XML_HEAD + RofhUtil.replaceBlank(xml);
		return getService().finishRmTaskAsyn(taskId, staff, excpCode, desc , xml, regionId);
	}
	
	
	@PostConstruct
	void init(){
		staff = new ObjectFactory().createArrayOfXsdString();
		staff.getItem().add(0, "99999");
		staff.getItem().add(1, "AUTO_STAFF");
		staff.getItem().add(2, "系统自动");
		
		nameSpace = WSURL.substring(0,WSURL.indexOf("?"));
		
		try {
			URL url = new URL(WSURL);
			implService = new SfRmSVImplService(url,new QName(nameSpace,"SfRmSVImplService"));
		} catch (Exception e) {
			
			logger.error("pboss服务无法连接" , e);
		}
	}
	
	public String[] status(){
		
		String[] str = new String[2];
		str[0] = WSURL;
		str[1] = implService == null ? "ERROE" : "OK";
		return str;
	}
}
