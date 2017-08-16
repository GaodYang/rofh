package com.boco.rofh.webservice.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * 
 * @author gaoyang
 *4.4.2 设备资源及端口查询 请求
 */
public class QueryBroadPlanReq {

	
	@XStreamAlias("standard_addr_id")
	//@FieldNote(name="标准地址ID",isNullAble=false,length=12,regex=FvRegex.NUM)
	private String standardAddrId;

	//产品编码
	@XStreamAlias("prod_code")
	private String prodCode;

	
	public String getStandardAddrId() {
		return standardAddrId;
	}


	public void setStandardAddrId(String standardAddrId) {
		this.standardAddrId = standardAddrId;
	}


	public String getProdCode() {
		return prodCode;
	}


	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}


	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
}
