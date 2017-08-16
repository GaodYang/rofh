package com.boco.rofh.webservice.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.dao.PbossFinishDao;
import com.boco.rofh.entity.WebservicePbossFinish;
import com.boco.rofh.utils.PbossServiceUtil;
import com.boco.rofh.webservice.pojo.CommonResult;

/**
 * 回单操作
 * @author gaoyang
 */

@Service
public class FinishRmTaskAsynService {

	private static final Logger logger = LoggerFactory.getLogger(FinishRmTaskAsynService.class);
	
	@Autowired
	private PbossServiceUtil pbossServiceUtil;
	
	@Autowired
	private PbossFinishDao finishDao;


	public void sendXmlToPboss(String taskId,String xml,String regionId) {
		
		try {
			
			String result = "";
			logger.info(xml);
			result = pbossServiceUtil.sendSuccess(Long.parseLong(taskId), xml, regionId);
			
			this.insertRecode(taskId, xml, result, true,null,regionId);
			
		} catch (Exception e) {
			this.insertRecode(taskId, xml, null, false,e.getMessage(),regionId);
			logger.error("回单出错！",e);
		}
	}
	
	public void sendErrorXmlToPboss(String taskId,String excpCode,String errorDesc,String regionId){
		
		logger.info("error : " + errorDesc);
		try {
			CommonResult res = new CommonResult();
			res.setResultCode(0);
			res.setResultDesc(errorDesc);
			
			String result = pbossServiceUtil.sendError(Long.parseLong(taskId), excpCode, errorDesc, res.toXml(), regionId);
			this.insertRecode(taskId, res.toXml(), result, true, null,regionId);
		} catch (Exception e) {
			this.insertRecode(taskId, null, null, false,e.getMessage(),regionId);
			logger.error("回单出错！",e);
		}
	}
	
	private void insertRecode(String taskId,String reqXml,String respXml,boolean success,String errorReason,String regionId){
		
		WebservicePbossFinish bean;
		if(success){
			bean = WebservicePbossFinish.toBean(respXml);
			bean.setSuccess("1");
		}else{
			bean = new WebservicePbossFinish();
			bean.setSuccess("0");
		}
		
		bean.setCreateTime(new Date());
		bean.setLastModifyTime(new Date());
		bean.setRelatedTaskCuid(taskId);
		bean.setReqXml(reqXml);
		bean.setErrorReason(errorReason);
	
		bean.setRespXml(respXml);
		bean.setRegionId(regionId);
		finishDao.save(bean);
	}
	
	public void reSendXmlToPboss(String cuid,String taskId,String xml,String regionId) {
		try {
			String result = pbossServiceUtil.sendSuccess(Long.parseLong(taskId), xml, regionId);
			this.updateRecode(cuid, result, true,null);
		} catch (Exception e) {
			this.updateRecode(cuid, null, false,e.getMessage());
			logger.error("回单出错！",e);
		}
	}
	
	private void updateRecode(String cuid,String respXml,boolean success,String errorReason){
		
		WebservicePbossFinish bean;
		if(success){
			bean = WebservicePbossFinish.toBean(respXml);
			bean.setSuccess("1");
		}else{
			bean = new WebservicePbossFinish();
			bean.setSuccess("0");
		}
		
		bean.setCuid(cuid);
		bean.setLastModifyTime(new Date());
		bean.setErrorReason(errorReason);	
		bean.setRespXml(respXml);

		finishDao.save(bean);
	}
	
}
