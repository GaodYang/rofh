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
 * The persistent class for the ATTEMP_AN_ONU database table.
 * 
 */
@MappedSuperclass
public abstract class AnOnuBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String abbreviation;

	@Column(name="ACCESS_TYPE")
	private String accessType;

	@Column(name="AUTH_TYPE")
	private BigDecimal authType;

	@Temporal(TemporalType.TIME)
	@Column(name="BACK_NETWORK_TIME")
	private Date backNetworkTime;

	private BigDecimal capacity;

	@Temporal(TemporalType.TIME)
	@Column(name="CREATE_TIME")
	private Date createTime;

	@Column(name="CREATOR_NAME")
	private String creatorName;

	@Temporal(TemporalType.TIME)
	private Date creattime;

	@Id
	@GeneratedValue(generator = "IDUtils")  
    @GenericGenerator(name = "IDUtils", strategy = "com.boco.rofh.utils.IDUtils",  
    		parameters = { @Parameter(name = "tableName", value = "AN_ONU") })
	private String cuid;

	@Column(name="DEV_CUID")
	private String devCuid;

	@Column(name="DEV_IP")
	private String devIp;

	private String fdn;

	private String fttx;

	@Column(name="GT_VERSION")
	private BigDecimal gtVersion;

	@Column(name="HARD_VERSION")
	private String hardVersion;

	@Column(name="IS_CLOSENET")
	private BigDecimal isClosenet;

	@Column(name="IS_PERMIT_SYS_DEL")
	private BigDecimal isPermitSysDel;

	private BigDecimal isdelete;

	@Column(name="LABEL_CN")
	private String labelCn;

	@Column(name="LABEL_DEV")
	private String labelDev;

	@Temporal(TemporalType.TIME)
	@Column(name="LAST_MODIFY_TIME")
	private Date lastModifyTime;

	@Column(name="LAST_MODIFY_USER")
	private String lastModifyUser;

	private BigDecimal latitude;

	@Column(name="LIVE_CYCLE")
	private String liveCycle;

	@Temporal(TemporalType.TIME)
	@Column(name="LIVEMODIFY_TIME")
	private Date livemodifyTime;

	private String location;

	private String logicid;

	private BigDecimal longitude;

	@Column(name="MAC_ADDR")
	private String macAddr;

	@Column(name="MAINT_MODE")
	private String maintMode;

	private String model;

	@Column(name="NATIVE_EMS_NAME")
	private String nativeEmsName;

	private BigDecimal objectid;

	@Column(name="ONU_ID")
	private String onuId;

	@Column(name="ONU_RTT")
	private BigDecimal onuRtt;

	@Column(name="ONU_TYPE")
	private String onuType;

	@Column(name="OPER_STATE")
	private String operState;

	private String ownership;

	@Column(name="OWNERSHIP_MAN")
	private String ownershipMan;

	private String password;

	private String preserver;

	@Column(name="REAL_LATITUDE")
	private BigDecimal realLatitude;

	@Column(name="REAL_LONGITUDE")
	private BigDecimal realLongitude;

	@Column(name="RELATED_ACCESS_POINT")
	private String relatedAccessPoint;

	@Column(name="RELATED_CAB_CUID")
	private String relatedCabCuid;

	@Column(name="RELATED_DISTRICT_CUID")
	private String relatedDistrictCuid;

	@Column(name="RELATED_EMS_CUID")
	private String relatedEmsCuid;

	@Column(name="RELATED_OLT_CUID")
	private String relatedOltCuid;

	@Column(name="RELATED_OLT_PORT_CUID")
	private String relatedOltPortCuid;

	@Column(name="RELATED_PON_PORT_CUID")
	private String relatedPonPortCuid;

	@Column(name="RELATED_POS_CUID")
	private String relatedPosCuid;

	@Column(name="RELATED_POS_PORT_CUID")
	private String relatedPosPortCuid;

	@Column(name="RELATED_PROJECT_CUID")
	private String relatedProjectCuid;

	@Column(name="RELATED_ROOM_CUID")
	private String relatedRoomCuid;


	@Column(name="RELATED_SITE_CUID")
	private String relatedSiteCuid;

	@Column(name="RELATED_TEMPLATE_NAME")
	private String relatedTemplateName;

	@Column(name="RELATED_UPNE_CUID")
	private String relatedUpneCuid;

	@Column(name="RELATED_UPNE_PORT_CUID")
	private String relatedUpnePortCuid;

	@Column(name="RELATED_VENDOR_CUID")
	private String relatedVendorCuid;

	private String remark;

	private String seqno;

	@Temporal(TemporalType.TIME)
	@Column(name="SETUP_TIME")
	private Date setupTime;

	@Column(name="SOFT_VERSION")
	private String softVersion;

	@Column(name="STANDARD_NAME")
	private String standardName;

	private BigDecimal state;

	@Column(name="USE_STATE")
	private String useState;

	private String userlabel;

	public AnOnuBase() {
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getAccessType() {
		return this.accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public BigDecimal getAuthType() {
		return this.authType;
	}

	public void setAuthType(BigDecimal authType) {
		this.authType = authType;
	}

	public Date getBackNetworkTime() {
		return this.backNetworkTime;
	}

	public void setBackNetworkTime(Date backNetworkTime) {
		this.backNetworkTime = backNetworkTime;
	}

	public BigDecimal getCapacity() {
		return this.capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreatorName() {
		return this.creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Date getCreattime() {
		return this.creattime;
	}

	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}

	public String getCuid() {
		return this.cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getDevCuid() {
		return this.devCuid;
	}

	public void setDevCuid(String devCuid) {
		this.devCuid = devCuid;
	}

	public String getDevIp() {
		return this.devIp;
	}

	public void setDevIp(String devIp) {
		this.devIp = devIp;
	}

	public String getFdn() {
		return this.fdn;
	}

	public void setFdn(String fdn) {
		this.fdn = fdn;
	}

	public String getFttx() {
		return this.fttx;
	}

	public void setFttx(String fttx) {
		this.fttx = fttx;
	}

	public BigDecimal getGtVersion() {
		return this.gtVersion;
	}

	public void setGtVersion(BigDecimal gtVersion) {
		this.gtVersion = gtVersion;
	}

	public String getHardVersion() {
		return this.hardVersion;
	}

	public void setHardVersion(String hardVersion) {
		this.hardVersion = hardVersion;
	}

	public BigDecimal getIsClosenet() {
		return this.isClosenet;
	}

	public void setIsClosenet(BigDecimal isClosenet) {
		this.isClosenet = isClosenet;
	}

	public BigDecimal getIsPermitSysDel() {
		return this.isPermitSysDel;
	}

	public void setIsPermitSysDel(BigDecimal isPermitSysDel) {
		this.isPermitSysDel = isPermitSysDel;
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

	public String getLabelDev() {
		return this.labelDev;
	}

	public void setLabelDev(String labelDev) {
		this.labelDev = labelDev;
	}

	public Date getLastModifyTime() {
		return this.lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getLastModifyUser() {
		return this.lastModifyUser;
	}

	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public String getLiveCycle() {
		return this.liveCycle;
	}

	public void setLiveCycle(String liveCycle) {
		this.liveCycle = liveCycle;
	}

	public Date getLivemodifyTime() {
		return this.livemodifyTime;
	}

	public void setLivemodifyTime(Date livemodifyTime) {
		this.livemodifyTime = livemodifyTime;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLogicid() {
		return this.logicid;
	}

	public void setLogicid(String logicid) {
		this.logicid = logicid;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getMacAddr() {
		return this.macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public String getMaintMode() {
		return this.maintMode;
	}

	public void setMaintMode(String maintMode) {
		this.maintMode = maintMode;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNativeEmsName() {
		return this.nativeEmsName;
	}

	public void setNativeEmsName(String nativeEmsName) {
		this.nativeEmsName = nativeEmsName;
	}

	public BigDecimal getObjectid() {
		return this.objectid;
	}

	public void setObjectid(BigDecimal objectid) {
		this.objectid = objectid;
	}

	public String getOnuId() {
		return this.onuId;
	}

	public void setOnuId(String onuId) {
		this.onuId = onuId;
	}

	public BigDecimal getOnuRtt() {
		return this.onuRtt;
	}

	public void setOnuRtt(BigDecimal onuRtt) {
		this.onuRtt = onuRtt;
	}

	public String getOnuType() {
		return this.onuType;
	}

	public void setOnuType(String onuType) {
		this.onuType = onuType;
	}

	public String getOperState() {
		return this.operState;
	}

	public void setOperState(String operState) {
		this.operState = operState;
	}

	public String getOwnership() {
		return this.ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getOwnershipMan() {
		return this.ownershipMan;
	}

	public void setOwnershipMan(String ownershipMan) {
		this.ownershipMan = ownershipMan;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPreserver() {
		return this.preserver;
	}

	public void setPreserver(String preserver) {
		this.preserver = preserver;
	}

	public BigDecimal getRealLatitude() {
		return this.realLatitude;
	}

	public void setRealLatitude(BigDecimal realLatitude) {
		this.realLatitude = realLatitude;
	}

	public BigDecimal getRealLongitude() {
		return this.realLongitude;
	}

	public void setRealLongitude(BigDecimal realLongitude) {
		this.realLongitude = realLongitude;
	}

	public String getRelatedAccessPoint() {
		return this.relatedAccessPoint;
	}

	public void setRelatedAccessPoint(String relatedAccessPoint) {
		this.relatedAccessPoint = relatedAccessPoint;
	}

	public String getRelatedCabCuid() {
		return this.relatedCabCuid;
	}

	public void setRelatedCabCuid(String relatedCabCuid) {
		this.relatedCabCuid = relatedCabCuid;
	}

	public String getRelatedDistrictCuid() {
		return this.relatedDistrictCuid;
	}

	public void setRelatedDistrictCuid(String relatedDistrictCuid) {
		this.relatedDistrictCuid = relatedDistrictCuid;
	}

	public String getRelatedEmsCuid() {
		return this.relatedEmsCuid;
	}

	public void setRelatedEmsCuid(String relatedEmsCuid) {
		this.relatedEmsCuid = relatedEmsCuid;
	}

	public String getRelatedOltCuid() {
		return this.relatedOltCuid;
	}

	public void setRelatedOltCuid(String relatedOltCuid) {
		this.relatedOltCuid = relatedOltCuid;
	}

	public String getRelatedOltPortCuid() {
		return this.relatedOltPortCuid;
	}

	public void setRelatedOltPortCuid(String relatedOltPortCuid) {
		this.relatedOltPortCuid = relatedOltPortCuid;
	}

	public String getRelatedPonPortCuid() {
		return this.relatedPonPortCuid;
	}

	public void setRelatedPonPortCuid(String relatedPonPortCuid) {
		this.relatedPonPortCuid = relatedPonPortCuid;
	}

	public String getRelatedPosCuid() {
		return this.relatedPosCuid;
	}

	public void setRelatedPosCuid(String relatedPosCuid) {
		this.relatedPosCuid = relatedPosCuid;
	}

	public String getRelatedPosPortCuid() {
		return this.relatedPosPortCuid;
	}

	public void setRelatedPosPortCuid(String relatedPosPortCuid) {
		this.relatedPosPortCuid = relatedPosPortCuid;
	}

	public String getRelatedProjectCuid() {
		return this.relatedProjectCuid;
	}

	public void setRelatedProjectCuid(String relatedProjectCuid) {
		this.relatedProjectCuid = relatedProjectCuid;
	}

	public String getRelatedRoomCuid() {
		return this.relatedRoomCuid;
	}

	public void setRelatedRoomCuid(String relatedRoomCuid) {
		this.relatedRoomCuid = relatedRoomCuid;
	}

	public String getRelatedSiteCuid() {
		return this.relatedSiteCuid;
	}

	public void setRelatedSiteCuid(String relatedSiteCuid) {
		this.relatedSiteCuid = relatedSiteCuid;
	}

	public String getRelatedTemplateName() {
		return this.relatedTemplateName;
	}

	public void setRelatedTemplateName(String relatedTemplateName) {
		this.relatedTemplateName = relatedTemplateName;
	}

	public String getRelatedUpneCuid() {
		return this.relatedUpneCuid;
	}

	public void setRelatedUpneCuid(String relatedUpneCuid) {
		this.relatedUpneCuid = relatedUpneCuid;
	}

	public String getRelatedUpnePortCuid() {
		return this.relatedUpnePortCuid;
	}

	public void setRelatedUpnePortCuid(String relatedUpnePortCuid) {
		this.relatedUpnePortCuid = relatedUpnePortCuid;
	}

	public String getRelatedVendorCuid() {
		return this.relatedVendorCuid;
	}

	public void setRelatedVendorCuid(String relatedVendorCuid) {
		this.relatedVendorCuid = relatedVendorCuid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSeqno() {
		return this.seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public Date getSetupTime() {
		return this.setupTime;
	}

	public void setSetupTime(Date setupTime) {
		this.setupTime = setupTime;
	}

	public String getSoftVersion() {
		return this.softVersion;
	}

	public void setSoftVersion(String softVersion) {
		this.softVersion = softVersion;
	}

	public String getStandardName() {
		return this.standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public BigDecimal getState() {
		return this.state;
	}

	public void setState(BigDecimal state) {
		this.state = state;
	}

	public String getUseState() {
		return this.useState;
	}

	public void setUseState(String useState) {
		this.useState = useState;
	}

	public String getUserlabel() {
		return this.userlabel;
	}

	public void setUserlabel(String userlabel) {
		this.userlabel = userlabel;
	}

}