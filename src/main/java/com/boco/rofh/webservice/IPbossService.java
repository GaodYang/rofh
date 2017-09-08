package com.boco.rofh.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IPbossService {

	/**
	 * 资源配置
	 * @param sRegionId
	 * @param sReceiveMsg
	 * @return
	 */
	@WebMethod(action="http://webservice.rofh.boco.com/configTaskReq")
	public String configTaskReq(@WebParam(name = "sReceiveMsg")String sReceiveMsg,@WebParam(name = "sRegionId")String sRegionId);
	
	/**
	 * 资源报俊
	 * @param sRegionId
	 * @param sReceiveMsg
	 * @return
	 */
	@WebMethod(action="http://webservice.rofh.boco.com/completeTaskReq")
	public String completeTaskReq(@WebParam(name = "sReceiveMsg")String sReceiveMsg,@WebParam(name = "sRegionId")String sRegionId);
	
	/**
	 * 资源撤单
	 * @param sRegionId
	 * @param sReceiveMsg
	 * @return
	 */
	@WebMethod(action="http://webservice.rofh.boco.com/cancelTaskReq")
	public String cancelTaskReq(@WebParam(name = "sReceiveMsg")String sReceiveMsg,@WebParam(name = "sRegionId")String sRegionId);
}
