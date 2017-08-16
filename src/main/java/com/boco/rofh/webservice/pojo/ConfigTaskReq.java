package com.boco.rofh.webservice.pojo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.boco.rofh.utils.DateTimeConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 资源配置
 * @author gaoyang
 *
 */
@XStreamAlias("ConfigTaskReq")
public class ConfigTaskReq {

	@XStreamAlias("req_info")
	private ReqInfo reqInfo;
	
	@XStreamAlias("prod_info_list")
	private List<ProdInfo> prodInfoList;
	
	@XStreamOmitField
	private Map<String,String> detialMap;
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
	public ReqInfo getReqInfo() {
		return reqInfo;
	}

	public void setReqInfo(ReqInfo reqInfo) {
		this.reqInfo = reqInfo;
	}

	public List<ProdInfo> getProdInfoList() {
		return prodInfoList;
	}

	public void setProdInfoList(List<ProdInfo> prodInfoList) {
		this.prodInfoList = prodInfoList;
	}

	public static class ReqInfo{
		
		@XStreamAlias("act_code")
		//@FieldNote(name="产品操作编码")
		private String actCode;
		
		@XStreamAlias("src_task_id")
		private String srcTaskId;
		
		@XStreamAlias("src_order_id")
		private String srcOrderId;
		
		@XStreamAlias("district_id")
		private String districtId;
		
		@XStreamAlias("conf_kind")
		private String confKind;
		
		@XStreamAlias("alarm_date")
		@XStreamConverter(value=DateTimeConverter.class,strings={"yyyy-MM-dd HH:mm:ss"})
		private Date alarmDate;
		
		@XStreamAlias("delay_date")
		@XStreamConverter(value=DateTimeConverter.class,strings={"yyyy-MM-dd HH:mm:ss"})
		private Date delayDate;
		
		//组织机构
		@XStreamAlias("org_id")
		private String orgId;
		
		@XStreamAlias("exception_reason")
		//@FieldNote(name="异常原因",isNullAble=false)
		private String exceptionReason;
		
		@XStreamAlias("exception_flag")
		//@FieldNote(name="异常标识",isNullAble=false)
		private String exceptionFlag;
		
		@XStreamAlias("exception_code")
		//@FieldNote(name="异常编码",isNullAble=false)
		private String exceptionCode;
		
		@XStreamAlias("req_attr_list")
		private List<ReqAttr> reqAttrList;

		

		public String getActCode() {
			return actCode;
		}



		public void setActCode(String actCode) {
			this.actCode = actCode;
		}



		public String getSrcTaskId() {
			return srcTaskId;
		}



		public void setSrcTaskId(String srcTaskId) {
			this.srcTaskId = srcTaskId;
		}



		public String getSrcOrderId() {
			return srcOrderId;
		}



		public void setSrcOrderId(String srcOrderId) {
			this.srcOrderId = srcOrderId;
		}



		public String getDistrictId() {
			return districtId;
		}



		public void setDistrictId(String districtId) {
			this.districtId = districtId;
		}



		public String getConfKind() {
			return confKind;
		}



		public void setConfKind(String confKind) {
			this.confKind = confKind;
		}



		public Date getAlarmDate() {
			return alarmDate;
		}



		public void setAlarmDate(Date alarmDate) {
			this.alarmDate = alarmDate;
		}



		public Date getDelayDate() {
			return delayDate;
		}



		public void setDelayDate(Date delayDate) {
			this.delayDate = delayDate;
		}



		public String getOrgId() {
			return orgId;
		}



		public void setOrgId(String orgId) {
			this.orgId = orgId;
		}



		public String getExceptionReason() {
			return exceptionReason;
		}



		public void setExceptionReason(String exceptionReason) {
			this.exceptionReason = exceptionReason;
		}



		public String getExceptionFlag() {
			return exceptionFlag;
		}



		public void setExceptionFlag(String exceptionFlag) {
			this.exceptionFlag = exceptionFlag;
		}



		public String getExceptionCode() {
			return exceptionCode;
		}



		public void setExceptionCode(String exceptionCode) {
			this.exceptionCode = exceptionCode;
		}



		public List<ReqAttr> getReqAttrList() {
			return reqAttrList;
		}



		public void setReqAttrList(List<ReqAttr> reqAttrList) {
			this.reqAttrList = reqAttrList;
		}



		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}
	}
	
	@XStreamAlias("attr")
	public static class ReqAttr{
		
		@XStreamAlias("attr_code")
		private String attrCode;
		
		@XStreamAlias("attr_val")
		private String attrVal;
		
		@XStreamAlias("attr_val_desc")
		private String attrValDesc;

		public String getAttrCode() {
			return attrCode;
		}

		public void setAttrCode(String attrCode) {
			this.attrCode = attrCode;
		}

		public String getAttrVal() {
			return attrVal;
		}

		public void setAttrVal(String attrVal) {
			this.attrVal = attrVal;
		}

		public String getAttrValDesc() {
			return attrValDesc;
		}

		public void setAttrValDesc(String attrValDesc) {
			this.attrValDesc = attrValDesc;
		}
		
		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}

	}
	@XStreamAlias("prod_info")
	public static class ProdInfo{
		
		@XStreamAlias("prod_ins_id")
		private String prodInsId;
		
		@XStreamAlias("access_num")
		//@FieldNote(name="接入号码",isNullAble=false)
		private String accessNum;
		
		@XStreamAlias("standard_addr_id")
		//@FieldNote(name="标准地址id")
		private String standardAddrId;
		
		@XStreamAlias("main_prod_flag")
		//@FieldNote(name="主产品标志")
		private String mainProdFlag;
		
		@XStreamAlias("main_prod_id")
		//@FieldNote(name="主产品ID",isNullAble=false)
		private String mainProdId;
		
		@XStreamAlias("prod_srv_spec_code")
		private String prodSrvSpecCode;
		
		@XStreamAlias("onu_id")
		private String onuId;
		
		@XStreamAlias("prod_attr_list")
		private List<ReqAttr> prodAttrList;

		
		
		public String getProdInsId() {
			return prodInsId;
		}



		public void setProdInsId(String prodInsId) {
			this.prodInsId = prodInsId;
		}



		public String getAccessNum() {
			return accessNum;
		}



		public void setAccessNumb(String accessNum) {
			this.accessNum = accessNum;
		}



		public String getStandardAddrId() {
			return standardAddrId;
		}



		public void setStandardAddrId(String standardAddrId) {
			this.standardAddrId = standardAddrId;
		}



		public String getMainProdFlag() {
			return mainProdFlag;
		}



		public void setMainProdFlag(String mainProdFlag) {
			this.mainProdFlag = mainProdFlag;
		}



		public String getMainProdId() {
			return mainProdId;
		}



		public void setMainProdId(String mainProdId) {
			this.mainProdId = mainProdId;
		}



		public String getProdSrvSpecCode() {
			return prodSrvSpecCode;
		}



		public void setProdSrvSpecCode(String prodSrvSpecCode) {
			this.prodSrvSpecCode = prodSrvSpecCode;
		}



		public List<ReqAttr> getProdAttrList() {
			return prodAttrList;
		}



		public void setProdAttrList(List<ReqAttr> prodAttrList) {
			this.prodAttrList = prodAttrList;
		}



		public String getOnuId() {
			return onuId;
		}



		public void setOnuId(String onuId) {
			this.onuId = onuId;
		}


		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}


	}
	public Map<String, String> getDetialMap() {
		return detialMap;
	}

	public void setDetialMap(Map<String, String> detialMap) {
		this.detialMap = detialMap;
	}
	
	
}
