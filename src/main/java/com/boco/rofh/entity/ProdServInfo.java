package com.boco.rofh.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the T_PROD_SERV_INFO database table.
 * 
 */
@Entity
@Table(name="T_PROD_SERV_INFO")
@NamedQuery(name="ProdServInfo.findAll", query="SELECT t FROM ProdServInfo t")
@Cacheable
public class ProdServInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PROD_SRV_CODE")
	private String prodSrvCode;

	@Column(name="PROD_SRV_NAME")
	private String prodSrvName;

	@Column(name="PROD_SRV_TYPE")
	private String prodSrvType;

	public ProdServInfo() {
	}

	public String getProdSrvCode() {
		return this.prodSrvCode;
	}

	public void setProdSrvCode(String prodSrvCode) {
		this.prodSrvCode = prodSrvCode;
	}

	public String getProdSrvName() {
		return this.prodSrvName;
	}

	public void setProdSrvName(String prodSrvName) {
		this.prodSrvName = prodSrvName;
	}

	public String getProdSrvType() {
		return this.prodSrvType;
	}

	public void setProdSrvType(String prodSrvType) {
		this.prodSrvType = prodSrvType;
	}

}