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
 * The persistent class for the AN_WBS database table.
 * 
 */
@Entity
@Table(name="AN_WBS")
@NamedQuery(name="AnWb.findAll", query="SELECT a FROM AnWbs a")
public class AnWbs implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ALL_BANDWIDTH")
	private String allBandwidth;

	@Column(name="BUSINESS_VLAN")
	private String businessVlan;

	@Column(name="COVER_INFO")
	private String coverInfo;

	@Column(name="COVER_USER_COUNT")
	private BigDecimal coverUserCount;

	@Temporal(TemporalType.TIME)
	@Column(name="CREATE_TIME")
	private Date createTime;

	@Column(name="CREATOR_NAME")
	private String creatorName;

	@Id
	private String cuid;

	@Column(name="DEV_IP")
	private String devIp;

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
	private BigDecimal liveCycle;

	private String location;

	private BigDecimal longitude;

	@Column(name="MAC_ADDR")
	private String macAddr;

	@Column(name="MAINT_MODE")
	private BigDecimal maintMode;

	@Column(name="MANAGEMENT_VLAN")
	private String managementVlan;

	private String model;

	private BigDecimal objectid;

	private BigDecimal ownership;

	@Column(name="OWNERSHIP_MAN")
	private String ownershipMan;

	@Column(name="PORT_NUM")
	private BigDecimal portNum;

	private String preserver;

	@Column(name="RELATED_BRAS_CUID")
	private String relatedBrasCuid;

	@Column(name="RELATED_BRAS_PTP")
	private String relatedBrasPtp;

	@Column(name="RELATED_BTS_CUID")
	private String relatedBtsCuid;

	@Column(name="RELATED_DISTRICT_CUID")
	private String relatedDistrictCuid;

	@Column(name="RELATED_PTP_CUID")
	private String relatedPtpCuid;

	@Column(name="RELATED_TRANS_CUID")
	private String relatedTransCuid;

	@Column(name="RELATED_VENDOR_CUID")
	private String relatedVendorCuid;

	private String remark;

	@Temporal(TemporalType.TIME)
	@Column(name="SETUP_TIME")
	private Date setupTime;

	@Column(name="SOFT_VERSION")
	private String softVersion;

	@Column(name="STANDARD_NAME")
	private String standardName;

	@Column(name="USE_BANDWIDTH")
	private String useBandwidth;

	@Column(name="USED_PORT_NUM")
	private BigDecimal usedPortNum;

	@Column(name="WIRE_CHANNEL_1")
	private BigDecimal wireChannel1;

	@Column(name="WIRE_CHANNEL_2")
	private BigDecimal wireChannel2;

	@Column(name="WIRE_CHANNEL_3")
	private BigDecimal wireChannel3;

	@Column(name="WIRE_DIRECTION_1")
	private BigDecimal wireDirection1;

	@Column(name="WIRE_DIRECTION_2")
	private BigDecimal wireDirection2;

	@Column(name="WIRE_DIRECTION_3")
	private BigDecimal wireDirection3;

	@Column(name="WIRE_FREQUENCY_1")
	private BigDecimal wireFrequency1;

	@Column(name="WIRE_FREQUENCY_2")
	private BigDecimal wireFrequency2;

	@Column(name="WIRE_FREQUENCY_3")
	private BigDecimal wireFrequency3;

	@Column(name="WIRE_LABEL_CN_1")
	private String wireLabelCn1;

	@Column(name="WIRE_LABEL_CN_2")
	private String wireLabelCn2;

	@Column(name="WIRE_LABEL_CN_3")
	private String wireLabelCn3;

	public AnWbs() {
	}

	public String getAllBandwidth() {
		return this.allBandwidth;
	}

	public void setAllBandwidth(String allBandwidth) {
		this.allBandwidth = allBandwidth;
	}

	public String getBusinessVlan() {
		return this.businessVlan;
	}

	public void setBusinessVlan(String businessVlan) {
		this.businessVlan = businessVlan;
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

	public String getCuid() {
		return this.cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getDevIp() {
		return this.devIp;
	}

	public void setDevIp(String devIp) {
		this.devIp = devIp;
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

	public String getMacAddr() {
		return this.macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public BigDecimal getMaintMode() {
		return this.maintMode;
	}

	public void setMaintMode(BigDecimal maintMode) {
		this.maintMode = maintMode;
	}

	public String getManagementVlan() {
		return this.managementVlan;
	}

	public void setManagementVlan(String managementVlan) {
		this.managementVlan = managementVlan;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public BigDecimal getPortNum() {
		return this.portNum;
	}

	public void setPortNum(BigDecimal portNum) {
		this.portNum = portNum;
	}

	public String getPreserver() {
		return this.preserver;
	}

	public void setPreserver(String preserver) {
		this.preserver = preserver;
	}

	public String getRelatedBrasCuid() {
		return this.relatedBrasCuid;
	}

	public void setRelatedBrasCuid(String relatedBrasCuid) {
		this.relatedBrasCuid = relatedBrasCuid;
	}

	public String getRelatedBrasPtp() {
		return this.relatedBrasPtp;
	}

	public void setRelatedBrasPtp(String relatedBrasPtp) {
		this.relatedBrasPtp = relatedBrasPtp;
	}

	public String getRelatedBtsCuid() {
		return this.relatedBtsCuid;
	}

	public void setRelatedBtsCuid(String relatedBtsCuid) {
		this.relatedBtsCuid = relatedBtsCuid;
	}

	public String getRelatedDistrictCuid() {
		return this.relatedDistrictCuid;
	}

	public void setRelatedDistrictCuid(String relatedDistrictCuid) {
		this.relatedDistrictCuid = relatedDistrictCuid;
	}

	public String getRelatedPtpCuid() {
		return this.relatedPtpCuid;
	}

	public void setRelatedPtpCuid(String relatedPtpCuid) {
		this.relatedPtpCuid = relatedPtpCuid;
	}

	public String getRelatedTransCuid() {
		return this.relatedTransCuid;
	}

	public void setRelatedTransCuid(String relatedTransCuid) {
		this.relatedTransCuid = relatedTransCuid;
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

	public String getUseBandwidth() {
		return this.useBandwidth;
	}

	public void setUseBandwidth(String useBandwidth) {
		this.useBandwidth = useBandwidth;
	}

	public BigDecimal getUsedPortNum() {
		return this.usedPortNum;
	}

	public void setUsedPortNum(BigDecimal usedPortNum) {
		this.usedPortNum = usedPortNum;
	}

	public BigDecimal getWireChannel1() {
		return this.wireChannel1;
	}

	public void setWireChannel1(BigDecimal wireChannel1) {
		this.wireChannel1 = wireChannel1;
	}

	public BigDecimal getWireChannel2() {
		return this.wireChannel2;
	}

	public void setWireChannel2(BigDecimal wireChannel2) {
		this.wireChannel2 = wireChannel2;
	}

	public BigDecimal getWireChannel3() {
		return this.wireChannel3;
	}

	public void setWireChannel3(BigDecimal wireChannel3) {
		this.wireChannel3 = wireChannel3;
	}

	public BigDecimal getWireDirection1() {
		return this.wireDirection1;
	}

	public void setWireDirection1(BigDecimal wireDirection1) {
		this.wireDirection1 = wireDirection1;
	}

	public BigDecimal getWireDirection2() {
		return this.wireDirection2;
	}

	public void setWireDirection2(BigDecimal wireDirection2) {
		this.wireDirection2 = wireDirection2;
	}

	public BigDecimal getWireDirection3() {
		return this.wireDirection3;
	}

	public void setWireDirection3(BigDecimal wireDirection3) {
		this.wireDirection3 = wireDirection3;
	}

	public BigDecimal getWireFrequency1() {
		return this.wireFrequency1;
	}

	public void setWireFrequency1(BigDecimal wireFrequency1) {
		this.wireFrequency1 = wireFrequency1;
	}

	public BigDecimal getWireFrequency2() {
		return this.wireFrequency2;
	}

	public void setWireFrequency2(BigDecimal wireFrequency2) {
		this.wireFrequency2 = wireFrequency2;
	}

	public BigDecimal getWireFrequency3() {
		return this.wireFrequency3;
	}

	public void setWireFrequency3(BigDecimal wireFrequency3) {
		this.wireFrequency3 = wireFrequency3;
	}

	public String getWireLabelCn1() {
		return this.wireLabelCn1;
	}

	public void setWireLabelCn1(String wireLabelCn1) {
		this.wireLabelCn1 = wireLabelCn1;
	}

	public String getWireLabelCn2() {
		return this.wireLabelCn2;
	}

	public void setWireLabelCn2(String wireLabelCn2) {
		this.wireLabelCn2 = wireLabelCn2;
	}

	public String getWireLabelCn3() {
		return this.wireLabelCn3;
	}

	public void setWireLabelCn3(String wireLabelCn3) {
		this.wireLabelCn3 = wireLabelCn3;
	}

}