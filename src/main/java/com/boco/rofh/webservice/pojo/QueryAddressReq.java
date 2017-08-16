package com.boco.rofh.webservice.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class QueryAddressReq {

	@XStreamAlias("std_addr_id")
	private String stdAddrId;
	//@FieldNote(name="行政区域ID",isNullAble=false,length=64,type=FvEnum.STRING)
	@XStreamAlias("district_id")
	private String districtId;
	
	//@FieldNote(name="行政区域名称",isNullAble=true,length=64,type=FvEnum.STRING)
/*	@XStreamAlias("district_name")
	private String districtName;*/
	
	//@FieldNote(name="主题词",isNullAble=false,length=254,type=FvEnum.STRING)
	@XStreamAlias("topic_word")
	private String topicWord;
	
	//标准地址类型ID
	@XStreamAlias("std_addr_type_id")
	private String stdAddrTypeId;
	
	//地址简拼
	@XStreamAlias("detail_spell")
	private String detailSpell;
	
	//地址名称
	@XStreamAlias("detail_name")
	private String detailName;
	
	//地址简拼
	private String spell;
	//地址名称
	private String name;
//	/上级地址ID
	@XStreamAlias("parent_addr_id")
	private String parentAddrId;
//	/上级地址名称
	@XStreamAlias("parent_addr_name")
	private String parentAddrName	;

	//状态
	@XStreamAlias("state")
	private String state;
	
	//页数（第几页)
	//@FieldNote(name="页数",isNullAble=false,length=8,type=FvEnum.STRING,regex=FvRegex.NUM)
	//开始页数
	@XStreamAlias("start_index")
	private int startIndex;
	
	//@FieldNote(name="每页数量",isNullAble=false,length=4,type=FvEnum.STRING,regex=FvRegex.NUM)
	@XStreamAlias("end_index")
	private int endIndex;

	

	public String getStdAddrId() {
		return stdAddrId;
	}



	public void setStdAddrId(String stdAddrId) {
		this.stdAddrId = stdAddrId;
	}



	public String getDistrictId() {
		return districtId;
	}



	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}



	/*public String getDistrictName() {
		return districtName;
	}



	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}*/



	public String getTopicWord() {
		return topicWord;
	}



	public void setTopicWord(String topicWord) {
		this.topicWord = topicWord;
	}



	public String getStdAddrTypeId() {
		return stdAddrTypeId;
	}



	public void setStdAddrTypeId(String stdAddrTypeId) {
		this.stdAddrTypeId = stdAddrTypeId;
	}



	public String getDetailSpell() {
		return detailSpell;
	}



	public void setDetailSpell(String detailSpell) {
		this.detailSpell = detailSpell;
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



	

	public String getSpell() {
		return spell;
	}



	public void setSpell(String spell) {
		this.spell = spell;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getParentAddrId() {
		return parentAddrId;
	}



	public void setParentAddrId(String parentAddrId) {
		this.parentAddrId = parentAddrId;
	}



	public String getParentAddrName() {
		return parentAddrName;
	}



	public void setParentAddrName(String parentAddrName) {
		this.parentAddrName = parentAddrName;
	}



	public int getStartIndex() {
		return startIndex;
	}



	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}



	public int getEndIndex() {
		return endIndex;
	}



	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}



	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
