package com.boco.rofh.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author gaoyang 2017年3月7日 常量定义类
 */
public class WebServiceConstant {

	private WebServiceConstant() throws IllegalAccessException {
		throw new IllegalAccessException("不能创建此类");
	}

	/**
	 * xml消息头
	 */
	public static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";

	/**
	 * CDATA标签开始
	 */
	public static final String PREFIX_CDATA = "<![CDATA[";

	/**
	 * CDATA标签结束
	 */
	public static final String SUFFIX_CDATA = "]]>";

	
	public static final int DEFAULT_POOL_SIZE = 20;
	/**
	 * 接入类型枚举
	 * 
	 * @author gaoyang 2017年3月8日
	 *
	 */
	public static class AccessMode {

		public static final String FTTB = "1";
		public static final String FTTH = "2";
		public static final String PON_LAN = "3";
		public static final String 光纤_LAN = "4";
		public static final String XDSL = "5";
		public static final String CABLE = "6";
		public static final String WBS = "7";
		public static final String 广电 = "8";
		public static final String IPTV = "15";
		public static final String IMS = "16";

	}

	//0普通宽带  1一网通宽带	2小微宽带
	public static class BusinessType {

		public static final String 宽带 = "12";
		public static final String 语音 = "13";
		public static final String IPTV = "15";
		public static final String IMS = "16";
	}
	
	public static class ProductType {

		public static final String 宽带业务 = "1";
		public static final String IMS业务 = "2";
		public static final String OTT业务 = "3";
		public static final String IPTV业务 = "15";
		public static final String 无线宽带 = "99";
	}
	
	public static class PtpState {

		public static final String 空闲 = "1";
		public static final String 预占 = "3";
		public static final String 实占 = "2";
	}

	public static class ProductAction {
		public static final String 装机 = "21";
		public static final String 撤单 = "22";
		public static final String 拆机 = "25";
		public static final String 移机 = "23";
		public static final String 移机拆机单 = "99";
		public static final String 入网 = "10";
	}

	public static class BusinessState {
		public static final String 未归档 = "0";
		public static final String 已归档 = "1";
		public static final String 已取消 = "2";

	}

	public static class ProductStatus {
		public static final String 在网 = "1";
		public static final String 退网 = "2";
		public static final String 工程 = "3";
		public static final String 工程取消 = "4";
		public static final String 停机 = "5";
	}
	
	public static final Map<String, String> ACTIVATESTATE = new HashMap();
	
	static {
		ACTIVATESTATE.put("20-000", "22");
		ACTIVATESTATE.put("20-001", "23");
		ACTIVATESTATE.put("30-000", "32");
		ACTIVATESTATE.put("30-001", "33");
	 }
	
	public static enum ProdSerType{
		
		WBS,CTT,FTTB,FTTH,IPTV,IMS;
	}
	
	public static enum DataSource{
		
		SF,HIS,ATTEMP;
	}

	public static enum ProducrAction{
		
		INSTALL("21"),UNINSTALL("25"),MOVE("23");
		
		private String code;
		private ProducrAction(String code) {
			
			this.code = code;
		}
		
		public String getCode(){
			
			return code;
		}
		
		public static String getName(String code){
			

			for(ProducrAction action : ProducrAction.values()){
				
				if(action.code.equals(code)){
					
					return action.name();
				}
			}
			
			return code;
		}
		
	}
}
