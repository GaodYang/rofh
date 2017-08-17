package com.boco.rofh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the AN_POS database table.
 * 
 */
@Entity
@Table(name="AN_POS")
@NamedQuery(name="AnPo.findAll", query="SELECT a FROM AnPos a")
public class AnPos implements Serializable {
	private static final long serialVersionUID = 1L;

	private String abbreviation;

	@Column(name="ACCESS_TYPE")
	private BigDecimal accessType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="BACK_NETWORK_TIME")
	private Date backNetworkTime;

	@Column(name="CAN_ALLOCATE_TO_USER")
	private BigDecimal canAllocateToUser;

	@Column(name="CONSTRUCT_UNIT")
	private String constructUnit;

	private String construction;

	@Column(name="COVER_INFO")
	private String coverInfo;

	@Column(name="COVER_USER_COUNT")
	private BigDecimal coverUserCount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime;

	@Column(name="CREATOR_NAME")
	private String creatorName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creattime;

	@Id
	private String cuid;

	@Column(name="DATA_QUALITY_PERSON")
	private String dataQualityPerson;

	@Column(name="DEV_CUID")
	private String devCuid;

	private String fdn;

	@Column(name="GT_VERSION")
	private BigDecimal gtVersion;

	@Column(name="HARD_VERSION")
	private String hardVersion;

	private String idn;

	@Column(name="INSTALL_LOCATION_TYPE")
	private BigDecimal installLocationType;

	@Column(name="IS_CLOSENET")
	private BigDecimal isClosenet;

	@Column(name="IS_PERMIT_SYS_DEL")
	private BigDecimal isPermitSysDel;

	private BigDecimal isdelete;

	@Column(name="LABEL_CN")
	private String labelCn;

	@Column(name="LABEL_DEV")
	private String labelDev;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_MODIFY_TIME")
	private Date lastModifyTime;

	@Column(name="LAST_MODIFY_USER")
	private String lastModifyUser;

	private BigDecimal latitude;

	@Column(name="LIVE_CYCLE")
	private BigDecimal liveCycle;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LIVEMODIFY_TIME")
	private Date livemodifyTime;

	private String location;

	private BigDecimal longitude;

	@Column(name="MAINT_MODE")
	private BigDecimal maintMode;

	@Column(name="MAINT_PERSON")
	private String maintPerson;

	private String model;

	@Column(name="NATIVE_EMS_NAME")
	private String nativeEmsName;

	private BigDecimal objectid;

	private BigDecimal ownership;

	@Column(name="OWNERSHIP_MAN")
	private String ownershipMan;

	@Column(name="POS_TYPE")
	private BigDecimal posType;

	private String preserver;

	private String ration;

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

	@Column(name="RELATED_PON_CUID")
	private String relatedPonCuid;

	@Column(name="RELATED_PORT_CUID")
	private String relatedPortCuid;

	@Column(name="RELATED_PORT2_CUID")
	private String relatedPort2Cuid;

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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="REPAIR_TIME")
	private Date repairTime;

	private String seqno;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SETUP_TIME")
	private Date setupTime;

	@Column(name="SOFT_VERSION")
	private String softVersion;

	@Column(name="STANDARD_NAME")
	private String standardName;

	@Column(name="USE_STATE")
	private BigDecimal useState;

	private String userlabel;

	public AnPos() {
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public BigDecimal getAccessType() {
		return this.accessType;
	}

	public void setAccessType(BigDecimal accessType) {
		this.accessType = accessType;
	}

	public Date getBackNetworkTime() {
		return this.backNetworkTime;
	}

	public void setBackNetworkTime(Date backNetworkTime) {
		this.backNetworkTime = backNetworkTime;
	}

	public BigDecimal getCanAllocateToUser() {
		return this.canAllocateToUser;
	}

	public void setCanAllocateToUser(BigDecimal canAllocateToUser) {
		this.canAllocateToUser = canAllocateToUser;
	}

	public String getConstructUnit() {
		return this.constructUnit;
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit;
	}

	public String getConstruction() {
		return this.construction;
	}

	public void setConstruction(String construction) {
		this.construction = construction;
	}

	public String getCoverInfo() {
		return this.coverInfo;
	}

	public void setCoverInfo(String coverInfo) {
		this.coverInfo = coverInfo;
	}

	public BigDecimal getCoverUserCount() {
		return this.coverUserCount;
	}

	public void setCoverUserCount(BigDecimal coverUserCount) {
		this.coverUserCount = coverUserCount;
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

	public String getDataQualityPerson() {
		return this.dataQualityPerson;
	}

	public void setDataQualityPerson(String dataQualityPerson) {
		this.dataQualityPerson = dataQualityPerson;
	}

	public String getDevCuid() {
		return this.devCuid;
	}

	public void setDevCuid(String devCuid) {
		this.devCuid = devCuid;
	}

	public String getFdn() {
		return this.fdn;
	}

	public void setFdn(String fdn) {
		this.fdn = fdn;
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

	public String getIdn() {
		return this.idn;
	}

	public void setIdn(String idn) {
		this.idn = idn;
	}

	public BigDecimal getInstallLocationType() {
		return this.installLocationType;
	}

	public void setInstallLocationType(BigDecimal installLocationType) {
		this.installLocationType = installLocationType;
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

	public BigDecimal getLiveCycle() {
		return this.liveCycle;
	}

	public void setLiveCycle(BigDecimal liveCycle) {
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

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getMaintMode() {
		return this.maintMode;
	}

	public void setMaintMode(BigDecimal maintMode) {
		this.maintMode = maintMode;
	}

	public String getMaintPerson() {
		return this.maintPerson;
	}

	public void setMaintPerson(String maintPerson) {
		this.maintPerson = maintPerson;
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

	public BigDecimal getOwnership() {
		return this.ownership;
	}

	public void setOwnership(BigDecimal ownership) {
		this.ownership = ownership;
	}

	public String getOwnershipMan() {
		return this.ownershipMan;
	}

	public void setOwnershipMan(String ownershipMan) {
		this.ownershipMan = ownershipMan;
	}

	public BigDecimal getPosType() {
		return this.posType;
	}

	public void setPosType(BigDecimal posType) {
		this.posType = posType;
	}

	public String getPreserver() {
		return this.preserver;
	}

	public void setPreserver(String preserver) {
		this.preserver = preserver;
	}

	public String getRation() {
		return this.ration;
	}

	public void setRation(String ration) {
		this.ration = ration;
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

	public String getRelatedPonCuid() {
		return this.relatedPonCuid;
	}

	public void setRelatedPonCuid(String relatedPonCuid) {
		this.relatedPonCuid = relatedPonCuid;
	}

	public String getRelatedPortCuid() {
		return this.relatedPortCuid;
	}

	public void setRelatedPortCuid(String relatedPortCuid) {
		this.relatedPortCuid = relatedPortCuid;
	}

	public String getRelatedPort2Cuid() {
		return this.relatedPort2Cuid;
	}

	public void setRelatedPort2Cuid(String relatedPort2Cuid) {
		this.relatedPort2Cuid = relatedPort2Cuid;
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

	public Date getRepairTime() {
		return this.repairTime;
	}

	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
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

	public BigDecimal getUseState() {
		return this.useState;
	}

	public void setUseState(BigDecimal useState) {
		this.useState = useState;
	}

	public String getUserlabel() {
		return this.userlabel;
	}

	public void setUserlabel(String userlabel) {
		this.userlabel = userlabel;
	}

}