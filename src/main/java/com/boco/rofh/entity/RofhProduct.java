package com.boco.rofh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the T_ATTEMP_ROFH_PRODUCT database table.
 * 
 */

@MappedSuperclass
public abstract class RofhProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCESS_DEVICE")
	private String accessDevice;

	@Column(name="ACCESS_MODE")
	private String accessMode;

	@Column(name="ACCESS_PORT")
	private String accessPort;

	@Column(name="ACCOUNT_NAME")
	private String accountName;

	@Column(name="ACCOUNT_PASSWORD")
	private String accountPassword;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="APPOINTMENT_TIME")
	private Date appointmentTime;

	@Column(name="APPROVE_RESULT")
	private String approveResult;

	@Column(name="APPROVE_TIME")
	private String approveTime;

	@Column(name="BROADAND_WIDTH")
	private String broadandWidth;

	@Column(name="BUSINESS_CITY_D")
	private String businessCityD;

	@Column(name="BUSINESS_COUNTY_D")
	private String businessCountyD;

	@Column(name="BUSINESS_IP")
	private String businessIp;

	@Column(name="BUSINESS_IP_TYPE")
	private String businessIpType;

	@Column(name="BUSINESS_PROVINCE_D")
	private String businessProvinceD;

	@Column(name="BUSINESS_STATE")
	private String businessState;

	@Column(name="BUSINESS_TYPE")
	private String businessType;

	@Column(name="CELL_ADDRESS")
	private String cellAddress;

	@Column(name="CELL_NAME")
	private String cellName;

	@Column(name="COVERAGE_AREA_FULLDESC")
	private String coverageAreaFulldesc;

	private String cpvlan;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	@Column(name="STB_MAC")
	private String stbMac;

	@Id
	@GeneratedValue(generator = "IDUtils")  
    @GenericGenerator(name = "IDUtils", strategy = "com.boco.rofh.utils.IDUtils",  
    		parameters = { @Parameter(name = "tableName", value = "T_ROFH_RRODUCT") })  
	private String cuid;

	private String cvlan;

	@Column(name="DATA_SOURCE")
	private String dataSource;

	private String distance;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="END_TIME")
	private Date endTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FINISH_TIME")
	private Date finishTime;

	@Column(name="INSTALL_ADDRESS")
	private String installAddress;

	@Column(name="MEAL_STANDARD")
	private String mealStandard;

	@Column(name="ONT_TYPE")
	private String ontType;

	@Column(name="ONU_IS_OLD")
	private String onuIsOld;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OPEN_TIME")
	private Date openTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PREEMPT_TIME")
	private Date preemptTime;

	@Column(name="PRODUCT_ACTION")
	private String productAction;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PRODUCT_NAME")
	private String productName;

	@Column(name="PRODUCT_STATUS")
	private String productStatus;

	@Column(name="PRODUCT_TYPE")
	private String productType;

	@Column(name="RE_OPT_ONU")
	private String reOptOnu;

	@Column(name="RELATED_BMCLASSTYPE_CUID")
	private String relatedBmclasstypeCuid;

	@Column(name="RELATED_COVERAGE_ADDR_CUID")
	private String relatedCoverageAddrCuid;

	@Column(name="RELATED_CUSTOMER_CUID")
	private String relatedCustomerCuid;

	@Column(name="RELATED_MAINTAIN_CUID")
	private String relatedMaintainCuid;

	@Column(name="RELATED_ORDER_CUID")
	private String relatedOrderCuid;

	@Column(name="RELATED_SHEET_CUID")
	private String relatedSheetCuid;

	private String remark;

	@Column(name="RESULT_DETAIL")
	private String resultDetail;

	@Column(name="SALE_TYPE")
	private String saleType;

	@Column(name="SN_CODE")
	private String snCode;

	private String svlan;

	@Column(name="VOICE_NUMBER")
	private String voiceNumber;

	@Column(name="VOICE_PASSWORD")
	private String voicePassword;

	@Column(name="VOICE_RANGE")
	private String voiceRange;

	@Column(name="VOICE_USERNAME")
	private String voiceUsername;

	public RofhProduct() {
	}

	public String getAccessDevice() {
		return this.accessDevice;
	}

	public void setAccessDevice(String accessDevice) {
		this.accessDevice = accessDevice;
	}

	public String getAccessMode() {
		return this.accessMode;
	}

	public void setAccessMode(String accessMode) {
		this.accessMode = accessMode;
	}

	public String getAccessPort() {
		return this.accessPort;
	}

	public void setAccessPort(String accessPort) {
		this.accessPort = accessPort;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPassword() {
		return this.accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public Date getAppointmentTime() {
		return this.appointmentTime;
	}

	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getApproveResult() {
		return this.approveResult;
	}

	public void setApproveResult(String approveResult) {
		this.approveResult = approveResult;
	}

	public String getApproveTime() {
		return this.approveTime;
	}

	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}

	public String getBroadandWidth() {
		return this.broadandWidth;
	}

	public void setBroadandWidth(String broadandWidth) {
		this.broadandWidth = broadandWidth;
	}

	public String getBusinessCityD() {
		return this.businessCityD;
	}

	public void setBusinessCityD(String businessCityD) {
		this.businessCityD = businessCityD;
	}

	public String getBusinessCountyD() {
		return this.businessCountyD;
	}

	public void setBusinessCountyD(String businessCountyD) {
		this.businessCountyD = businessCountyD;
	}

	public String getBusinessIp() {
		return this.businessIp;
	}

	public void setBusinessIp(String businessIp) {
		this.businessIp = businessIp;
	}

	public String getBusinessIpType() {
		return this.businessIpType;
	}

	public void setBusinessIpType(String businessIpType) {
		this.businessIpType = businessIpType;
	}

	public String getBusinessProvinceD() {
		return this.businessProvinceD;
	}

	public void setBusinessProvinceD(String businessProvinceD) {
		this.businessProvinceD = businessProvinceD;
	}

	public String getBusinessState() {
		return this.businessState;
	}

	public void setBusinessState(String businessState) {
		this.businessState = businessState;
	}

	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getCellAddress() {
		return this.cellAddress;
	}

	public void setCellAddress(String cellAddress) {
		this.cellAddress = cellAddress;
	}

	public String getCellName() {
		return this.cellName;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public String getCoverageAreaFulldesc() {
		return this.coverageAreaFulldesc;
	}

	public void setCoverageAreaFulldesc(String coverageAreaFulldesc) {
		this.coverageAreaFulldesc = coverageAreaFulldesc;
	}

	public String getCpvlan() {
		return this.cpvlan;
	}

	public void setCpvlan(String cpvlan) {
		this.cpvlan = cpvlan;
	}

	public String getStbMac() {
		return stbMac;
	}

	public void setStbMac(String stbMac) {
		this.stbMac = stbMac;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCuid() {
		return this.cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getCvlan() {
		return this.cvlan;
	}

	public void setCvlan(String cvlan) {
		this.cvlan = cvlan;
	}

	public String getDataSource() {
		return this.dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getDistance() {
		return this.distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getFinishTime() {
		return this.finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getInstallAddress() {
		return this.installAddress;
	}

	public void setInstallAddress(String installAddress) {
		this.installAddress = installAddress;
	}

	public String getMealStandard() {
		return this.mealStandard;
	}

	public void setMealStandard(String mealStandard) {
		this.mealStandard = mealStandard;
	}

	public String getOntType() {
		return this.ontType;
	}

	public void setOntType(String ontType) {
		this.ontType = ontType;
	}

	public String getOnuIsOld() {
		return this.onuIsOld;
	}

	public void setOnuIsOld(String onuIsOld) {
		this.onuIsOld = onuIsOld;
	}

	public Date getOpenTime() {
		return this.openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getPreemptTime() {
		return this.preemptTime;
	}

	public void setPreemptTime(Date preemptTime) {
		this.preemptTime = preemptTime;
	}

	public String getProductAction() {
		return this.productAction;
	}

	public void setProductAction(String productAction) {
		this.productAction = productAction;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductStatus() {
		return this.productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getReOptOnu() {
		return this.reOptOnu;
	}

	public void setReOptOnu(String reOptOnu) {
		this.reOptOnu = reOptOnu;
	}

	public String getRelatedBmclasstypeCuid() {
		return this.relatedBmclasstypeCuid;
	}

	public void setRelatedBmclasstypeCuid(String relatedBmclasstypeCuid) {
		this.relatedBmclasstypeCuid = relatedBmclasstypeCuid;
	}

	public String getRelatedCoverageAddrCuid() {
		return this.relatedCoverageAddrCuid;
	}

	public void setRelatedCoverageAddrCuid(String relatedCoverageAddrCuid) {
		this.relatedCoverageAddrCuid = relatedCoverageAddrCuid;
	}

	public String getRelatedCustomerCuid() {
		return this.relatedCustomerCuid;
	}

	public void setRelatedCustomerCuid(String relatedCustomerCuid) {
		this.relatedCustomerCuid = relatedCustomerCuid;
	}

	public String getRelatedMaintainCuid() {
		return this.relatedMaintainCuid;
	}

	public void setRelatedMaintainCuid(String relatedMaintainCuid) {
		this.relatedMaintainCuid = relatedMaintainCuid;
	}

	public String getRelatedOrderCuid() {
		return this.relatedOrderCuid;
	}

	public void setRelatedOrderCuid(String relatedOrderCuid) {
		this.relatedOrderCuid = relatedOrderCuid;
	}

	public String getRelatedSheetCuid() {
		return this.relatedSheetCuid;
	}

	public void setRelatedSheetCuid(String relatedSheetCuid) {
		this.relatedSheetCuid = relatedSheetCuid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getResultDetail() {
		return this.resultDetail;
	}

	public void setResultDetail(String resultDetail) {
		this.resultDetail = resultDetail;
	}

	public String getSaleType() {
		return this.saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getSnCode() {
		return this.snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

	public String getSvlan() {
		return this.svlan;
	}

	public void setSvlan(String svlan) {
		this.svlan = svlan;
	}

	public String getVoiceNumber() {
		return this.voiceNumber;
	}

	public void setVoiceNumber(String voiceNumber) {
		this.voiceNumber = voiceNumber;
	}

	public String getVoicePassword() {
		return this.voicePassword;
	}

	public void setVoicePassword(String voicePassword) {
		this.voicePassword = voicePassword;
	}

	public String getVoiceRange() {
		return this.voiceRange;
	}

	public void setVoiceRange(String voiceRange) {
		this.voiceRange = voiceRange;
	}

	public String getVoiceUsername() {
		return this.voiceUsername;
	}

	public void setVoiceUsername(String voiceUsername) {
		this.voiceUsername = voiceUsername;
	}
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}

}