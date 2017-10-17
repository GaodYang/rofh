package com.boco.rofh.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ICrmService {

	/**
	 * 4.2 标准地址查询（树结构方式）
	 * @param sReceiveMsg
	 * @return
	 */
	@WebMethod(action="http://webservice.rofh.boco.com/queryAddressTree")
	public String queryAddressTree(@WebParam(name = "sReceiveMsg")String sReceiveMsg,@WebParam(name = "sRegionId")String sRegionId);
	
	/**
	 * 4.3 标准地址查询（模糊方式）
	 * @param sReceiveMsg
	 * @return
	 */
	@WebMethod(action="http://webservice.rofh.boco.com/queryAddress")
	public String queryAddress(@WebParam(name = "sReceiveMsg")String sReceiveMsg,@WebParam(name = "sRegionId")String sRegionId);
	
	/**
	 * 4.3 标准地址查询（模糊方式）
	 * @param sReceiveMsg
	 * @return
	 */
	@WebMethod(action="http://webservice.rofh.boco.com/queryAddressCount")
	public int queryAddressCount(@WebParam(name = "sReceiveMsg")String sReceiveMsg,@WebParam(name = "sRegionId")String sRegionId);
	/**
	 * 查询资源和端口
	 * @param sReceiveMsg
	 * @return
	 */
	@WebMethod(action="http://webservice.rofh.boco.com/queryBroadPlan")
	public String queryBroadPlan(@WebParam(name = "sReceiveMsg")String sReceiveMsg,@WebParam(name = "sRegionId")String sRegionId);
	
	/**
	 * 用户端口占用查询
	 * @param sReceiveMsg
	 * @return
	 */
	@WebMethod(action="http://webservice.rofh.boco.com/queryUserPlan")
	public String queryUserPlan(@WebParam(name = "sReceiveMsg")String sReceiveMsg,@WebParam(name = "sRegionId")String sRegionId);
	/**
	 * 资源端口占用
	 * @param sReceiveMsg
	 * @return
	 */
	@WebMethod(action="http://webservice.rofh.boco.com/occupyProdSrvInv")
	public String occupyProdSrvInv(@WebParam(name = "sReceiveMsg")String sReceiveMsg,@WebParam(name = "sRegionId")String sRegionId);
	
	/**
	 * 树形结构，查询父地址
	 * @param sReceiveMsg
	 * @param sRegionId
	 * @return
	 */
	@WebMethod(action="http://webservice.rofh.boco.com/queryAddressParentIds")
	public String queryAddressParentIds(@WebParam(name = "sReceiveMsg")String sReceiveMsg,@WebParam(name = "sRegionId")String sRegionId);
	
	public String queryAddressAttr(String sReceiveMsg,String sRegionId);
}
