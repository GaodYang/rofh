package com.boco.rofh.webservice.pojo;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

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
	public static class ProdServInfo{
		
		//产品服务编码
		@XStreamAlias("prod_srv_code")
		private String prodSrvCode;
		
		//产品服务名称
		@XStreamAlias("prod_srv_name")
		private String prodSrvName;
		
		//存量数量
		@XStreamAlias("inv_num")
		private String invNum;
		

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

		public String getInvNum() {
			return invNum;
		}

		public void setInvNum(String invNum) {
			this.invNum = invNum;
		}

		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
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
