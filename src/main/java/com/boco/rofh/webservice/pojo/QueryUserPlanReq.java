package com.boco.rofh.webservice.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 用户带宽占用查询请求
 * @author gaoyang
 *
 */
public class QueryUserPlanReq {

	@XStreamAlias("prod_ins_id")
	//@FieldNote(name="用户标识")
	private String prodInsId;
	
	@XStreamAlias("region_code")
	//@FieldNote(name="地市编码")
	private String regionCode;

	public String getProdInsId() {
		return prodInsId;
	}

	public void setProdInsId(String prodInsId) {
		this.prodInsId = prodInsId;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
}
