package com.boco.rofh.redis;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class RedisTaskKey {

	private String regionId;
	
	private String orderId;
	
	private int no;
	
	public RedisTaskKey() {
	}
	
	public RedisTaskKey(String regionId,String orderId,int no) {
		
		this.regionId = regionId;
		this.orderId = orderId;
		this.no = no;
	}
	
	public RedisTaskKey(String regionId,String orderId,String no) {
		
		this.regionId = regionId;
		this.orderId = orderId;
		this.no = Integer.parseInt(no);
	}

	
	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
	public String toKey(){
		
		return this.regionId + ":" + this.orderId + ":" + this.no;
	}
	
	public static RedisTaskKey fromKey(String key){
		
		String[] val = key.split(":");
		return new RedisTaskKey(val[0],val[1],val[2]);
	}
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
