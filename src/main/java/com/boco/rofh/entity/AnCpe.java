package com.boco.rofh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the AN_CPE database table.
 * 
 */
@Entity
@Table(name="AN_CPE")
@NamedQuery(name="AnCpe.findAll", query="SELECT a FROM AnCpe a")
public class AnCpe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"ALIAS\"")
	private String alias;

	@Column(name="CPE_IP")
	private String cpeIp;

	@Id
	@GeneratedValue(generator = "IDUtils")  
    @GenericGenerator(name = "IDUtils", strategy = "com.boco.rofh.utils.IDUtils",  
    		parameters = { @Parameter(name = "tableName", value = "AN_CPE") })
	private String cuid;

	@Column(name="LABEL_CN")
	private String labelCn;

	private double latitude;

	@Column(name="LIVE_CYCLE")
	private String liveCycle;

	@Column(name="MAINT_MODE")
	private String maintMode;

	private String model;

	private BigDecimal objectid;

	private String ownership;

	private double reallongitude;

	@Column(name="RELATED_DISTRICT_CUID")
	private String relatedDistrictCuid;

	@Column(name="RELATED_VENDOR_CUID")
	private String relatedVendorCuid;

	@Column(name="RELATED_WBS_CUID")
	private String relatedWbsCuid;

	@Column(name="RELATED_WBS_SPARE")
	private String relatedWbsSpare;

	private String remark;

	@Column(name="RESOURCES_TYPE")
	private String resourcesType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SETUP_TIME")
	private Date setupTime;

	@Column(name="WBS_COVER")
	private String wbsCover;

	@Column(name="WBS_LOCATION")
	private String wbsLocation;

	public AnCpe() {
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCpeIp() {
		return this.cpeIp;
	}

	public void setCpeIp(String cpeIp) {
		this.cpeIp = cpeIp;
	}

	public String getCuid() {
		return this.cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getLabelCn() {
		return this.labelCn;
	}

	public void setLabelCn(String labelCn) {
		this.labelCn = labelCn;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getLiveCycle() {
		return this.liveCycle;
	}

	public void setLiveCycle(String liveCycle) {
		this.liveCycle = liveCycle;
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

	public BigDecimal getObjectid() {
		return this.objectid;
	}

	public void setObjectid(BigDecimal objectid) {
		this.objectid = objectid;
	}

	public String getOwnership() {
		return this.ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public double getReallongitude() {
		return this.reallongitude;
	}

	public void setReallongitude(double reallongitude) {
		this.reallongitude = reallongitude;
	}

	public String getRelatedDistrictCuid() {
		return this.relatedDistrictCuid;
	}

	public void setRelatedDistrictCuid(String relatedDistrictCuid) {
		this.relatedDistrictCuid = relatedDistrictCuid;
	}

	public String getRelatedVendorCuid() {
		return this.relatedVendorCuid;
	}

	public void setRelatedVendorCuid(String relatedVendorCuid) {
		this.relatedVendorCuid = relatedVendorCuid;
	}

	public String getRelatedWbsCuid() {
		return this.relatedWbsCuid;
	}

	public void setRelatedWbsCuid(String relatedWbsCuid) {
		this.relatedWbsCuid = relatedWbsCuid;
	}

	public String getRelatedWbsSpare() {
		return this.relatedWbsSpare;
	}

	public void setRelatedWbsSpare(String relatedWbsSpare) {
		this.relatedWbsSpare = relatedWbsSpare;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getResourcesType() {
		return this.resourcesType;
	}

	public void setResourcesType(String resourcesType) {
		this.resourcesType = resourcesType;
	}

	public Date getSetupTime() {
		return this.setupTime;
	}

	public void setSetupTime(Date setupTime) {
		this.setupTime = setupTime;
	}

	public String getWbsCover() {
		return this.wbsCover;
	}

	public void setWbsCover(String wbsCover) {
		this.wbsCover = wbsCover;
	}

	public String getWbsLocation() {
		return this.wbsLocation;
	}

	public void setWbsLocation(String wbsLocation) {
		this.wbsLocation = wbsLocation;
	}

}