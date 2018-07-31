package com.boco.rofh.utils;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhCustomer;
import com.boco.rofh.entity.RofhOrder;
import com.boco.rofh.entity.RofhProduct;
import com.boco.scms.service.barcode.marketinginstall.InData;
import com.boco.scms.service.barcode.marketinginstall.TerminalMarketingInstallServiceLocator;
import com.boco.scms.service.barcode.marketinginstall.TerminalMarketingInstallServiceSoapBindingStub;

@Service
public class TerminalServiceUtil {

	@Value("${terminalWsServiceUrl}")
	private String WSURL ;
	
	private TerminalMarketingInstallServiceSoapBindingStub bindingStub;
	
	private final static Logger logger = Logger.getLogger(TerminalServiceUtil.class);
	
	public String marketingInstallPort(InData data) throws RemoteException{
		
		InData[] datas = {data};
		return bindingStub.marketingInstallPort(3, "111111", "222222", datas);
	}
	
	
	@PostConstruct
	void init(){
		
		try {
			URL url = new URL(WSURL);
			TerminalMarketingInstallServiceLocator Locator = new TerminalMarketingInstallServiceLocator();
			bindingStub = new TerminalMarketingInstallServiceSoapBindingStub(url, Locator);
			
		} catch (Exception e) {
			
			logger.error("terminal服务无法连接" , e);
		}
	}
	
	public String[] status(){
		
		String[] str = new String[2];
		str[0] = WSURL;
		str[1] = bindingStub == null ? "ERROE" : "OK";
		return str;
	}
	
	public void send(RofhBean rofhBean) {
		
		RofhProduct product = rofhBean.getProduct();
		RofhOrder order = rofhBean.getOrder();
		RofhCustomer customer = rofhBean.getCustomer();
		String sn = product.getSnCode();
		String mac = product.getStbMac();
		
		if(WebServiceConstant.ProductType.宽带业务.equals(product.getProductType()) && StringUtils.isBlank(sn)) {
			
			sn = "ZZ" + RofhUtil.getRandomNum(9);
		}
		InData data = new InData(null, null, null, order.getCrmSheetNo(), product.getInstallAddress(), 
				product.getAccountName(), customer.getLabelCn(), 
				customer.getContactNumber(), DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"),
				mac, null, sn, (short) 22, "综合资源", "综合资源", "0000");
		
		logger.debug("终端系统发送信息：" + ToStringBuilder.reflectionToString(data, ToStringStyle.SHORT_PREFIX_STYLE));
		
		try {
			
			String result = this.marketingInstallPort(data);
			logger.debug("终端系统返回信息：" + result);
		} catch (RemoteException e) {
			
			logger.error("向终端系统发送数据出错！", e);
		}
	}
	
}
