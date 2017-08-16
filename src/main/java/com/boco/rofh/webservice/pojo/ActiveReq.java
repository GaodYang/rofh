package com.boco.rofh.webservice.pojo;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.boco.rofh.utils.XStreamUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("msgBody")
public class ActiveReq {

	private SvcFulfillMsg svcFulfillMsg;
	
	public static class SvcFulfillMsg{
		
		private String sheetMainCode;
		
		private String sheetCode;
		
		private String sheetTitle;
		
		private String operType;
		
		private String deadlineTime;
		
		private String dispatchUser;
		
		private String dispatchDepartment;
		
		private String dispatchTelephone;
		
		private String cityName;
		
		private String memo;
		
		private Product product;
		
		private NewResData  newResData;

		public String getSheetMainCode() {
			return sheetMainCode;
		}

		public void setSheetMainCode(String sheetMainCode) {
			this.sheetMainCode = sheetMainCode;
		}

		public String getSheetCode() {
			return sheetCode;
		}

		public void setSheetCode(String sheetCode) {
			this.sheetCode = sheetCode;
		}

		public String getSheetTitle() {
			return sheetTitle;
		}

		public void setSheetTitle(String sheetTitle) {
			this.sheetTitle = sheetTitle;
		}

		public String getOperType() {
			return operType;
		}

		public void setOperType(String operType) {
			this.operType = operType;
		}

		public String getDeadlineTime() {
			return deadlineTime;
		}

		public void setDeadlineTime(String deadlineTime) {
			this.deadlineTime = deadlineTime;
		}

		public String getDispatchUser() {
			return dispatchUser;
		}

		public void setDispatchUser(String dispatchUser) {
			this.dispatchUser = dispatchUser;
		}

		public String getDispatchDepartment() {
			return dispatchDepartment;
		}

		public void setDispatchDepartment(String dispatchDepartment) {
			this.dispatchDepartment = dispatchDepartment;
		}

		public String getDispatchTelephone() {
			return dispatchTelephone;
		}

		public void setDispatchTelephone(String dispatchTelephone) {
			this.dispatchTelephone = dispatchTelephone;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public String getMemo() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public NewResData getNewResData() {
			return newResData;
		}

		public void setNewResData(NewResData newResData) {
			this.newResData = newResData;
		}
		
		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}
		
	}
	
	public static class Product{
		
		private String ontType;
		
		private String productCode;
		
		private String productType;
		
		private String businessCity;
		
		private String customerName;
		
		private String customerCode;
		
		private String customerCity;
		
		private String customerAddress;
		
		private String regionName;
		
		private String nettopoType;

		public String getOntType() {
			return ontType;
		}

		public void setOntType(String ontType) {
			this.ontType = ontType;
		}

		public String getProductCode() {
			return productCode;
		}

		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}

		public String getProductType() {
			return productType;
		}

		public void setProductType(String productType) {
			this.productType = productType;
		}

		public String getBusinessCity() {
			return businessCity;
		}

