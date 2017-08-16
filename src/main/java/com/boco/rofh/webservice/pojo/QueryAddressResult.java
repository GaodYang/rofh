package com.boco.rofh.webservice.pojo;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 4.3 标准地址查询 返回消息
 * @author gaoyang
 *
 */
@XStreamAlias("resStdAddrList")
public class QueryAddressResult {

	
	
	/**
	 * 地址列表
	 */
	@XStreamAlias("res_std_addr_list")
	private List<StdAddr> resstdaddrlist;
	
	@XStreamAlias("res_std_addr_info")
	public static class StdAddr{
		
		/**
		 * 行政区域ID
		 */
		@XStreamAlias("district_id")
		private String districtId;
		
		/**
		 * 行政区域名称
		 */
		@XStreamAlias("district_name")
		private String districtName;

		/**
		 * id
		 */
		private String id;

		/**
		 * name
		 */
		private String name;

		/**
		 * 地址简拼
		 */
		@XStreamAlias("detail_spell")
		private String detailSpell;

		/**
		 * 地址类型
		 */
		@XStreamAlias("std_addr_type_id")
		private String stdAddrTypeId;
		
		/**
		 * 主题词
		 */
		@XStreamAlias("topic_word")
		private String topicWord;
		
		/**
		 * 地址名称
		 */
		@XStreamAlias("detail_name")
		private String detailName;
		
		/**
		 * 地址状态
		 */
		@XStreamAlias("state")
		private String state = "U";
		
		/**
		 * 是否末级地址
		 */
		@XStreamAlias("is_coverpoint")
		private String isCoverpoint = "Y";
		
		@XStreamAlias("uptown_addressid")
		private String uptownAddressid;
		
		@XStreamAlias("detail_all_name")
		private String  detailAllName;
		
		@XStreamAlias("detail_all_spell")
		private String  detailAllSpell;
		
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


		public String getUptownAddressid() {
			return uptownAddressid;
		}


		public void setUptownAddressid(String uptownAddressid) {
			this.uptownAddressid = uptownAddressid;
		}


		public String getDistrictId() {
			return districtId;
		}


		public void setDistrictId(String districtId) {
			this.districtId = districtId;
		}


		public String getDistrictName() {
			return districtName;
		}


		public void setDistrictName(String districtName) {
			this.districtName = districtName;
		}


		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getDetailSpell() {
			return detailSpell;
		}


		public void setDetailSpell(String detailSpell) {
			this.detailSpell = detailSpell;
		}


		public String getStdAddrTypeId() {
			return stdAddrTypeId;
		}


		public void setStdAddrTypeId(String stdAddrTypeId) {
			this.stdAddrTypeId = stdAddrTypeId;
		}


		public String getTopicWord() {
			return topicWord;
		}


		public void setTopicWord(String topicWord) {
			this.topicWord = topicWord;
		}


		public String getDetailName() {
			return detailName;
		}


		public void setDetailName(String detailName) {
			this.detailName = detailName;
		}


		public String getState() {
			return state;
		}


		public void setState(String state) {
			this.state = state;
		}


		public String getIsCoverpoint() {
			return isCoverpoint;
		}


		public void setIsCoverpoint(String isCoverpoint) {
			this.isCoverpoint = isCoverpoint;
		}


		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}
	}
	


	public List<StdAddr> getResstdaddrlist() {
		return resstdaddrlist;
	}




	public void setResstdaddrlist(List<StdAddr> resstdaddrlist) {
		this.resstdaddrlist = resstdaddrlist;
	}




	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
}
