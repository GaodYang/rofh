package com.boco.rofh.webservice.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class QueryAddressTreeReq {


	
	@XStreamAlias("now_addr_id")
	private String nowAddrId;

	//子节点的深度
	@XStreamAlias("child_deep")
	private String childDeep;

	@XStreamAlias("district_id")
	private String districtId;

	public String getNowAddrId() {
		return nowAddrId;
	}

	public void setNowAddrId(String nowAddrId) {
		this.nowAddrId = nowAddrId;
	}


	public String getChildDeep() {
		return childDeep;
	}

	public void setChildDeep(String childDeep) {
		this.childDeep = childDeep;
	}

	
	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
}
 