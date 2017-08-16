package com.boco.rofh.webservice.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class QueryAddressParentIdsResult {

	@XStreamAlias("addr_parent_ids")
	private String addrParentIds;

	public String getAddrParentIds() {
		return addrParentIds;
	}

	public void setAddrParentIds(String addrParentIds) {
		this.addrParentIds = addrParentIds;
	}
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
}
