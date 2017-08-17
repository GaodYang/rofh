package com.boco.rofh.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the T_ROFH_CUSTOMER database table.
 * 
 */
@Entity
@Table(name="T_ROFH_CUSTOMER")
@NamedQuery(name="RofhCustomer.findAll", query="SELECT t FROM RofhCustomer t")
public class RofhCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "IDUtils")  
    @GenericGenerator(name = "IDUtils", strategy = "com.boco.rofh.utils.IDUtils",  
    		parameters = { @Parameter(name = "tableName", value = "T_ROFH_CUSTOMER") })
	private String cuid;

	private String city;

	@Column(name="CONTACT_NUMBER")
	private String contactNumber;

	private String county;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime;

	@Column(name="CUSTOMER_ADDRESS")
	private String customerAddress;

	@Column(name="CUSTOMER_CODE")
	private String customerCode;

	@Column(name="CUSTOMER_CONTACT")
	private String customerContact;

	@Column(name="EMAIL_ADDRESS")
	private String emailAddress;

	@Column(name="LABEL_CN")
	private String labelCn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_MODIFY_TIME")
	private Date lastModifyTime;

	private String province;

	@Column(name="RELATED_BMCLASSTYPE_CUID")
	private String relatedBmclasstypeCuid;

	private String remark;

	@Column(name="SEC_CONTACT_EMAIL")
	private String secContactEmail;

	@Column(name="SEC_CONTACT_NAME")
	private String secContactName;

	@Column(name="SEC_CONTACT_NUMBER")
	private String secContactNumber;

	public RofhCustomer() {
	}

	public String getCuid() {
		return this.cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCustomerAddress() {
		return this.customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerContact() {
		return this.customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getLabelCn() {
		return this.labelCn;
	}

	public void setLabelCn(String labelCn) {
		this.labelCn = labelCn;
	}

	public Date getLastModifyTime() {
		return this.lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRelatedBmclasstypeCuid() {
		return this.relatedBmclasstypeCuid;
	}

	public void setRelatedBmclasstypeCuid(String relatedBmclasstypeCuid) {
		this.relatedBmclasstypeCuid = relatedBmclasstypeCuid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSecContactEmail() {
		return this.secContactEmail;
	}

	public void setSecContactEmail(String secContactEmail) {
		this.secContactEmail = secContactEmail;
	}

	public String getSecContactName() {
		return this.secContactName;
	}

	public void setSecContactName(String secContactName) {
		this.secContactName = secContactName;
	}

	public String getSecContactNumber() {
		return this.secContactNumber;
	}

	public void setSecContactNumber(String secContactNumber) {
		this.secContactNumber = secContactNumber;
	}


}