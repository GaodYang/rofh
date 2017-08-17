package com.boco.rofh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The persistent class for the T_ROFH_PRECOVER_ACTIVATE database table.
 * 
 */
@Entity
@Table(name="T_ROFH_PRECOVER_ACTIVATE")
@NamedQuery(name="RofhActivate.findAll", query="SELECT r FROM RofhActivate r")
public class RofhActivate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCESS_TYPE")
	private String accessType;

	@Column(name="ACTIVATE_RESULT")
	private String activateResult;

	@Column(name="ACTIVATE_STATE")
	private String activateState;

	@Column(name="ACTIVATE_STATUS")
	private String activateStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ACTIVATE_TIME")
	private Date activateTime;

	@Column(name="AUTO_TIMES")
	private BigDecimal autoTimes;

	@Id
	@GeneratedValue(generator = "IDUtils")  
    @GenericGenerator(name = "IDUtils", strategy = "com.boco.rofh.utils.IDUtils",  
    		parameters = { @Parameter(name = "tableName", value = "T_ROFH_PRECOVER_ACTIVATE") })
	private String cuid;

	private String cvlan;

	@Column(name="DATA_TYPE")
	private String dataType;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DEACTIVATE_TIME")
	private Date deactivateTime;

	private String logicid;

	@Column(name="OLT_PORT_NAME")
	private String oltPortName;

	@Column(name="ONU_NAME")
	private String onuName;

	@Column(name="ONU_PORT_NAME")
	private String onuPortName;

	@Column(name="ONU_TYPE")
	private String onuType;

	@Column(name="ONU_VENDER")
	private String onuVender;

	@Column(name="RELATED_OLT_CUID")
	private String relatedOltCuid;

	@Column(name="RELATED_OLT_DEVIP")
	private String relatedOltDevip;

	@Column(name="RELATED_OLT_NAME")
	private String relatedOltName;

	@Column(name="RELATED_OLT_PORT_CUID")
	private String relatedOltPortCuid;

	@Column(name="RELATED_ONU_CUID")
	private String relatedOnuCuid;

	@Column(name="RELATED_ONU_PORT_CUID")
	private String relatedOnuPortCuid;

	@Column(name="RELATED_ORDER_CUID")
	private String relatedOrderCuid;

	@Column(name="RELATED_POS_PORT_CUID")
	private String relatedPosPortCuid;

	@Column(name="RELATED_SHEET_CUID")
	private String relatedSheetCuid;

	@Column(name="RELATED_TASK_CUID")
	private String relatedTaskCuid;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="RETURN_ACTIVATE_TIME")
	private Date returnActivateTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SEND_ACTIVATE_TIME")
	private Date sendActivateTime;

	@Column(name="STANDARD_NAME")
	private String standardName;

	private String svlan;

	private String upport;

	@Column(name="VOICE_ACTIVATE_STATE")
	private String voiceActivateState;

	@Column(name="VOICE_SVLAN")
	private String voiceSvlan;

	public RofhActivate() {
	}

	public String getAccessType() {
		return this.accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getActivateResult() {
		return this.activateResult;
	}

	public void setActivateResult(String activateResult) {
		this.activateResult = activateResult;
	}

	public String getActivateState() {
		return this.activateState;
	}

	public void setActivateState(String activateState) {
		this.activateState = activateState;
	}

	public String getActivateStatus() {
		return this.activateStatus;
	}

	public void setActivateStatus(String activateStatus) {
		this.activateStatus = activateStatus;
	}

	public Date getActivateTime() {
		return this.activateTime;
	}

	public void setActivateTime(Date activateTime) {
		this.activateTime = activateTime;
	}

	public BigDecimal getAutoTimes() {
		return this.autoTimes;
	}

	public void setAutoTimes(BigDecimal autoTimes) {
		this.autoTimes = autoTimes;
	}

	public String getCuid() {
		return this.cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getCvlan() {
		return this.cvlan;
	}

	public void setCvlan(String cvlan) {
		this.cvlan = cvlan;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Date getDeactivateTime() {
		return this.deactivateTime;
	}

	public void setDeactivateTime(Date deactivateTime) {
		this.deactivateTime = deactivateTime;
	}

	public String getLogicid() {
		return this.logicid;
	}

	public void setLogicid(String logicid) {
		this.logicid = logicid;
	}

	public String getOltPortName() {
		return this.oltPortName;
	}

	public void setOltPortName(String oltPortName) {
		this.oltPortName = oltPortName;
	}

	public String getOnuName() {
		return this.onuName;
	}

	public void setOnuName(String onuName) {
		this.onuName = onuName;
	}

	public String getOnuPortName() {
		return this.onuPortName;
	}

	public void setOnuPortName(String onuPortName) {
		this.onuPortName = onuPortName;
	}

	public String getOnuType() {
		return this.onuType;
	}

	public void setOnuType(String onuType) {
		this.onuType = onuType;
	}

	public String getOnuVender() {
		return this.onuVender;
	}

	public void setOnuVender(String onuVender) {
		this.onuVender = onuVender;
	}

	public String getRelatedOltCuid() {
		return this.relatedOltCuid;
	}

	public void setRelatedOltCuid(String relatedOltCuid) {
		this.relatedOltCuid = relatedOltCuid;
	}

	public String getRelatedOltDevip() {
		return this.relatedOltDevip;
	}

	public void setRelatedOltDevip(String relatedOltDevip) {
		this.relatedOltDevip = relatedOltDevip;
	}

	public String getRelatedOltName() {
		return this.relatedOltName;
	}

	public void setRelatedOltName(String relatedOltName) {
		this.relatedOltName = relatedOltName;
	}

	public String getRelatedOltPortCuid() {
		return this.relatedOltPortCuid;
	}

	public void setRelatedOltPortCuid(String relatedOltPortCuid) {
		this.relatedOltPortCuid = relatedOltPortCuid;
	}

	public String getRelatedOnuCuid() {
		return this.relatedOnuCuid;
	}

	public void setRelatedOnuCuid(String relatedOnuCuid) {
		this.relatedOnuCuid = relatedOnuCuid;
	}

	public String getRelatedOnuPortCuid() {
		return this.relatedOnuPortCuid;
	}

	public void setRelatedOnuPortCuid(String relatedOnuPortCuid) {
		this.relatedOnuPortCuid = relatedOnuPortCuid;
	}

	public String getRelatedOrderCuid() {
		return this.relatedOrderCuid;
	}

	public void setRelatedOrderCuid(String relatedOrderCuid) {
		this.relatedOrderCuid = relatedOrderCuid;
	}

	public String getRelatedPosPortCuid() {
		return this.relatedPosPortCuid;
	}

	public void setRelatedPosPortCuid(String relatedPosPortCuid) {
		this.relatedPosPortCuid = relatedPosPortCuid;
	}

	public String getRelatedSheetCuid() {
		return this.relatedSheetCuid;
	}

	public void setRelatedSheetCuid(String relatedSheetCuid) {
		this.relatedSheetCuid = relatedSheetCuid;
	}

	public String getRelatedTaskCuid() {
		return this.relatedTaskCuid;
	}

	public void setRelatedTaskCuid(String relatedTaskCuid) {
		this.relatedTaskCuid = relatedTaskCuid;
	}

	public Date getReturnActivateTime() {
		return this.returnActivateTime;
	}

	public void setReturnActivateTime(Date returnActivateTime) {
		this.returnActivateTime = returnActivateTime;
	}

	public Date getSendActivateTime() {
		return this.sendActivateTime;
	}

	public void setSendActivateTime(Date sendActivateTime) {
		this.sendActivateTime = sendActivateTime;
	}

	public String getStandardName() {
		return this.standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public String getSvlan() {
		return this.svlan;
	}

	public void setSvlan(String svlan) {
		this.svlan = svlan;
	}

	public String getUpport() {
		return this.upport;
	}

	public void setUpport(String upport) {
		this.upport = upport;
	}

	public String getVoiceActivateState() {
		return this.voiceActivateState;
	}

	public void setVoiceActivateState(String voiceActivateState) {
		this.voiceActivateState = voiceActivateState;
	}

	public String getVoiceSvlan() {
		return this.voiceSvlan;
	}

	public void setVoiceSvlan(String voiceSvlan) {
		this.voiceSvlan = voiceSvlan;
	}

}