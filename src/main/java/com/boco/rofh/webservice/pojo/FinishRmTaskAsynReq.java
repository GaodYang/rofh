package com.boco.rofh.webservice.pojo;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.boco.rofh.utils.XStreamUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 回单信息
 * @author gaoyang
 *
 */
@XStreamAlias("root")
public class FinishRmTaskAsynReq {
	
	@XStreamAlias("prod_list")
	private List<FProdInfo> prodList;
	
	
	
	public List<FProdInfo> getProdList() {
		return prodList;
	}

	public void setProdList(List<FProdInfo> prodList) {
		this.prodList = prodList;
	}

	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
	@XStreamAlias("prod_info")
	public static class FProdInfo{
		
		//用户id
		@XStreamAlias("prod_ins_id")
		private String prodInsId;
		
		@XStreamAlias("ressrv_list")
		private List<Ressrv> ressrvList;
		
		
		
		public String getProdInsId() {
			return prodInsId;
		}



		public void setProdInsId(String prodInsId) {
			this.prodInsId = prodInsId;
		}



		public List<Ressrv> getRessrvList() {
			return ressrvList;
		}



		public void setRessrvList(List<Ressrv> ressrvList) {
			this.ressrvList = ressrvList;
		}



		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}
	}
	
	@XStreamAlias("ressrv")
	public static class Ressrv{
		
		//资源服务ID
		@XStreamAlias("ressrv_id")
		private String ressrvId;
		
		//资源服务名称
		@XStreamAlias("ressrv_name")
		private String ressrvName;
		
		////资源服务编码
		@XStreamAlias("ressrv_code")
		private String ressrvCode;
		
		//资源服务状态
		@XStreamAlias("ressrv_state")
		private String ressrvState;
		
		@XStreamAlias("res_list")
		private List<Res> resList;
		
		
		
		public String getRessrvId() {
			return ressrvId;
		}



		public void setRessrvId(String ressrvId) {
			this.ressrvId = ressrvId;
		}



		public String getRessrvName() {
			return ressrvName;
		}



		public void setRessrvName(String ressrvName) {
			this.ressrvName = ressrvName;
		}



		public String getRessrvCode() {
			return ressrvCode;
		}



		public void setRessrvCode(String ressrvCode) {
			this.ressrvCode = ressrvCode;
		}



		public String getRessrvState() {
			return ressrvState;
		}



		public void setRessrvState(String ressrvState) {
			this.ressrvState = ressrvState;
		}



		public List<Res> getResList() {
			return resList;
		}



		public void setResList(List<Res> resList) {
			this.resList = resList;
		}



		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}
	}
	
	@XStreamAlias("res")
	public static class Res{
		
		//资源ID
		@XStreamAlias("res_id")
		private String resId;
		
		//资源编码
		@XStreamAlias("res_code")
		private String resCode;
		
		//资源规格编码
		@XStreamAlias("res_spec_code")
		private String resSpecCode;
		
		//资源规格名称
		@XStreamAlias("res_spec_name")
		private String resSpecName;
		
		//资源状态
		@XStreamAlias("res_state")
		private String resState;
		
		//资源动作
		@XStreamAlias("res_act")
		private String resAct;
		
		//扩展属性列表
		@XStreamAlias("res_attr_list")
		private List<ResAttr> resAttrList;

		
		
		public String getResId() {
			return resId;
		}



		public void setResId(String resId) {
			this.resId = resId;
		}



		public String getResCode() {
			return resCode;
		}



		public void setResCode(String resCode) {
			this.resCode = resCode;
		}



		public String getResSpecCode() {
			return resSpecCode;
		}



		public void setResSpecCode(String resSpecCode) {
			this.resSpecCode = resSpecCode;
		}



		public String getResSpecName() {
			return resSpecName;
		}



		public void setResSpecName(String resSpecName) {
			this.resSpecName = resSpecName;
		}



		public String getResState() {
			return resState;
		}



		public void setResState(String resState) {
			this.resState = resState;
		}



		public String getResAct() {
			return resAct;
		}



		public void setResAct(String resAct) {
			this.resAct = resAct;
		}



		public List<ResAttr> getResAttrList() {
			return resAttrList;
		}



		public void setResAttrList(List<ResAttr> resAttrList) {
			this.resAttrList = resAttrList;
		}



		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}
	}
	
	@XStreamAlias("res_attr")
	public static class ResAttr{
		
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
	
	/**
	 * 转为xml
	 * @param cls 泛型的class 
	 * @return
	 */
	public String toXml(){

		return XStreamUtil.toXml(this,null);
	}
}
