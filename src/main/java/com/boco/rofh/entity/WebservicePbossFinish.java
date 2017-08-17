package com.boco.rofh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.boco.rofh.utils.XStreamUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * The persistent class for the T_WEBSERVICE_PBOSS_FINISH database table.
 * 
 */
@Entity
@Table(name="T_WEBSERVICE_PBOSS_FINISH")
@NamedQuery(name="WebservicePbossFinish.findAll", query="SELECT t FROM WebservicePbossFinish t")
@XStreamAlias("root")	
public class WebservicePbossFinish implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "IDUtils")  
    @GenericGenerator(name = "IDUtils", strategy = "com.boco.rofh.utils.IDUtils",  
    		parameters = { @Parameter(name = "tableName", value = "T_WEBSERVICE_PBOSS_FINISH") })
	private String cuid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime;

	@Column(name="ERROR_REASON")
	private String errorReason;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_MODIFY_TIME")
	private Date lastModifyTime;

	@Column(name="REGION_ID")
	private String regionId;

	@Column(name="RELATED_TASK_CUID")
	private String relatedTaskCuid;

	@Column(name="REPEAT_TIME")
	private BigDecimal repeatTime;

	@Lob
	@Column(name="REQ_XML")
	private String reqXml;

	@Column(name="RESP_XML")
	private String respXml;

	@Column(name="RESULT_CODE")
	@XStreamAlias("result_code")
	private String resultCode;

	@Column(name="RESULT_MSG")
	@XStreamAlias("result_msg")
	private String resultMsg;

	private String success;

	public WebservicePbossFinish() {
	}

	public String getCuid() {
		return this.cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getErrorReason() {
		return this.errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

	public Date getLastModifyTime() {
		return this.lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getRegionId() {
		return this.regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getRelatedTaskCuid() {
		return this.relatedTaskCuid;
	}

	public void setRelatedTaskCuid(String relatedTaskCuid) {
		this.relatedTaskCuid = relatedTaskCuid;
	}

	public BigDecimal getRepeatTime() {
		return this.repeatTime;
	}

	public void setRepeatTime(BigDecimal repeatTime) {
		this.repeatTime = repeatTime;
	}

	public String getReqXml() {
		return this.reqXml;
	}

	public void setReqXml(String reqXml) {
		this.reqXml = reqXml;
	}

	public String getRespXml() {
		return this.respXml;
	}

	public void setRespXml(String respXml) {
		this.respXml = respXml;
	}

	public String getResultCode() {
		return this.resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return this.resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getSuccess() {
		return this.success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public static WebservicePbossFinish toBean(String xml) {
		
		return XStreamUtil.fromXml(WebservicePbossFinish.class,xml,null,null);
	}

}