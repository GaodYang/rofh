package com.boco.rofh.webservice.pojo;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.boco.rofh.utils.DateTimeConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

public class QueryAddressAttrResult {

	@XStreamAlias("region_type")
	private String regionType;
	
	@XStreamAlias("community_info")
	private CommunityInfo communityInfo;

	public String getRegionType() {
		return regionType;
	}

	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}
	
	public CommunityInfo getCommunityInfo() {
		return communityInfo;
	}

	public void setCommunityInfo(CommunityInfo communityInfo) {
		this.communityInfo = communityInfo;
	}

	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
	public static class CommunityInfo{
		
		@XStreamAlias("community_type")
		private String communityType;
		
		@XStreamAlias("completion_time")
		@XStreamConverter(value=DateTimeConverter.class,strings={"yyyy-MM-dd HH:mm:ss"})
		private Date completionTime;


		public String getCommunityType() {
			return communityType;
		}

		public void setCommunityType(String communityType) {
			this.communityType = communityType;
		}

		public Date getCompletionTime() {
			return completionTime;
		}

		public void setCompletionTime(Date completionTime) {
			this.completionTime = completionTime;
		}


		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}	
		
	}
	
}
