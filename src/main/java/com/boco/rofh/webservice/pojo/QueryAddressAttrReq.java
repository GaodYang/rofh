package com.boco.rofh.webservice.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class QueryAddressAttrReq {

	@XStreamAlias("standard_addr_id")
	private String standardAddrId;

	public String getStandardAddrId() {
		return standardAddrId;
	}

	public void setStandardAddrId(String standardAddrId) {
		this.standardAddrId = standardAddrId;
	}
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
}
