package com.boco.rofh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the T_ROFH_ORDER database table.
 * 
 */
@Entity
@Table(name="T_ROFH_ORDER")
public class RofhOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACCEPT_TIME_LIMIT")
	private Date acceptTimeLimit;

	private String attachment;

	@Column(name="BUILDING_SECTION")
	private String buildingSection;

	@Column(name="BUSINESS_RANGE")
	private String businessRange;

	@Column(name="BUSINESS_TYPE")
	private String businessType;

	@Column(name="CHANCE_ID")
	private String chanceId;

	@Column(name="CHANGE_MODEL")
	private String changeModel;

	@Column(name="CLAIM_TIME")
	private String claimTime;

	@Column(name="CONTRACT_ATTACHMENT")
	private String contractAttachment;

	@Column(name="CONTRACT_NUMBER")
	private String contractNumber;

	@Column(name="CRM_SHEET_NO")
	private String crmSheetNo;

	@Column(name="CRM_TASK_ID")
	private String crmTaskId;

	@Id
	@GeneratedValue(generator = "IDUtils")  
    @GenericGenerator(name = "IDUtils", strategy = "com.boco.rofh.utils.IDUtils",  
    		parameters = { @Parameter(name = "tableName", value = "T_ROFH_ORDER") })  
	private String cuid;

	@Column(name="CUST_MANAGER_NAME")
	private String custManagerName;

	@Column(name="GROUP_CODE")
	private String groupCode;

	@Column(name="IMPORT_LEVEL")
	private String importLevel;

	@Column(name="INDUSTRY_MANAGER")
	private String industryManager;

	@Column(name="INDUSTRY_MANAGER_TEL")
	private String industryManagerTel;

	@Column(name="IS_DISPATCH_PROVINCE")
	private String isDispatchProvince;

	private String lessor;

	@Column(name="LIMIT_TIME")
	private String limitTime;

	private String magicboxflag;

	@Column(name="MANAGER_CODE")
	private String managerCode;

	@Column(name="MANAGER_EMAIL")
	private String managerEmail;

	@Column(name="MANAGER_LINK_PHONE")
	private String managerLinkPhone;

	@Column(name="MANAGER_SPACE_CUID")
	private String managerSpaceCuid;

	@Column(name="NEED_DESC")
	private String needDesc;

	@Column(name="OWNER_AREA")
	private String ownerArea;

	@Column(name="PRODUCT_NUM")
	private String productNum;

	@Column(name="PROGRAM_NUM")
	private String programNum;

	@Column(name="PROJECT_BUDGE")
	private String projectBudge;

	@Column(name="PROJECT_NAME")
	private String projectName;

	@Column(name="PROJECT_PRICE")
	private String projectPrice;

	@Column(name="REL_GROUP_CUSTOMER_CUID")
	private String relGroupCustomerCuid;

	@Column(name="REL_PROD_TYPE_CUID")
	private String relProdTypeCuid;

	@Column(name="REL_SURVEY_SHEET_ID")
	private String relSurveySheetId;

	@Column(name="RELATED_PLAN_CUID")
	private String relatedPlanCuid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="RENEW_TIME")
	private Date renewTime;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	@Column(name="SHEET_TYPE")
	private String sheetType;

	@Column(name="SIGN_TIME")
	private String signTime;

	private String stbid;

	@Column(name="SUPPORT_APP")
	private String supportApp;

	@Column(name="TEC_SUPPORTER")
	private String tecSupporter;

	@Column(name="TEC_SUPPORTER_TEL")
	private String tecSupporterTel;

	private String title;

	@Column(name="TRANS_BUSINESS")
	private String transBusiness;

	public RofhOrder() {
	}

	public Date getAcceptTimeLimit() {
		return this.acceptTimeLimit;
	}

	public void setAcceptTimeLimit(Date acceptTimeLimit) {
		this.acceptTimeLimit = acceptTimeLimit;
	}

	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getBuildingSection() {
		return this.buildingSection;
	}

	public void setBuildingSection(String buildingSection) {
		this.buildingSection = buildingSection;
	}

	public String getBusinessRange() {
		return this.businessRange;
	}

	public void setBusinessRange(String businessRange) {
		this.businessRange = businessRange;
	}

	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getChanceId() {
		return this.chanceId;
	}

	public void setChanceId(String chanceId) {
		this.chanceId = chanceId;
	}

	public String getChangeModel() {
		return this.changeModel;
	}

	public void setChangeModel(String changeModel) {
		this.changeModel = changeModel;
	}

	public String getClaimTime() {
		return this.claimTime;
	}

	public void setClaimTime(String claimTime) {
		this.claimTime = claimTime;
	}

	public String getContractAttachment() {
		return this.contractAttachment;
	}

	public void setContractAttachment(String contractAttachment) {
		this.contractAttachment = contractAttachment;
	}

	public String getContractNumber() {
		return this.contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getCrmSheetNo() {
		return this.crmSheetNo;
	}

	public void setCrmSheetNo(String crmSheetNo) {
		this.crmSheetNo = crmSheetNo;
	}

	public String getCrmTaskId() {
		return this.crmTaskId;
	}

	public void setCrmTaskId(String crmTaskId) {
		this.crmTaskId = crmTaskId;
	}

	public String getCuid() {
		return this.cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getCustManagerName() {
		return this.custManagerName;
	}

	public void setCustManagerName(String custManagerName) {
		this.custManagerName = custManagerName;
	}

	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getImportLevel() {
		return this.importLevel;
	}

	public void setImportLevel(String importLevel) {
		this.importLevel = importLevel;
	}

	public String getIndustryManager() {
		return this.industryManager;
	}

	public void setIndustryManager(String industryManager) {
		this.industryManager = industryManager;
	}

	public String getIndustryManagerTel() {
		return this.industryManagerTel;
	}

	public void setIndustryManagerTel(String industryManagerTel) {
		this.industryManagerTel = industryManagerTel;
	}

	public String getIsDispatchProvince() {
		return this.isDispatchProvince;
	}

	public void setIsDispatchProvince(String isDispatchProvince) {
		this.isDispatchProvince = isDispatchProvince;
	}

	public String getLessor() {
		return this.lessor;
	}

	public void setLessor(String lessor) {
		this.lessor = lessor;
	}

	public String getLimitTime() {
		return this.limitTime;
	}

	public void setLimitTime(String limitTime) {
		this.limitTime = limitTime;
	}

	public String getMagicboxflag() {
		return this.magicboxflag;
	}

	public void setMagicboxflag(String magicboxflag) {
		this.magicboxflag = magicboxflag;
	}

	public String getManagerCode() {
		return this.managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getManagerEmail() {
		return this.managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getManagerLinkPhone() {
		return this.managerLinkPhone;
	}

	public void setManagerLinkPhone(String managerLinkPhone) {
		this.managerLinkPhone = managerLinkPhone;
	}

	public String getManagerSpaceCuid() {
		return this.managerSpaceCuid;
	}

	public void setManagerSpaceCuid(String managerSpaceCuid) {
		this.managerSpaceCuid = managerSpaceCuid;
	}

	public String getNeedDesc() {
		return this.needDesc;
	}

	public void setNeedDesc(String needDesc) {
		this.needDesc = needDesc;
	}

	public String getOwnerArea() {
		return this.ownerArea;
	}

	public void setOwnerArea(String ownerArea) {
		this.ownerArea = ownerArea;
	}

	public String getProductNum() {
		return this.productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getProgramNum() {
		return this.programNum;
	}

	public void setProgramNum(String programNum) {
		this.programNum = programNum;
	}

	public String getProjectBudge() {
		return this.projectBudge;
	}

	public void setProjectBudge(String projectBudge) {
		this.projectBudge = projectBudge;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectPrice() {
		return this.projectPrice;
	}

	public void setProjectPrice(String projectPrice) {
		this.projectPrice = projectPrice;
	}

	public String getRelGroupCustomerCuid() {
		return this.relGroupCustomerCuid;
	}

	public void setRelGroupCustomerCuid(String relGroupCustomerCuid) {
		this.relGroupCustomerCuid = relGroupCustomerCuid;
	}

	public String getRelProdTypeCuid() {
		return this.relProdTypeCuid;
	}

	public void setRelProdTypeCuid(String relProdTypeCuid) {
		this.relProdTypeCuid = relProdTypeCuid;
	}

	public String getRelSurveySheetId() {
		return this.relSurveySheetId;
	}

	public void setRelSurveySheetId(String relSurveySheetId) {
		this.relSurveySheetId = relSurveySheetId;
	}

	public String getRelatedPlanCuid() {
		return this.relatedPlanCuid;
	}

	public void setRelatedPlanCuid(String relatedPlanCuid) {
		this.relatedPlanCuid = relatedPlanCuid;
	}

	public Date getRenewTime() {
		return this.renewTime;
	}

	public void setRenewTime(Date renewTime) {
		this.renewTime = renewTime;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getSheetType() {
		return this.sheetType;
	}

	public void setSheetType(String sheetType) {
		this.sheetType = sheetType;
	}

	public String getSignTime() {
		return this.signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getStbid() {
		return this.stbid;
	}

	public void setStbid(String stbid) {
		this.stbid = stbid;
	}

	public String getSupportApp() {
		return this.supportApp;
	}

	public void setSupportApp(String supportApp) {
		this.supportApp = supportApp;
	}

	public String getTecSupporter() {
		return this.tecSupporter;
	}

	public void setTecSupporter(String tecSupporter) {
		this.tecSupporter = tecSupporter;
	}

	public String getTecSupporterTel() {
		return this.tecSupporterTel;
	}

	public void setTecSupporterTel(String tecSupporterTel) {
		this.tecSupporterTel = tecSupporterTel;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTransBusiness() {
		return this.transBusiness;
	}

	public void setTransBusiness(String transBusiness) {
		this.transBusiness = transBusiness;
	}

}