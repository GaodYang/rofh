package com.boco.rofh.webservice.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class QueryAddressParentIdsReq {

//	/当前节点地址ID
	@XStreamAlias("now_addr_id")
	private String nowAddrId	;
	//根节点地址ID
	@XStreamAlias("root_addr_id")
	private String rootAddrId	;
	public String getNowAddrId() {
		return nowAddrId;
	}
	public void setNowAddrId(String nowAddrId) {
		this.nowAddrId = nowAddrId;
	}
	public String getRootAddrId() {
		return rootAddrId;
	}
	public void setRootAddrId(String rootAddrId) {
		this.rootAddrId = rootAddrId;
	}

	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
}
