package com.boco.rofh.webservice.pojo;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 用户带宽占用查询结果
 * @author gaoyang
 *
 */
public class QueryUserPlanResult {

	@XStreamAlias("prod_srv_list")
	private List<ProdServInfo> prodSrvList;

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
	
	@XStreamAlias("prod_serv_info")
	public static class ProdServInfo{
		
		/**
		 * 实体规格标识
		 */
		@XStreamAlias("obj_spec_id")
		private String objSpecId;
		
		/**
		 * 实体规格标识
		 */
		@XStreamAlias("obj_spec_name")
		private String objSpecName;
		/**
		 * 资源类型
		 */
		@XStreamAlias("res_type")
		private String resType;
		
		/**
		 * 资源名称
		 */
		@XStreamAlias("name")
		private String name;
		
		/**
		 * 资源服务规格标识
		 */
		@XStreamAlias("res_srv_spec_id")
		private String resSrvSpecId;
		
		/**
		 * 存量标识
		 */
		@XStreamAlias("inv_id")
		private String invId;

		@XStreamAlias("res_info")
		private ResInfo resInfo;
		
		public String getObjSpecName() {
			return objSpecName;
		}

		public void setObjSpecName(String objSpecName) {
			this.objSpecName = objSpecName;
		}

		public ResInfo getResInfo() {
			return resInfo;
		}

		public void setResInfo(ResInfo resInfo) {
			this.resInfo = resInfo;
		}

		public String getObjSpecId() {
			return objSpecId;
		}

		public void setObjSpecId(String objSpecId) {
			this.objSpecId = objSpecId;
		}

		public String getResType() {
			return resType;
		}

		public void setResType(String resType) {
			this.resType = resType;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getResSrvSpecId() {
			return resSrvSpecId;
		}

		public void setResSrvSpecId(String resSrvSpecId) {
			this.resSrvSpecId = resSrvSpecId;
		}

		public String getInvId() {
			return invId;
		}

		public void setInvId(String invId) {
			this.invId = invId;
		}
		
		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}
	}
	
	public static class ResInfo{
		
		//端口标识
		@XStreamAlias("port_id")
		private String portId	;	
		//端口名称
		@XStreamAlias("port_name")
		private String portName	;
		//端口类型标识
		@XStreamAlias("port_type_id")
		private String portTypeId		;
		//端口类型名称
		@XStreamAlias("port_type_name")
		private String portTypeName	;
		//端口局向标识
		@XStreamAlias("port_exch_id")
		private String portExchId	;
		//端口序列号
		@XStreamAlias("port_serial_number")
		private String portSerialNumber	;	
		//端口局向
		@XStreamAlias("port_exch_name")
		private String portExchName	;	
		//设备名称
		@XStreamAlias("eqpt_name")
		private String eqptName	;	
		//设备种类标识
		@XStreamAlias("eqpt_kind_id")
		private String eqptKindId	;
		//设备种类名称
		@XStreamAlias("eqpt_kind_name")
		private String eqptKindName	;
		//设备地址
		@XStreamAlias("eqpt_addr")
		private String eqptAddr	;	
		//设备区域
		@XStreamAlias("eqpt_area_name")
		private String eqptAreaName	;	
		//所属机房
		@XStreamAlias("room_name")
		private String roomName	;	
		//面板名称
		@XStreamAlias("board_name")
		private String boardName	;	
		//面板规格
		@XStreamAlias("board_type_id")
		private String boardTypeId	;	
		
		
		public String getPortId() {
			return portId;
		}


		public void setPortId(String portId) {
			this.portId = portId;
		}


		public String getPortName() {
			return portName;
		}


		public void setPortName(String portName) {
			this.portName = portName;
		}


		public String getPortTypeId() {
			return portTypeId;
		}


		public void setPortTypeId(String portTypeId) {
			this.portTypeId = portTypeId;
		}


		public String getPortTypeName() {
			return portTypeName;
		}


		public void setPortTypeName(String portTypeName) {
			this.portTypeName = portTypeName;
		}


		public String getPortExchId() {
			return portExchId;
		}


		public void setPortExchId(String portExchId) {
			this.portExchId = portExchId;
		}


		public String getPortSerialNumber() {
			return portSerialNumber;
		}


		public void setPortSerialNumber(String portSerialNumber) {
			this.portSerialNumber = portSerialNumber;
		}


		public String getPortExchName() {
			return portExchName;
		}


		public void setPortExchName(String portExchName) {
			this.portExchName = portExchName;
		}


		public String getEqptName() {
			return eqptName;
		}


		public void setEqptName(String eqptName) {
			this.eqptName = eqptName;
		}


		public String getEqptKindId() {
			return eqptKindId;
		}


		public void setEqptKindId(String eqptKindId) {
			this.eqptKindId = eqptKindId;
		}


		public String getEqptKindName() {
			return eqptKindName;
		}


		public void setEqptKindName(String eqptKindName) {
			this.eqptKindName = eqptKindName;
		}


		public String getEqptAddr() {
			return eqptAddr;
		}


		public void setEqptAddr(String eqptAddr) {
			this.eqptAddr = eqptAddr;
		}


		public String getEqptAreaName() {
			return eqptAreaName;
		}


		public void setEqptAreaName(String eqptAreaName) {
			this.eqptAreaName = eqptAreaName;
		}


		public String getRoomName() {
			return roomName;
		}


		public void setRoomName(String roomName) {
			this.roomName = roomName;
		}


		public String getBoardName() {
			return boardName;
		}


		public void setBoardName(String boardName) {
			this.boardName = boardName;
		}


		public String getBoardTypeId() {
			return boardTypeId;
		}


		public void setBoardTypeId(String boardTypeId) {
			this.boardTypeId = boardTypeId;
		}


		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}

	}
}