		public void setBusinessCity(String businessCity) {
			this.businessCity = businessCity;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public String getCustomerCode() {
			return customerCode;
		}

		public void setCustomerCode(String customerCode) {
			this.customerCode = customerCode;
		}

		public String getCustomerCity() {
			return customerCity;
		}

		public void setCustomerCity(String customerCity) {
			this.customerCity = customerCity;
		}

		public String getCustomerAddress() {
			return customerAddress;
		}

		public void setCustomerAddress(String customerAddress) {
			this.customerAddress = customerAddress;
		}

		public String getRegionName() {
			return regionName;
		}

		public void setRegionName(String regionName) {
			this.regionName = regionName;
		}

		public String getNettopoType() {
			return nettopoType;
		}

		public void setNettopoType(String nettopoType) {
			this.nettopoType = nettopoType;
		}
		
		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}
		
	}
	
	public static class NewResData{
		
		private List<Device> deviceList;

		public List<Device> getDeviceList() {
			return deviceList;
		}

		public void setDeviceList(List<Device> deviceList) {
			this.deviceList = deviceList;
		}
		
		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}
	}
	
	@XStreamAlias("device")
	public static class Device{
		
		private String deviceName;
		
		private String deviceType;
		
		private List<Olt> attribute;

		public String getDeviceName() {
			return deviceName;
		}

		public void setDeviceName(String deviceName) {
			this.deviceName = deviceName;
		}

		public String getDeviceType() {
			return deviceType;
		}

		public void setDeviceType(String deviceType) {
			this.deviceType = deviceType;
		}

		public List<Olt> getAttribute() {
			return attribute;
		}

		public void setAttribute(List<Olt> attribute) {
			this.attribute = attribute;
		}
		
		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}
	}
	
	@XStreamAlias("olt")
	public static class Olt{
		
		private String ems;
		
		private String adminIp;
		
		private String ipmanSw;
		
		private String oltUpPort1;
		
		private String oltUpPort2;
		
		private String ponPort;
		
		private String posL1Name;
		
		private String posL1Ratio;
		
		private String posL2Name;
		
		private String posL2Ratio;
		
		private String posPonPort;
		
		private String onuIp;
		
		private String onuOper;
		
		private String onuType;
		
		private String onuModel;
		
		private String onuPassword;
		
		private String onuAddress;
		
		private String onuId;
		
		private String onuName;
		
		private String onuCustPort;
		
		private String onuPortType;
		
		private String svlan;
		
		private String cvlan;
		
		private String onuFactory;

		public String getEms() {
			return ems;
		}

		public void setEms(String ems) {
			this.ems = ems;
		}

		public String getAdminIp() {
			return adminIp;
		}

		public void setAdminIp(String adminIp) {
			this.adminIp = adminIp;
		}

		public String getIpmanSw() {
			return ipmanSw;
		}

		public void setIpmanSw(String ipmanSw) {
			this.ipmanSw = ipmanSw;
		}

		public String getOltUpPort1() {
			return oltUpPort1;
		}

		public void setOltUpPort1(String oltUpPort1) {
			this.oltUpPort1 = oltUpPort1;
		}

		public String getOltUpPort2() {
			return oltUpPort2;
		}

		public void setOltUpPort2(String oltUpPort2) {
			this.oltUpPort2 = oltUpPort2;
		}

		public String getPonPort() {
			return ponPort;
		}

		public void setPonPort(String ponPort) {
			this.ponPort = ponPort;
		}

		public String getPosL1Name() {
			return posL1Name;
		}

		public void setPosL1Name(String posL1Name) {
			this.posL1Name = posL1Name;
		}

		public String getPosL1Ratio() {
			return posL1Ratio;
		}

		public void setPosL1Ratio(String posL1Ratio) {
			this.posL1Ratio = posL1Ratio;
		}

		public String getPosL2Name() {
			return posL2Name;
		}

		public void setPosL2Name(String posL2Name) {
			this.posL2Name = posL2Name;
		}

		public String getPosL2Ratio() {
			return posL2Ratio;
		}

		public void setPosL2Ratio(String posL2Ratio) {
			this.posL2Ratio = posL2Ratio;
		}

		public String getPosPonPort() {
			return posPonPort;
		}

		public void setPosPonPort(String posPonPort) {
			this.posPonPort = posPonPort;
		}

		public String getOnuIp() {
			return onuIp;
		}

		public void setOnuIp(String onuIp) {
			this.onuIp = onuIp;
		}

		public String getOnuOper() {
			return onuOper;
		}

		public void setOnuOper(String onuOper) {
			this.onuOper = onuOper;
		}

		public String getOnuType() {
			return onuType;
		}

		public void setOnuType(String onuType) {
			this.onuType = onuType;
		}

		public String getOnuModel() {
			return onuModel;
		}

		public void setOnuModel(String onuModel) {
			this.onuModel = onuModel;
		}

		public String getOnuPassword() {
			return onuPassword;
		}

		public void setOnuPassword(String onuPassword) {
			this.onuPassword = onuPassword;
		}

		public String getOnuAddress() {
			return onuAddress;
		}

		public void setOnuAddress(String onuAddress) {
			this.onuAddress = onuAddress;
		}

		public String getOnuId() {
			return onuId;
		}

		public void setOnuId(String onuId) {
			this.onuId = onuId;
		}

		public String getOnuName() {
			return onuName;
		}

		public void setOnuName(String onuName) {
			this.onuName = onuName;
		}

		public String getOnuCustPort() {
			return onuCustPort;
		}

		public void setOnuCustPort(String onuCustPort) {
			this.onuCustPort = onuCustPort;
		}

		public String getOnuPortType() {
			return onuPortType;
		}

		public void setOnuPortType(String onuPortType) {
			this.onuPortType = onuPortType;
		}

		public String getSvlan() {
			return svlan;
		}

		public void setSvlan(String svlan) {
			this.svlan = svlan;
		}

		public String getCvlan() {
			return cvlan;
		}

		public void setCvlan(String cvlan) {
			this.cvlan = cvlan;
		}

		public String getOnuFactory() {
			return onuFactory;
		}

		public void setOnuFactory(String onuFactory) {
			this.onuFactory = onuFactory;
		}
		
		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}
	}

	public SvcFulfillMsg getSvcFulfillMsg() {
		return svcFulfillMsg;
	}

	public void setSvcFulfillMsg(SvcFulfillMsg svcFulfillMsg) {
		this.svcFulfillMsg = svcFulfillMsg;
	}
	
	public String toXml(){
		
		return XStreamUtil.toXml(this,null);
	}
}
