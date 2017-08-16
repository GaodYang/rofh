package com.boco.rofh.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IActiveService {

	public String inform(@WebParam(name = "system")String system,@WebParam(name = "msgType")String msgType,@WebParam(name = "msgBody")String msgBody);
}
