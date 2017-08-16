package com.boco.rofh.webservice.pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.boco.rofh.utils.XStreamUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("rtInfo")
public class ActiveResult {

	private String rtCode;
	
	private String resId;
	
	private String rtMessage;
	
	private String rtEntity;

	public String getRtCode() {
		return rtCode;
	}

	public void setRtCode(String rtCode) {
		this.rtCode = rtCode;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getRtMessage() {
		return rtMessage;
	}

	public void setRtMessage(String rtMessage) {
		this.rtMessage = rtMessage;
	}

	public String getRtEntity() {
		return rtEntity;
	}

	public void setRtEntity(String rtEntity) {
		this.rtEntity = rtEntity;
	}
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
	
	public static ActiveResult toBean(String xml){
		

		return XStreamUtil.fromXml(ActiveResult.class,xml,null,null);
	}
	
	public String toXml(){
		
		return XStreamUtil.toXml(this, null);
	}
}
