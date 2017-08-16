package com.boco.rofh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the T_ROFH_FULL_ADDRESS database table.
 * 
 */
@Entity
@Table(name="T_ROFH_FULL_ADDRESS")
public class RofhFullAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	private String abbreviation;

	@Column(name="ADDRESS_LEVEL")
	private String addressLevel;

	@Column(name="APPROVE_RESULT")
	private String approveResult;

	private String building;

	private String city;

	private String community;

	private String county;

	@Temporal(TemporalType.TIME)
	@Column(name="CREATE_TIME")
	private Date createTime;

	@Id
	private String cuid;

	private String flag;

	@Column(name="FLOOR_NO")
	private String floorNo;

	@Column(name="LABEL_CN")
	private String labelCn;

	@Temporal(TemporalType.TIME)
	@Column(name="LAST_MODIFY_TIME")
	private Date lastModifyTime;

	private BigDecimal latitude;

	private BigDecimal longitude;

	@Column(name="OBJECT_ID")
	private BigDecimal objectId;

	private String pinyin;

	private String postcode;

	private String province;

	private String regiontype1;

	private String regiontype2;

	@Column(name="RELATED_BMCLASSTYPE_CUID")
	private String relatedBmclasstypeCuid;

	@Column(name="RELATED_COMMUNITY_CUID")
	private String relatedCommunityCuid;

	@Column(name="RELATED_PROJECT_CUID")
	private String relatedProjectCuid;

	private String road;

	@Column(name="ROAD_NUMBER")
	private String roadNumber;

	@Column(name="ROOM_NO")
	private String roomNo;


	private String town;

	@Column(name="UNIT_NO")
	private String unitNo;

	@Column(name="VILLAGE_ALIAS")
	private String villageAlias;

	private String villages;

	public RofhFullAddress() {
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getAddressLevel() {
		return this.addressLevel;
	}

	public void setAddressLevel(String addressLevel) {
		this.addressLevel = addressLevel;
	}

	public String getApproveResult() {
		return this.approveResult;
	}

	public void setApproveResult(String approveResult) {
		this.approveResult = approveResult;
	}

	public String getBuilding() {
		return this.building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}


	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCommunity() {
		return this.community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
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

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFloorNo() {
		return this.floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
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

	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getObjectId() {
		return this.objectId;
	}

	public void setObjectId(BigDecimal objectId) {
		this.objectId = objectId;
	}


	public String getPinyin() {
		return this.pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRegiontype1() {
		return this.regiontype1;
	}

	public void setRegiontype1(String regiontype1) {
		this.regiontype1 = regiontype1;
	}

	public String getRegiontype2() {
		return this.regiontype2;
	}

	public void setRegiontype2(String regiontype2) {
		this.regiontype2 = regiontype2;
	}

	public String getRelatedBmclasstypeCuid() {
		return this.relatedBmclasstypeCuid;
	}

	public void setRelatedBmclasstypeCuid(String relatedBmclasstypeCuid) {
		this.relatedBmclasstypeCuid = relatedBmclasstypeCuid;
	}

	public String getRelatedCommunityCuid() {
		return this.relatedCommunityCuid;
	}

	public void setRelatedCommunityCuid(String relatedCommunityCuid) {
		this.relatedCommunityCuid = relatedCommunityCuid;
	}


	public String getRelatedProjectCuid() {
		return this.relatedProjectCuid;
	}

	public void setRelatedProjectCuid(String relatedProjectCuid) {
		this.relatedProjectCuid = relatedProjectCuid;
	}


	public String getRoad() {
		return this.road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getRoadNumber() {
		return this.roadNumber;
	}

	public void setRoadNumber(String roadNumber) {
		this.roadNumber = roadNumber;
	}

	public String getRoomNo() {
		return this.roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getUnitNo() {
		return this.unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public String getVillageAlias() {
		return this.villageAlias;
	}

	public void setVillageAlias(String villageAlias) {
		this.villageAlias = villageAlias;
	}

	public String getVillages() {
		return this.villages;
	}

	public void setVillages(String villages) {
		this.villages = villages;
	}

}