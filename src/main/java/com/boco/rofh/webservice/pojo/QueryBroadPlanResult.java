package com.boco.rofh.webservice.pojo;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 4.4.2 设备资源及端口查询 响应
 * @author gaoyang
 *
 */
@XStreamAlias("broadPlanResult")
public class QueryBroadPlanResult {

	@XStreamAlias("prod_srv_list")
	private List<ProdServInfo> prodSrvList;
	
	@XStreamAlias("prod_serv_info")
	public static class ProdServInfo implements Cloneable{
		
		//产品服务编码
		@XStreamAlias("prod_srv_code")
		private String prodSrvCode;
		
		//产品服务名称
		@XStreamAlias("prod_srv_name")
		private String prodSrvName;
		
		//存量数量
		@XStreamAlias("inv_num")
		private int invNum;
		
		@XStreamOmitField
		private  String type;
		
		public String getProdSrvCode() {
			return prodSrvCode;
		}

		public void setProdSrvCode(String prodSrvCode) {
			this.prodSrvCode = prodSrvCode;
		}

		public String getProdSrvName() {
			return prodSrvName;
		}

		public void setProdSrvName(String prodSrvName) {
			this.prodSrvName = prodSrvName;
		}

		public int getInvNum() {
			return invNum;
		}

		public void setInvNum(int invNum) {
			this.invNum = invNum;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}
		
		@Override
		public ProdServInfo clone() {
			
			try {
				return (ProdServInfo)super.clone();
				
			} catch (CloneNotSupportedException e) {
				
				return new ProdServInfo();
			}
			
		}
		
	}
	
	
	
	public List<ProdServInfo> getProdSrvList() {
		return prodSrvList;
	}



	public void setProdSrvList(List<ProdServInfo> prodSrvList) {
		this.prodSrvList = prodSrvList;
	}



	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
}
