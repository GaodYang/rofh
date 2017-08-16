package com.boco.rofh.webservice.service;

import java.lang.reflect.ParameterizedType;

import org.apache.log4j.Logger;

import com.boco.rofh.exception.UserException;
import com.boco.rofh.webservice.pojo.CommonResult;
import com.boco.rofh.webservice.pojo.CommonRootReq;


abstract class BaseRofhWebService<T,E> {

	private final static Logger logger = Logger.getLogger(BaseRofhWebService.class);
	
	private ThreadLocal<String> regionId = new ThreadLocal<String>();
	
	//处理
	protected abstract E doBusiness(T req);
	
	public String doProcess(String xml,String regionId){
			
		logger.info("收到的报文：" + xml);
		logger.info("regionId：" + regionId);
		
		this.regionId.set(regionId);

		//将报文信息转换为实体
		CommonRootReq<T> root = new CommonRootReq<T>();
		
		//返回实体
		CommonResult<E> commenResult = new CommonResult<E>();
		try{
			//解析
			@SuppressWarnings("unchecked")
			Class<T> cls = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			root = root.toBean(xml,cls);
		}catch(Exception e){
			
			logger.error("报文解析失败！", e);
			buildErrorResp(commenResult,e);
			return sendRespMsg(commenResult);
		}
		
		logger.info("报文转换后的实体为:" + root);
		
		
		try {
				commenResult.setResultCode(1);
				commenResult.setResultDesc("处理成功");
				//业务逻辑
				commenResult.setResultContent(doBusiness(root.getMsgContent()));
			
		}  catch(UserException e){
			
			commenResult.setResultCode(0);
			commenResult.setResultDesc(e.getMessage());
			logger.error(e.getMessage());
		}catch (Exception e) {
			
			buildErrorResp(commenResult,e);
			logger.error("系统异常！" ,e);
		}
		
		
		//结果消息返回
		return sendRespMsg(commenResult);
		
	}
	/**
	 * 构造返回消息xml
	 * @param commenResult
	 * @return
	 */
	
	private void buildErrorResp(CommonResult<E> commenResult , Exception e ){
		
		commenResult.setResultCode(0);
		String msg = e.getMessage() == null ? e.toString() : e.getMessage();
		msg = msg.length() > 200 ? msg.substring(0,200) : msg;
		commenResult.setResultDesc("系统异常:" + msg);
	}
	
	private  String sendRespMsg(CommonResult<E> commenResult){
		
		String returnMsg = commenResult.toXml();
		return returnMsg;
	}
	
	public String getRegionId() {
		return regionId.get();
	}
	
	
}
