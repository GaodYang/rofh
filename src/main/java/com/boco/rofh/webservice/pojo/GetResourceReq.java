package com.boco.rofh.webservice.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("GetResourceReq")
public class GetResourceReq {

	@XStreamAlias("prod_ins_id")
//	@FieldNote(name="用户ID",isNullAble=false)
	private String prodInsId ;
	
//	@FieldNote(name="客户名称",isNullAble=false)
	@XStreamAlias("user_name")
	private String username;
	
//	@FieldNote(name="客户编号",isNullAble=false)
	private String account ;
	
//	@FieldNote(name="标准地址",isNullAble=false)
	private String address ;
	
	@XStreamAlias("standard_addr_id")
//	@FieldNote(name="标准地址ID",isNullAble=false)
	private String stdAddrId;
	
	@XStreamAlias("prod_srv_code")
//	@FieldNote(name="专业服务编码",isNullAble=false)
	private String prodSrvCode;

	public String getProdInsId() {
		return prodInsId;
	}

	public void setProdInsId(String prodInsId) {
		this.prodInsId = prodInsId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStdAddrId() {
		return stdAddrId;
	}

	public void setStdAddrId(String stdAddrId) {
		this.stdAddrId = stdAddrId;
	}

	
	public String getProdSrvCode() {
		return prodSrvCode;
	}

	public void setProdSrvCode(String prodSrvCode) {
		this.prodSrvCode = prodSrvCode;
	}

	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}

}
