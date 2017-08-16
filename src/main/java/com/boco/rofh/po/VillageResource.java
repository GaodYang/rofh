package com.boco.rofh.po;

import org.apache.commons.lang.builder.ToStringBuilder;

public class VillageResource {
// t.ptid, t.ptname, t.id, t.name, t.type, bc.label_cn village, t.state state
	private String ptid;
	
	private String ptname;
	
	private String id;
	
	private String name;
	
	private String type;
	
	private String village;
	
	private int state;
	
	private String cuid;

	public String getPtid() {
		return ptid;
	}

	public void setPtid(String ptid) {
		this.ptid = ptid;
	}

	public String getPtname() {
		return ptname;
	}

	public void setPtname(String ptname) {
		this.ptname = ptname;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
	
	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
}
