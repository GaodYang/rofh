package com.boco.rofh.redis;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class RedisKey {

	private String orderId;
	
	private String no;
	
	public RedisKey() {
	}
	
	public RedisKey(String orderId,String no) {
		
		this.orderId = orderId;
		this.no = no;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getNo() {
		return Integer.parseInt(no);
	}

	public void setNo(int no) {
		this.no = no + "";
	}
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
