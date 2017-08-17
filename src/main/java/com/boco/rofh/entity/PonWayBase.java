package com.boco.rofh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the ATTEMP_PON_WAY database table.
 * 
 */
@MappedSuperclass
public abstract class PonWayBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACCOUNT_END_TIME")
	private Date accountEndTime;

	@Column(name="ACCOUNT_NAME")
	private String accountName;

	@Column(name="ACCOUNT_PASSWORD")
	private String accountPassword;

	@Column(name="BAND_WIDTH")
	private String bandWidth;

	private String bandwidth;

	@Column(name="BUSINESS_ACCESS_TYPE")
	private String businessAccessType;

	@Column(name="BUSINESS_SLA_LEVLE")
	private BigDecimal businessSlaLevle;

	@Column(name="BUSINISS_TYPE_CUID")
	private String businissTypeCuid;

	@Column(name="C_VLAN")
	private String cVlan;

	@Column(name="CHECK_OPT_WAY_STATE")
	private BigDecimal checkOptWayState;

	@Column(name="CHECK_TOPO_STATE")
	private BigDecimal checkTopoState;

	@Column(name="CLIENT_PORT_MODEL")
	private BigDecimal clientPortModel;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime;

	@Id
	@GeneratedValue(generator = "IDUtils")  
    @GenericGenerator(name = "IDUtils", strategy = "com.boco.rofh.utils.IDUtils",  
    		parameters = { @Parameter(name = "tableName", value = "PON_WAY") })
	private String cuid;

	private String cvlan;

	@Column(name="DISPATCH_NAME")
	private String dispatchName;

	@Column(name="DOWN_BAND_WIDTH")
	private BigDecimal downBandWidth;

	@Column(name="FIBER_CAB_LOCATION")
	private String fiberCabLocation;

	@Column(name="GM_PORT")
	private String gmPort;

	@Column(name="GT_VERSION")
	private BigDecimal gtVersion;

	@Column(name="IS_WHOLE_JUMP")
	private BigDecimal isWholeJump;

	@Column(name="IS_WHOLE_ROUTE")
	private BigDecimal isWholeRoute;

	private BigDecimal isdelete;

	@Column(name="LABEL_CN")
	private String labelCn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_MODIFY_TIME")
	private Date lastModifyTime;

	@Column(name="LIGHT_PORT_CUID")
	private String lightPortCuid;

	@Column(name="LIGHT_UP_PORT_CUID")
	private String lightUpPortCuid;

	@Column(name="LINE_NAME")
	private String lineName;

	@Column(name="MAINTAIN_ORG")
	private String maintainOrg;

	@Column(name="NAME_CUID_A")
	private String nameCuidA;

	@Column(name="NAME_CUID_Z")
	private String nameCuidZ;

	@Column(name="NAME_TYPE_A")
	private String nameTypeA;

	@Column(name="NAME_TYPE_Z")
	private String nameTypeZ;

	@Column(name="NE_CUID_A")
	private String neCuidA;

	@Column(name="NE_CUID_Z")
	private String neCuidZ;

	@Column(name="NE_PORT_CUID_A")
	private String nePortCuidA;

	@Column(name="NE_PORT_CUID_Z")
	private String nePortCuidZ;

	@Column(name="NET_LEVLE")
	private BigDecimal netLevle;

	@Column(name="OBJECT_TYPE_CODE")
	private BigDecimal objectTypeCode;

	private BigDecimal objectid;

	@Column(name="OLT_PON_CARD_CUID")
	private String oltPonCardCuid;

	@Column(name="OLT_PON_PORT_CUID")
	private String oltPonPortCuid;

	@Column(name="OLT_UP_PORT_CUID")
	private String oltUpPortCuid;

	@Column(name="ONU_CHANGE_TYPE")
	private BigDecimal onuChangeType;

	@Column(name="ONU_IN_ADDR")
	private String onuInAddr;

	@Column(name="ONU_PORT_CUID")
	private String onuPortCuid;

	@Column(name="ONU_PORT_TYPE")
	private BigDecimal onuPortType;

	@Column(name="ONU_TEMPLATE_CUID")
	private String onuTemplateCuid;

	@Column(name="ONU_TO_POS_CUID")
	private String onuToPosCuid;

	@Column(name="ONU_TYPE_CUID")
	private String onuTypeCuid;

	@Column(name="ONU_UP_PORT_CUID")
	private String onuUpPortCuid;

	@Column(name="PON_BUSINISS_NAME")
	private String ponBusinissName;

	@Column(name="PON_WAY_STATE")
	private BigDecimal ponWayState;

	@Column(name="POS_TO_OLT_CUID")
	private String posToOltCuid;

	@Column(name="POS_TO_POS_CUID")
	private String posToPosCuid;

	@Column(name="RELATED_A_ODF_CUID")
	private String relatedAOdfCuid;

	@Column(name="RELATED_CFG_SERVICE_CUID")
	private String relatedCfgServiceCuid;

	@Column(name="RELATED_DISTRICT_CUID")
	private String relatedDistrictCuid;

	@Column(name="RELATED_FIBER_CAB_CUID")
	private String relatedFiberCabCuid;

	@Column(name="RELATED_LIGHT_CUID")
	private String relatedLightCuid;

	@Column(name="RELATED_NET_DOMAIN_CUID")
	private String relatedNetDomainCuid;

	@Column(name="RELATED_OLT_CUID")
	private String relatedOltCuid;

	@Column(name="RELATED_ONU_CUID")
	private String relatedOnuCuid;

	@Column(name="RELATED_OUT_PONWAY_CUID")
	private String relatedOutPonwayCuid;

	@Column(name="RELATED_PRODUCT_CUID")
	private String relatedProductCuid;

	@Column(name="RELATED_ROUTE_CUID")
	private String relatedRouteCuid;

	@Column(name="RELATED_SECOND_LIGHT_CUID")
	private String relatedSecondLightCuid;

	@Column(name="RELATED_SERVICE_CUID")
	private String relatedServiceCuid;

	@Column(name="RELATED_SHEET_ID")
	private String relatedSheetId;

	@Column(name="RELATED_Z_ODF_CUID")
	private String relatedZOdfCuid;

	private String remark;

	@Column(name="SCHEDULE_STATE")
	private BigDecimal scheduleState;

	@Column(name="SECOND_FIBER_CAB_CUID")
	private String secondFiberCabCuid;

	@Column(name="SECOND_FIBER_CAB_LOCATION")
	private String secondFiberCabLocation;

	@Column(name="SECOND_LIGHT_PORT_CUID")
	private String secondLightPortCuid;

	@Column(name="SECOND_LIGHT_UP_PORT_CUID")
	private String secondLightUpPortCuid;

	@Column(name="SERVICE_SLA_LEVLE")
	private BigDecimal serviceSlaLevle;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SERVICE_TIME")
	private Date serviceTime;

	@Column(name="SHARE_MODEL")
	private BigDecimal shareModel;

	@Column(name="SITE_CUID_A")
	private String siteCuidA;

	@Column(name="SITE_CUID_Z")
	private String siteCuidZ;

	@Column(name="SITE_DISTRICT_A")
	private String siteDistrictA;

	@Column(name="SITE_DISTRICT_Z")
	private String siteDistrictZ;

	@Column(name="SPECIAL_LINE_NUM")
	private String specialLineNum;

	private String svlan;

	@Column(name="SWITCH_NE_CUID")
	private String switchNeCuid;

	@Column(name="SWITCH_NE_PORT_CUID")
	private String switchNePortCuid;

	@Column(name="SWITCH_NE_Z_CUID")
	private String switchNeZCuid;

	@Column(name="SWITCH_PORT_Z_CUID")
	private String switchPortZCuid;

	@Column(name="SWITCH_PORT2")
	private String switchPort2;

	@Column(name="TARGET_LEVLE")
	private BigDecimal targetLevle;

	@Column(name="TEL_NUM")
	private String telNum;

	@Column(name="UP_BAND_WIDTH")
	private BigDecimal upBandWidth;

	@Column(name="USER_ADDRESS")
	private String userAddress;

	@Column(name="VOICE_PASSWORD")
	private String voicePassword;

	@Column(name="VOICE_USER_NAME")
	private String voiceUserName;

	@Column(name="VOICE_VLAN")
	private String voiceVlan;

	@Column(name="VP_CUID")
	private String vpCuid;

	public PonWayBase() {
	}

	public Date getAccountEndTime() {
		return this.accountEndTime;
	}

	public void setAccountEndTime(Date accountEndTime) {
		this.accountEndTime = accountEndTime;
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

	public String getBandWidth() {
		return this.bandWidth;
	}

	public void setBandWidth(String bandWidth) {
		this.bandWidth = bandWidth;
	}

	public String getBandwidth() {
		return this.bandwidth;
	}

	public void setBandwidth(String bandwidth) {
		this.bandwidth = bandwidth;
	}

	public String getBusinessAccessType() {
		return this.businessAccessType;
	}

	public void setBusinessAccessType(String businessAccessType) {
		this.businessAccessType = businessAccessType;
	}

	public BigDecimal getBusinessSlaLevle() {
		return this.businessSlaLevle;
	}

	public void setBusinessSlaLevle(BigDecimal businessSlaLevle) {
		this.businessSlaLevle = businessSlaLevle;
	}

	public String getBusinissTypeCuid() {
		return this.businissTypeCuid;
	}

	public void setBusinissTypeCuid(String businissTypeCuid) {
		this.businissTypeCuid = businissTypeCuid;
	}

	public String getCVlan() {
		return this.cVlan;
	}

	public void setCVlan(String cVlan) {
		this.cVlan = cVlan;
	}

	public BigDecimal getCheckOptWayState() {
		return this.checkOptWayState;
	}

	public void setCheckOptWayState(BigDecimal checkOptWayState) {
		this.checkOptWayState = checkOptWayState;
	}

	public BigDecimal getCheckTopoState() {
		return this.checkTopoState;
	}

	public void setCheckTopoState(BigDecimal checkTopoState) {
		this.checkTopoState = checkTopoState;
	}

	public BigDecimal getClientPortModel() {
		return this.clientPortModel;
	}

	public void setClientPortModel(BigDecimal clientPortModel) {
		this.clientPortModel = clientPortModel;
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

	public String getDispatchName() {
		return this.dispatchName;
	}

	public void setDispatchName(String dispatchName) {
		this.dispatchName = dispatchName;
	}

	public BigDecimal getDownBandWidth() {
		return this.downBandWidth;
	}

	public void setDownBandWidth(BigDecimal downBandWidth) {
		this.downBandWidth = downBandWidth;
	}

	public String getFiberCabLocation() {
		return this.fiberCabLocation;
	}

	public void setFiberCabLocation(String fiberCabLocation) {
		this.fiberCabLocation = fiberCabLocation;
	}

	public String getGmPort() {
		return this.gmPort;
	}

	public void setGmPort(String gmPort) {
		this.gmPort = gmPort;
	}

	public BigDecimal getGtVersion() {
		return this.gtVersion;
	}

	public void setGtVersion(BigDecimal gtVersion) {
		this.gtVersion = gtVersion;
	}

	public BigDecimal getIsWholeJump() {
		return this.isWholeJump;
	}

	public void setIsWholeJump(BigDecimal isWholeJump) {
		this.isWholeJump = isWholeJump;
	}

	public BigDecimal getIsWholeRoute() {
		return this.isWholeRoute;
	}

	public void setIsWholeRoute(BigDecimal isWholeRoute) {
		this.isWholeRoute = isWholeRoute;
	}

	public BigDecimal getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(BigDecimal isdelete) {
		this.isdelete = isdelete;
	}

	public String getLabelCn() {
		return this.labelCn;
	}

	public void setLabelCn(String labelCn) {
		this.labelCn = labelCn;
	}

	public Date getLastModifyTime() {
		return this.lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getLightPortCuid() {
		return this.lightPortCuid;
	}

	public void setLightPortCuid(String lightPortCuid) {
		this.lightPortCuid = lightPortCuid;
	}

	public String getLightUpPortCuid() {
		return this.lightUpPortCuid;
	}

	public void setLightUpPortCuid(String lightUpPortCuid) {
		this.lightUpPortCuid = lightUpPortCuid;
	}

	public String getLineName() {
		return this.lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getMaintainOrg() {
		return this.maintainOrg;
	}

	public void setMaintainOrg(String maintainOrg) {
		this.maintainOrg = maintainOrg;
	}

	public String getNameCuidA() {
		return this.nameCuidA;
	}

	public void setNameCuidA(String nameCuidA) {
		this.nameCuidA = nameCuidA;
	}

	public String getNameCuidZ() {
		return this.nameCuidZ;
	}

	public void setNameCuidZ(String nameCuidZ) {
		this.nameCuidZ = nameCuidZ;
	}

	public String getNameTypeA() {
		return this.nameTypeA;
	}

	public void setNameTypeA(String nameTypeA) {
		this.nameTypeA = nameTypeA;
	}

	public String getNameTypeZ() {
		return this.nameTypeZ;
	}

	public void setNameTypeZ(String nameTypeZ) {
		this.nameTypeZ = nameTypeZ;
	}

	public String getNeCuidA() {
		return this.neCuidA;
	}

	public void setNeCuidA(String neCuidA) {
		this.neCuidA = neCuidA;
	}

	public String getNeCuidZ() {
		return this.neCuidZ;
	}

	public void setNeCuidZ(String neCuidZ) {
		this.neCuidZ = neCuidZ;
	}

	public String getNePortCuidA() {
		return this.nePortCuidA;
	}

	public void setNePortCuidA(String nePortCuidA) {
		this.nePortCuidA = nePortCuidA;
	}

	public String getNePortCuidZ() {
		return this.nePortCuidZ;
	}

	public void setNePortCuidZ(String nePortCuidZ) {
		this.nePortCuidZ = nePortCuidZ;
	}

	public BigDecimal getNetLevle() {
		return this.netLevle;
	}

	public void setNetLevle(BigDecimal netLevle) {
		this.netLevle = netLevle;
	}

	public BigDecimal getObjectTypeCode() {
		return this.objectTypeCode;
	}

	public void setObjectTypeCode(BigDecimal objectTypeCode) {
		this.objectTypeCode = objectTypeCode;
	}

	public BigDecimal getObjectid() {
		return this.objectid;
	}

	public void setObjectid(BigDecimal objectid) {
		this.objectid = objectid;
	}

	public String getOltPonCardCuid() {
		return this.oltPonCardCuid;
	}

	public void setOltPonCardCuid(String oltPonCardCuid) {
		this.oltPonCardCuid = oltPonCardCuid;
	}

	public String getOltPonPortCuid() {
		return this.oltPonPortCuid;
	}

	public void setOltPonPortCuid(String oltPonPortCuid) {
		this.oltPonPortCuid = oltPonPortCuid;
	}

	public String getOltUpPortCuid() {
		return this.oltUpPortCuid;
	}

	public void setOltUpPortCuid(String oltUpPortCuid) {
		this.oltUpPortCuid = oltUpPortCuid;
	}

	public BigDecimal getOnuChangeType() {
		return this.onuChangeType;
	}

	public void setOnuChangeType(BigDecimal onuChangeType) {
		this.onuChangeType = onuChangeType;
	}

	public String getOnuInAddr() {
		return this.onuInAddr;
	}

	public void setOnuInAddr(String onuInAddr) {
		this.onuInAddr = onuInAddr;
	}

	public String getOnuPortCuid() {
		return this.onuPortCuid;
	}

	public void setOnuPortCuid(String onuPortCuid) {
		this.onuPortCuid = onuPortCuid;
	}

	public BigDecimal getOnuPortType() {
		return this.onuPortType;
	}

	public void setOnuPortType(BigDecimal onuPortType) {
		this.onuPortType = onuPortType;
	}

	public String getOnuTemplateCuid() {
		return this.onuTemplateCuid;
	}

	public void setOnuTemplateCuid(String onuTemplateCuid) {
		this.onuTemplateCuid = onuTemplateCuid;
	}

	public String getOnuToPosCuid() {
		return this.onuToPosCuid;
	}

	public void setOnuToPosCuid(String onuToPosCuid) {
		this.onuToPosCuid = onuToPosCuid;
	}

	public String getOnuTypeCuid() {
		return this.onuTypeCuid;
	}

	public void setOnuTypeCuid(String onuTypeCuid) {
		this.onuTypeCuid = onuTypeCuid;
	}

	public String getOnuUpPortCuid() {
		return this.onuUpPortCuid;
	}

	public void setOnuUpPortCuid(String onuUpPortCuid) {
		this.onuUpPortCuid = onuUpPortCuid;
	}

	public String getPonBusinissName() {
		return this.ponBusinissName;
	}

	public void setPonBusinissName(String ponBusinissName) {
		this.ponBusinissName = ponBusinissName;
	}

	public BigDecimal getPonWayState() {
		return this.ponWayState;
	}

	public void setPonWayState(BigDecimal ponWayState) {
		this.ponWayState = ponWayState;
	}

	public String getPosToOltCuid() {
		return this.posToOltCuid;
	}

	public void setPosToOltCuid(String posToOltCuid) {
		this.posToOltCuid = posToOltCuid;
	}

	public String getPosToPosCuid() {
		return this.posToPosCuid;
	}

	public void setPosToPosCuid(String posToPosCuid) {
		this.posToPosCuid = posToPosCuid;
	}

	public String getRelatedAOdfCuid() {
		return this.relatedAOdfCuid;
	}

	public void setRelatedAOdfCuid(String relatedAOdfCuid) {
		this.relatedAOdfCuid = relatedAOdfCuid;
	}

	public String getRelatedCfgServiceCuid() {
		return this.relatedCfgServiceCuid;
	}

	public void setRelatedCfgServiceCuid(String relatedCfgServiceCuid) {
		this.relatedCfgServiceCuid = relatedCfgServiceCuid;
	}

	public String getRelatedDistrictCuid() {
		return this.relatedDistrictCuid;
	}

	public void setRelatedDistrictCuid(String relatedDistrictCuid) {
		this.relatedDistrictCuid = relatedDistrictCuid;
	}

	public String getRelatedFiberCabCuid() {
		return this.relatedFiberCabCuid;
	}

	public void setRelatedFiberCabCuid(String relatedFiberCabCuid) {
		this.relatedFiberCabCuid = relatedFiberCabCuid;
	}

	public String getRelatedLightCuid() {
		return this.relatedLightCuid;
	}

	public void setRelatedLightCuid(String relatedLightCuid) {
		this.relatedLightCuid = relatedLightCuid;
	}

	public String getRelatedNetDomainCuid() {
		return this.relatedNetDomainCuid;
	}

	public void setRelatedNetDomainCuid(String relatedNetDomainCuid) {
		this.relatedNetDomainCuid = relatedNetDomainCuid;
	}

	public String getRelatedOltCuid() {
		return this.relatedOltCuid;
	}

	public void setRelatedOltCuid(String relatedOltCuid) {
		this.relatedOltCuid = relatedOltCuid;
	}

	public String getRelatedOnuCuid() {
		return this.relatedOnuCuid;
	}

	public void setRelatedOnuCuid(String relatedOnuCuid) {
		this.relatedOnuCuid = relatedOnuCuid;
	}

	public String getRelatedOutPonwayCuid() {
		return this.relatedOutPonwayCuid;
	}

	public void setRelatedOutPonwayCuid(String relatedOutPonwayCuid) {
		this.relatedOutPonwayCuid = relatedOutPonwayCuid;
	}

	public String getRelatedProductCuid() {
		return this.relatedProductCuid;
	}

	public void setRelatedProductCuid(String relatedProductCuid) {
		this.relatedProductCuid = relatedProductCuid;
	}

	public String getRelatedRouteCuid() {
		return this.relatedRouteCuid;
	}

	public void setRelatedRouteCuid(String relatedRouteCuid) {
		this.relatedRouteCuid = relatedRouteCuid;
	}

	public String getRelatedSecondLightCuid() {
		return this.relatedSecondLightCuid;
	}

	public void setRelatedSecondLightCuid(String relatedSecondLightCuid) {
		this.relatedSecondLightCuid = relatedSecondLightCuid;
	}

	public String getRelatedServiceCuid() {
		return this.relatedServiceCuid;
	}

	public void setRelatedServiceCuid(String relatedServiceCuid) {
		this.relatedServiceCuid = relatedServiceCuid;
	}

	public String getRelatedSheetId() {
		return this.relatedSheetId;
	}

	public void setRelatedSheetId(String relatedSheetId) {
		this.relatedSheetId = relatedSheetId;
	}

	public String getRelatedZOdfCuid() {
		return this.relatedZOdfCuid;
	}

	public void setRelatedZOdfCuid(String relatedZOdfCuid) {
		this.relatedZOdfCuid = relatedZOdfCuid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getScheduleState() {
		return this.scheduleState;
	}

	public void setScheduleState(BigDecimal scheduleState) {
		this.scheduleState = scheduleState;
	}

	public String getSecondFiberCabCuid() {
		return this.secondFiberCabCuid;
	}

	public void setSecondFiberCabCuid(String secondFiberCabCuid) {
		this.secondFiberCabCuid = secondFiberCabCuid;
	}

	public String getSecondFiberCabLocation() {
		return this.secondFiberCabLocation;
	}

	public void setSecondFiberCabLocation(String secondFiberCabLocation) {
		this.secondFiberCabLocation = secondFiberCabLocation;
	}

	public String getSecondLightPortCuid() {
		return this.secondLightPortCuid;
	}

	public void setSecondLightPortCuid(String secondLightPortCuid) {
		this.secondLightPortCuid = secondLightPortCuid;
	}

	public String getSecondLightUpPortCuid() {
		return this.secondLightUpPortCuid;
	}

	public void setSecondLightUpPortCuid(String secondLightUpPortCuid) {
		this.secondLightUpPortCuid = secondLightUpPortCuid;
	}

	public BigDecimal getServiceSlaLevle() {
		return this.serviceSlaLevle;
	}

	public void setServiceSlaLevle(BigDecimal serviceSlaLevle) {
		this.serviceSlaLevle = serviceSlaLevle;
	}

	public Date getServiceTime() {
		return this.serviceTime;
	}

	public void setServiceTime(Date serviceTime) {
		this.serviceTime = serviceTime;
	}

	public BigDecimal getShareModel() {
		return this.shareModel;
	}

	public void setShareModel(BigDecimal shareModel) {
		this.shareModel = shareModel;
	}

	public String getSiteCuidA() {
		return this.siteCuidA;
	}

	public void setSiteCuidA(String siteCuidA) {
		this.siteCuidA = siteCuidA;
	}

	public String getSiteCuidZ() {
		return this.siteCuidZ;
	}

	public void setSiteCuidZ(String siteCuidZ) {
		this.siteCuidZ = siteCuidZ;
	}

	public String getSiteDistrictA() {
		return this.siteDistrictA;
	}

	public void setSiteDistrictA(String siteDistrictA) {
		this.siteDistrictA = siteDistrictA;
	}

	public String getSiteDistrictZ() {
		return this.siteDistrictZ;
	}

	public void setSiteDistrictZ(String siteDistrictZ) {
		this.siteDistrictZ = siteDistrictZ;
	}

	public String getSpecialLineNum() {
		return this.specialLineNum;
	}

	public void setSpecialLineNum(String specialLineNum) {
		this.specialLineNum = specialLineNum;
	}

	public String getSvlan() {
		return this.svlan;
	}

	public void setSvlan(String svlan) {
		this.svlan = svlan;
	}

	public String getSwitchNeCuid() {
		return this.switchNeCuid;
	}

	public void setSwitchNeCuid(String switchNeCuid) {
		this.switchNeCuid = switchNeCuid;
	}

	public String getSwitchNePortCuid() {
		return this.switchNePortCuid;
	}

	public void setSwitchNePortCuid(String switchNePortCuid) {
		this.switchNePortCuid = switchNePortCuid;
	}

	public String getSwitchNeZCuid() {
		return this.switchNeZCuid;
	}

	public void setSwitchNeZCuid(String switchNeZCuid) {
		this.switchNeZCuid = switchNeZCuid;
	}

	public String getSwitchPortZCuid() {
		return this.switchPortZCuid;
	}

	public void setSwitchPortZCuid(String switchPortZCuid) {
		this.switchPortZCuid = switchPortZCuid;
	}

	public String getSwitchPort2() {
		return this.switchPort2;
	}

	public void setSwitchPort2(String switchPort2) {
		this.switchPort2 = switchPort2;
	}

	public BigDecimal getTargetLevle() {
		return this.targetLevle;
	}

	public void setTargetLevle(BigDecimal targetLevle) {
		this.targetLevle = targetLevle;
	}

	public String getTelNum() {
		return this.telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public BigDecimal getUpBandWidth() {
		return this.upBandWidth;
	}

	public void setUpBandWidth(BigDecimal upBandWidth) {
		this.upBandWidth = upBandWidth;
	}

	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getVoicePassword() {
		return this.voicePassword;
	}

	public void setVoicePassword(String voicePassword) {
		this.voicePassword = voicePassword;
	}

	public String getVoiceUserName() {
		return this.voiceUserName;
	}

	public void setVoiceUserName(String voiceUserName) {
		this.voiceUserName = voiceUserName;
	}

	public String getVoiceVlan() {
		return this.voiceVlan;
	}

	public void setVoiceVlan(String voiceVlan) {
		this.voiceVlan = voiceVlan;
	}

	public String getVpCuid() {
		return this.vpCuid;
	}

	public void setVpCuid(String vpCuid) {
		this.vpCuid = vpCuid;
	}

}