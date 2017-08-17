package com.boco.rofh.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


/**
 * The persistent class for the PTP database table.
 * 
 */
@Entity
@NamedQuery(name="Ptp.findAll", query="SELECT p FROM Ptp p")
public class Ptp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ADMIN_STATE")
	private BigDecimal adminState;

	@Column(name="AVAILABLE_BANDWIDTH")
	private String availableBandwidth;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime;

	@Id
	@GeneratedValue(generator = "IDUtils")  
    @GenericGenerator(name = "IDUtils", strategy = "com.boco.rofh.utils.IDUtils",  
    		parameters = { @Parameter(name = "tableName", value = "PTP") })
	private String cuid;

	private BigDecimal currentwavenum;

	private String cvlan;

	@Column(name="DEV_IP")
	private String devIp;

	@Column(name="DEV_TYPE")
	private BigDecimal devType;

	private BigDecimal directionality;

	private String fdn;

	private String frequency;

	@Column(name="GT_VERSION")
	private BigDecimal gtVersion;

	private String idn;

	@Column(name="INTERFACE_TYPE")
	private BigDecimal interfaceType;

	@Column(name="IS_CHANNEL")
	private BigDecimal isChannel;

	@Column(name="IS_CONN_STATE")
	private BigDecimal isConnState;

	@Column(name="IS_PERMIT_SYS_DEL")
	private BigDecimal isPermitSysDel;

	@Column(name="IS_SHARE")
	private BigDecimal isShare;

	private BigDecimal isdelete;

	@Column(name="LABEL_CN")
	private String labelCn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_MODIFY_TIME")
	private Date lastModifyTime;

	@Column(name="LAYER_INFO")
	private String layerInfo;

	@Column(name="LINE_BRANCH_TYPE")
	private BigDecimal lineBranchType;

	@Column(name="LIVE_CYCLE")
	private BigDecimal liveCycle;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LIVEMODIFY_TIME")
	private Date livemodifyTime;

	@Column(name="LIVEMODIFY_USER")
	private String livemodifyUser;

	@Column(name="LOOP_INFO")
	private String loopInfo;

	@Column(name="LOOP_STATE")
	private BigDecimal loopState;

	@Column(name="MAC_TAG_FLAG")
	private BigDecimal macTagFlag;

	@Column(name="MSTP_ANFCMODE")
	private BigDecimal mstpAnfcmode;

	@Column(name="MSTP_BMSGSUPPRESS")
	private BigDecimal mstpBmsgsuppress;

	@Column(name="MSTP_CFLEN")
	private BigDecimal mstpCflen;

	@Column(name="MSTP_EDETECT")
	private BigDecimal mstpEdetect;

	@Column(name="MSTP_ENCAPFORMAT")
	private BigDecimal mstpEncapformat;

	@Column(name="MSTP_ENCAPPROTOCOL")
	private BigDecimal mstpEncapprotocol;

	@Column(name="MSTP_EXTENDEADER")
	private BigDecimal mstpExtendeader;

	@Column(name="MSTP_FCSCALSEQ")
	private BigDecimal mstpFcscalseq;

	@Column(name="MSTP_FLOWCTRL")
	private BigDecimal mstpFlowctrl;

	@Column(name="MSTP_LCAS_FLAG")
	private BigDecimal mstpLcasFlag;

	@Column(name="MSTP_MAXPACKETLEN")
	private BigDecimal mstpMaxpacketlen;

	@Column(name="MSTP_NANFCMODE")
	private BigDecimal mstpNanfcmode;

	@Column(name="MSTP_PORT_SERVICETYPE")
	private BigDecimal mstpPortServicetype;

	@Column(name="MSTP_PORT_TYPE")
	private BigDecimal mstpPortType;

	@Column(name="MSTP_PORTENABLE")
	private BigDecimal mstpPortenable;

	@Column(name="MSTP_PPTENABLE")
	private BigDecimal mstpPptenable;

	@Column(name="MSTP_PVID")
	private String mstpPvid;

	@Column(name="MSTP_SCRAMBEL")
	private BigDecimal mstpScrambel;

	@Column(name="MSTP_TAG_FLAG")
	private BigDecimal mstpTagFlag;

	@Column(name="MSTP_USERID")
	private BigDecimal mstpUserid;

	@Column(name="MSTP_VLANID")
	private BigDecimal mstpVlanid;

	@Column(name="MSTP_VLANPRIORITY")
	private BigDecimal mstpVlanpriority;

	@Column(name="MSTP_WORKMODE")
	private BigDecimal mstpWorkmode;

	@Column(name="NATIVE_NAME")
	private String nativeName;

	@Column(name="OBJECT_TYPE_CODE")
	private BigDecimal objectTypeCode;

	private BigDecimal objectid;

	@Column(name="OPERATING_STATE")
	private BigDecimal operatingState;

	@Column(name="PORT_LOOP_STAT")
	private BigDecimal portLoopStat;

	@Column(name="PORT_NO")
	private BigDecimal portNo;

	@Column(name="PORT_RATE")
	private BigDecimal portRate;

	@Column(name="PORT_STATE")
	private String portState;

	@Column(name="PORT_SUB_TYPE")
	private BigDecimal portSubType;

	@Column(name="PORT_TYPE")
	private BigDecimal portType;

	@Column(name="PROJECT_STATE")
	private BigDecimal projectState;

	@Column(name="RECEIVE_FREQUENCY")
	private String receiveFrequency;

	@Column(name="RELATED_CARD_CUID")
	private String relatedCardCuid;

	@Column(name="RELATED_DISTRICT_CUID")
	private String relatedDistrictCuid;

	@Column(name="RELATED_EMS_CUID")
	private String relatedEmsCuid;

	@Column(name="RELATED_NE_CUID")
	private String relatedNeCuid;

	@Column(name="RELATED_USETASK_CUID")
	private String relatedUsetaskCuid;

	@Column(name="RELATED_VB_CUID")
	private String relatedVbCuid;

	private String remark;

	@Column(name="SEND_FREQUENCY")
	private String sendFrequency;

	@Column(name="STANDARD_NAME")
	private String standardName;

	@Column(name="SYS_NO")
	private String sysNo;

	@Column(name="TERMINATION_MODE")
	private BigDecimal terminationMode;

	@Column(name="USE_STATE")
	private BigDecimal useState;

	private String userlabel;

	private String vlan;
	
	private String svlan;
	
	public String getSvlan() {
		return svlan;
	}

	public void setSvlan(String svlan) {
		this.svlan = svlan;
	}

	@Column(name="WAVE_PATH_NO")
	private String wavePathNo;

	private BigDecimal waverate;

	public Ptp() {
	}

	public BigDecimal getAdminState() {
		return this.adminState;
	}

	public void setAdminState(BigDecimal adminState) {
		this.adminState = adminState;
	}

	public String getAvailableBandwidth() {
		return this.availableBandwidth;
	}

	public void setAvailableBandwidth(String availableBandwidth) {
		this.availableBandwidth = availableBandwidth;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCuid() {
		return this.cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public BigDecimal getCurrentwavenum() {
		return this.currentwavenum;
	}

	public void setCurrentwavenum(BigDecimal currentwavenum) {
		this.currentwavenum = currentwavenum;
	}

	public String getCvlan() {
		return this.cvlan;
	}

	public void setCvlan(String cvlan) {
		this.cvlan = cvlan;
	}

	public String getDevIp() {
		return this.devIp;
	}

	public void setDevIp(String devIp) {
		this.devIp = devIp;
	}

	public BigDecimal getDevType() {
		return this.devType;
	}

	public void setDevType(BigDecimal devType) {
		this.devType = devType;
	}

	public BigDecimal getDirectionality() {
		return this.directionality;
	}

	public void setDirectionality(BigDecimal directionality) {
		this.directionality = directionality;
	}

	public String getFdn() {
		return this.fdn;
	}

	public void setFdn(String fdn) {
		this.fdn = fdn;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public BigDecimal getGtVersion() {
		return this.gtVersion;
	}

	public void setGtVersion(BigDecimal gtVersion) {
		this.gtVersion = gtVersion;
	}

	public String getIdn() {
		return this.idn;
	}

	public void setIdn(String idn) {
		this.idn = idn;
	}

	public BigDecimal getInterfaceType() {
		return this.interfaceType;
	}

	public void setInterfaceType(BigDecimal interfaceType) {
		this.interfaceType = interfaceType;
	}

	public BigDecimal getIsChannel() {
		return this.isChannel;
	}

	public void setIsChannel(BigDecimal isChannel) {
		this.isChannel = isChannel;
	}

	public BigDecimal getIsConnState() {
		return this.isConnState;
	}

	public void setIsConnState(BigDecimal isConnState) {
		this.isConnState = isConnState;
	}

	public BigDecimal getIsPermitSysDel() {
		return this.isPermitSysDel;
	}

	public void setIsPermitSysDel(BigDecimal isPermitSysDel) {
		this.isPermitSysDel = isPermitSysDel;
	}

	public BigDecimal getIsShare() {
		return this.isShare;
	}

	public void setIsShare(BigDecimal isShare) {
		this.isShare = isShare;
	}

	public BigDecimal getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(BigDecimal isdelete) {
		this.isdelete = isdelete;
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

	public String getLayerInfo() {
		return this.layerInfo;
	}

	public void setLayerInfo(String layerInfo) {
		this.layerInfo = layerInfo;
	}

	public BigDecimal getLineBranchType() {
		return this.lineBranchType;
	}

	public void setLineBranchType(BigDecimal lineBranchType) {
		this.lineBranchType = lineBranchType;
	}

	public BigDecimal getLiveCycle() {
		return this.liveCycle;
	}

	public void setLiveCycle(BigDecimal liveCycle) {
		this.liveCycle = liveCycle;
	}

	public Date getLivemodifyTime() {
		return this.livemodifyTime;
	}

	public void setLivemodifyTime(Date livemodifyTime) {
		this.livemodifyTime = livemodifyTime;
	}

	public String getLivemodifyUser() {
		return this.livemodifyUser;
	}

	public void setLivemodifyUser(String livemodifyUser) {
		this.livemodifyUser = livemodifyUser;
	}

	public String getLoopInfo() {
		return this.loopInfo;
	}

	public void setLoopInfo(String loopInfo) {
		this.loopInfo = loopInfo;
	}

	public BigDecimal getLoopState() {
		return this.loopState;
	}

	public void setLoopState(BigDecimal loopState) {
		this.loopState = loopState;
	}

	public BigDecimal getMacTagFlag() {
		return this.macTagFlag;
	}

	public void setMacTagFlag(BigDecimal macTagFlag) {
		this.macTagFlag = macTagFlag;
	}

	public BigDecimal getMstpAnfcmode() {
		return this.mstpAnfcmode;
	}

	public void setMstpAnfcmode(BigDecimal mstpAnfcmode) {
		this.mstpAnfcmode = mstpAnfcmode;
	}

	public BigDecimal getMstpBmsgsuppress() {
		return this.mstpBmsgsuppress;
	}

	public void setMstpBmsgsuppress(BigDecimal mstpBmsgsuppress) {
		this.mstpBmsgsuppress = mstpBmsgsuppress;
	}

	public BigDecimal getMstpCflen() {
		return this.mstpCflen;
	}

	public void setMstpCflen(BigDecimal mstpCflen) {
		this.mstpCflen = mstpCflen;
	}

	public BigDecimal getMstpEdetect() {
		return this.mstpEdetect;
	}

	public void setMstpEdetect(BigDecimal mstpEdetect) {
		this.mstpEdetect = mstpEdetect;
	}

	public BigDecimal getMstpEncapformat() {
		return this.mstpEncapformat;
	}

	public void setMstpEncapformat(BigDecimal mstpEncapformat) {
		this.mstpEncapformat = mstpEncapformat;
	}

	public BigDecimal getMstpEncapprotocol() {
		return this.mstpEncapprotocol;
	}

	public void setMstpEncapprotocol(BigDecimal mstpEncapprotocol) {
		this.mstpEncapprotocol = mstpEncapprotocol;
	}

	public BigDecimal getMstpExtendeader() {
		return this.mstpExtendeader;
	}

	public void setMstpExtendeader(BigDecimal mstpExtendeader) {
		this.mstpExtendeader = mstpExtendeader;
	}

	public BigDecimal getMstpFcscalseq() {
		return this.mstpFcscalseq;
	}

	public void setMstpFcscalseq(BigDecimal mstpFcscalseq) {
		this.mstpFcscalseq = mstpFcscalseq;
	}

	public BigDecimal getMstpFlowctrl() {
		return this.mstpFlowctrl;
	}

	public void setMstpFlowctrl(BigDecimal mstpFlowctrl) {
		this.mstpFlowctrl = mstpFlowctrl;
	}

	public BigDecimal getMstpLcasFlag() {
		return this.mstpLcasFlag;
	}

	public void setMstpLcasFlag(BigDecimal mstpLcasFlag) {
		this.mstpLcasFlag = mstpLcasFlag;
	}

	public BigDecimal getMstpMaxpacketlen() {
		return this.mstpMaxpacketlen;
	}

	public void setMstpMaxpacketlen(BigDecimal mstpMaxpacketlen) {
		this.mstpMaxpacketlen = mstpMaxpacketlen;
	}

	public BigDecimal getMstpNanfcmode() {
		return this.mstpNanfcmode;
	}

	public void setMstpNanfcmode(BigDecimal mstpNanfcmode) {
		this.mstpNanfcmode = mstpNanfcmode;
	}

	public BigDecimal getMstpPortServicetype() {
		return this.mstpPortServicetype;
	}

	public void setMstpPortServicetype(BigDecimal mstpPortServicetype) {
		this.mstpPortServicetype = mstpPortServicetype;
	}

	public BigDecimal getMstpPortType() {
		return this.mstpPortType;
	}

	public void setMstpPortType(BigDecimal mstpPortType) {
		this.mstpPortType = mstpPortType;
	}

	public BigDecimal getMstpPortenable() {
		return this.mstpPortenable;
	}

	public void setMstpPortenable(BigDecimal mstpPortenable) {
		this.mstpPortenable = mstpPortenable;
	}

	public BigDecimal getMstpPptenable() {
		return this.mstpPptenable;
	}

	public void setMstpPptenable(BigDecimal mstpPptenable) {
		this.mstpPptenable = mstpPptenable;
	}

	public String getMstpPvid() {
		return this.mstpPvid;
	}

	public void setMstpPvid(String mstpPvid) {
		this.mstpPvid = mstpPvid;
	}

	public BigDecimal getMstpScrambel() {
		return this.mstpScrambel;
	}

	public void setMstpScrambel(BigDecimal mstpScrambel) {
		this.mstpScrambel = mstpScrambel;
	}

	public BigDecimal getMstpTagFlag() {
		return this.mstpTagFlag;
	}

	public void setMstpTagFlag(BigDecimal mstpTagFlag) {
		this.mstpTagFlag = mstpTagFlag;
	}

	public BigDecimal getMstpUserid() {
		return this.mstpUserid;
	}

	public void setMstpUserid(BigDecimal mstpUserid) {
		this.mstpUserid = mstpUserid;
	}

	public BigDecimal getMstpVlanid() {
		return this.mstpVlanid;
	}

	public void setMstpVlanid(BigDecimal mstpVlanid) {
		this.mstpVlanid = mstpVlanid;
	}

	public BigDecimal getMstpVlanpriority() {
		return this.mstpVlanpriority;
	}

	public void setMstpVlanpriority(BigDecimal mstpVlanpriority) {
		this.mstpVlanpriority = mstpVlanpriority;
	}

	public BigDecimal getMstpWorkmode() {
		return this.mstpWorkmode;
	}

	public void setMstpWorkmode(BigDecimal mstpWorkmode) {
		this.mstpWorkmode = mstpWorkmode;
	}

	public String getNativeName() {
		return this.nativeName;
	}

	public void setNativeName(String nativeName) {
		this.nativeName = nativeName;
	}

	public BigDecimal getObjectTypeCode() {
		return this.objectTypeCode;
	}

	public void setObjectTypeCode(BigDecimal objectTypeCode) {
		this.objectTypeCode = objectTypeCode;
	}

	public BigDecimal getObjectid() {
		return this.objectid;
	}

	public void setObjectid(BigDecimal objectid) {
		this.objectid = objectid;
	}

	public BigDecimal getOperatingState() {
		return this.operatingState;
	}

	public void setOperatingState(BigDecimal operatingState) {
		this.operatingState = operatingState;
	}

	public BigDecimal getPortLoopStat() {
		return this.portLoopStat;
	}

	public void setPortLoopStat(BigDecimal portLoopStat) {
		this.portLoopStat = portLoopStat;
	}

	public BigDecimal getPortNo() {
		return this.portNo;
	}

	public void setPortNo(BigDecimal portNo) {
		this.portNo = portNo;
	}

	public BigDecimal getPortRate() {
		return this.portRate;
	}

	public void setPortRate(BigDecimal portRate) {
		this.portRate = portRate;
	}

	public String getPortState() {
		return this.portState;
	}

	public void setPortState(String portState) {
		this.portState = portState;
	}

	public BigDecimal getPortSubType() {
		return this.portSubType;
	}

	public void setPortSubType(BigDecimal portSubType) {
		this.portSubType = portSubType;
	}

	public BigDecimal getPortType() {
		return this.portType;
	}

	public void setPortType(BigDecimal portType) {
		this.portType = portType;
	}

	public BigDecimal getProjectState() {
		return this.projectState;
	}

	public void setProjectState(BigDecimal projectState) {
		this.projectState = projectState;
	}

	public String getReceiveFrequency() {
		return this.receiveFrequency;
	}

	public void setReceiveFrequency(String receiveFrequency) {
		this.receiveFrequency = receiveFrequency;
	}

	public String getRelatedCardCuid() {
		return this.relatedCardCuid;
	}

	public void setRelatedCardCuid(String relatedCardCuid) {
		this.relatedCardCuid = relatedCardCuid;
	}

	public String getRelatedDistrictCuid() {
		return this.relatedDistrictCuid;
	}

	public void setRelatedDistrictCuid(String relatedDistrictCuid) {
		this.relatedDistrictCuid = relatedDistrictCuid;
	}

	public String getRelatedEmsCuid() {
		return this.relatedEmsCuid;
	}

	public void setRelatedEmsCuid(String relatedEmsCuid) {
		this.relatedEmsCuid = relatedEmsCuid;
	}

	public String getRelatedNeCuid() {
		return this.relatedNeCuid;
	}

	public void setRelatedNeCuid(String relatedNeCuid) {
		this.relatedNeCuid = relatedNeCuid;
	}

	public String getRelatedUsetaskCuid() {
		return this.relatedUsetaskCuid;
	}

	public void setRelatedUsetaskCuid(String relatedUsetaskCuid) {
		this.relatedUsetaskCuid = relatedUsetaskCuid;
	}

	public String getRelatedVbCuid() {
		return this.relatedVbCuid;
	}

	public void setRelatedVbCuid(String relatedVbCuid) {
		this.relatedVbCuid = relatedVbCuid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSendFrequency() {
		return this.sendFrequency;
	}

	public void setSendFrequency(String sendFrequency) {
		this.sendFrequency = sendFrequency;
	}

	public String getStandardName() {
		return this.standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public String getSysNo() {
		return this.sysNo;
	}

	public void setSysNo(String sysNo) {
		this.sysNo = sysNo;
	}

	public BigDecimal getTerminationMode() {
		return this.terminationMode;
	}

	public void setTerminationMode(BigDecimal terminationMode) {
		this.terminationMode = terminationMode;
	}

	public BigDecimal getUseState() {
		return this.useState;
	}

	public void setUseState(BigDecimal useState) {
		this.useState = useState;
	}

	public String getUserlabel() {
		return this.userlabel;
	}

	public void setUserlabel(String userlabel) {
		this.userlabel = userlabel;
	}

	public String getVlan() {
		return this.vlan;
	}

	public void setVlan(String vlan) {
		this.vlan = vlan;
	}

	public String getWavePathNo() {
		return this.wavePathNo;
	}

	public void setWavePathNo(String wavePathNo) {
		this.wavePathNo = wavePathNo;
	}

	public BigDecimal getWaverate() {
		return this.waverate;
	}

	public void setWaverate(BigDecimal waverate) {
		this.waverate = waverate;
	}

}