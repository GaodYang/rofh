package com.boco.rofh.webservice.pojo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 4.2 标准地址查询 返回消息
 * @author gaoyang
 *
 */
@XStreamAlias("resStdAddrList")
public class QueryAddressTreeResult {

	@XStreamAlias("res_std_addr_info")
	private ResStdAddrInfo  resStdAddrInfo;

	@XStreamAlias("res_std_addr_info")
	public static class ResStdAddrInfo{
		
		private String id;
		
		@XStreamAlias("district_id")
		private String districtId;
		
		private String name;
		
		private String spell;
		
		@XStreamAlias("std_addr_type_id")
		private String stdAddrTypeId;
		
		@XStreamAlias("parent_addr_id")
		private String parentAddrId;
		
		@XStreamAlias("detail_name")
		private String detailName;
		
		@XStreamAlias("detail_spell")
		private String detailSpell;
		
		@XStreamAlias("topic_word")
		private String topicWord;
		
		private String state = "U";
		
		private String remark;
		
		@XStreamAlias("create_date")
		private Date createDate;
		
		@XStreamAlias("region_id")
		private String regionId;
		
		@XStreamAlias("parent_addr_name")
		private String parentAddrName;
		
		@XStreamAlias("detail_all_name")
		private String detailAllName;
		
		@XStreamAlias("detail_all_spell")
		private String detailAllSpell;
		
		@XStreamAlias("sub_addr_list")
		private List<ResStdAddrInfo> subAddrList;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getDistrictId() {
			return districtId;
		}

		public void setDistrictId(String districtId) {
			this.districtId = districtId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSpell() {
			return spell;
		}

		public void setSpell(String spell) {
			this.spell = spell;
		}

		public String getStdAddrTypeId() {
			return stdAddrTypeId;
		}

		public void setStdAddrTypeId(String stdAddrTypeId) {
			this.stdAddrTypeId = stdAddrTypeId;
		}

		public String getParentAddrId() {
			return parentAddrId;
		}

		public void setParentAddrId(String parentAddrId) {
			this.parentAddrId = parentAddrId;
		}

		public String getDetailName() {
			return detailName;
		}

		public void setDetailName(String detailName) {
			this.detailName = detailName;
		}

		public String getDetailSpell() {
			return detailSpell;
		}

		public void setDetailSpell(String detailSpell) {
			this.detailSpell = detailSpell;
		}

		public String getTopicWord() {
			return topicWord;
		}

		public void setTopicWord(String topicWord) {
			this.topicWord = topicWord;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public String getRegionId() {
			return regionId;
		}

		public void setRegionId(String regionId) {
			this.regionId = regionId;
		}

		public String getParentAddrName() {
			return parentAddrName;
		}

		public void setParentAddrName(String parentAddrName) {
			this.parentAddrName = parentAddrName;
		}

		public String getDetailAllName() {
			return detailAllName;
		}

		public void setDetailAllName(String detailAllName) {
			this.detailAllName = detailAllName;
		}

		public String getDetailAllSpell() {
			return detailAllSpell;
		}

		public void setDetailAllSpell(String detailAllSpell) {
			this.detailAllSpell = detailAllSpell;
		}

		public List<ResStdAddrInfo> getSubAddrList() {
			return subAddrList;
		}

		public void setSubAddrList(List<ResStdAddrInfo> subAddrList) {
			this.subAddrList = subAddrList;
		}

		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}
	}
	
	
	public ResStdAddrInfo getResStdAddrInfo() {
		return resStdAddrInfo;
	}


	public void setResStdAddrInfo(ResStdAddrInfo resStdAddrInfo) {
		this.resStdAddrInfo = resStdAddrInfo;
	}


	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
}
