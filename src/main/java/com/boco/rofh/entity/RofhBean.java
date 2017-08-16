package com.boco.rofh.entity;

public class RofhBean {

	private RofhProduct product;
	
	private RofhCustomer customer;
	
	private RofhOrder order;
	
	private String regionId;
	
	private String action;
	
	private String activeMsg;

	public RofhProduct getProduct() {
		return product;
	}

	public void setProduct(RofhProduct product) {
		this.product = product;
	}

	public RofhCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(RofhCustomer customer) {
		this.customer = customer;
	}

	public RofhOrder getOrder() {
		return order;
	}

	public void setOrder(RofhOrder order) {
		this.order = order;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActiveMsg() {
		return activeMsg;
	}

	public void setActiveMsg(String activeMsg) {
		this.activeMsg = activeMsg;
	}
	
	
	
}
