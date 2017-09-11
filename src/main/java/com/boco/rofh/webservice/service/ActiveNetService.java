package com.boco.rofh.webservice.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.dao.ActiveDao;
import com.boco.rofh.dao.OnuDao;
import com.boco.rofh.dao.OrderDao;
import com.boco.rofh.dao.PonWayDao;
import com.boco.rofh.dao.PosDao;
import com.boco.rofh.dao.ProductDao;
import com.boco.rofh.dao.PtpDao;
import com.boco.rofh.entity.AnOnuAttemp;
import com.boco.rofh.entity.AnPos;
import com.boco.rofh.entity.PonWayAttemp;
import com.boco.rofh.entity.RofhActivate;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhOrder;
import com.boco.rofh.entity.RofhProduct;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.exception.UserException;
import com.boco.rofh.mapper.ActiveMapper;
import com.boco.rofh.utils.ActiveServiceUtil;
import com.boco.rofh.utils.ActiveThreadUtil;
import com.boco.rofh.utils.FinishMsgBuilder;
import com.boco.rofh.webservice.pojo.ActiveMsgBody;
import com.boco.rofh.webservice.pojo.ActiveReq;
import com.boco.rofh.webservice.pojo.ActiveReq.Device;
import com.boco.rofh.webservice.pojo.ActiveReq.NewResData;
import com.boco.rofh.webservice.pojo.ActiveReq.Olt;
import com.boco.rofh.webservice.pojo.ActiveReq.Product;
import com.boco.rofh.webservice.pojo.ActiveReq.SvcFulfillMsg;
import com.boco.rofh.webservice.pojo.ActiveResult;

@Service
public class ActiveNetService {
	
	@Autowired
	private ActiveMapper activeMapper;
	
	@Autowired
	private ActiveDao activeDao;
	
	@Autowired
	private PonWayDao<PonWayAttemp> attempPonWayDao;
	
	@Autowired
	private PosDao posDao;
	
	@Autowired
	private ProductDao<RofhProductAttemp> attempProductDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private FinishMsgBuilder builder;
	
	@Autowired
	private PtpDao ptpDao;
	
	@Autowired
	private OnuDao<AnOnuAttemp> attempOnuDao;
	
	@Autowired
	private FinishRmTaskAsynService finishRmTaskAsynService;
	
	@Autowired
	private ActiveServiceUtil activeServiceUtil;
	
	private static final Logger logger = LoggerFactory.getLogger(ActiveNetService.class);
	
	public String getMsgBody(RofhBean rofhBean,RofhActivate activateBean,boolean isAdd){
		
		String pBossStr = "";
		try {
			if (activateBean == null){
				return pBossStr;
			}
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String posL1Name = "";
			String posL1Ratio = "";
			AnPos pos = posDao.findOne(rofhBean.getProduct().getAccessDevice());
			if(pos != null){
				posL1Name = pos.getLabelCn();
				posL1Ratio = pos.getRation();
			}

			String city = activeMapper.getDistrictById(rofhBean.getProduct().getBusinessCityD());
			
			ActiveReq activeReq = new ActiveReq();
			SvcFulfillMsg fulfillMsg = new SvcFulfillMsg();
			activeReq.setSvcFulfillMsg(fulfillMsg);
			
			fulfillMsg.setSheetCode(activateBean.getCuid());
			fulfillMsg.setSheetMainCode(rofhBean.getOrder().getCrmSheetNo());
			fulfillMsg.setSheetTitle("");
			fulfillMsg.setOperType(isAdd ? "1" : "3");
			fulfillMsg.setDeadlineTime(df.format(new Date()));
			fulfillMsg.setCityName(city);
			Product product = new Product();
			fulfillMsg.setProduct(product);
			
			product.setOntType("HGU");
			product.setProductCode(rofhBean.getProduct().getProductCode());
			product.setProductType("1001");
			product.setBusinessCity(city);
			product.setCustomerName(rofhBean.getCustomer().getLabelCn());
			product.setCustomerCode(rofhBean.getCustomer().getCustomerCode());
			product.setCustomerCity(city);
			product.setCustomerAddress(rofhBean.getCustomer().getCustomerAddress());
			product.setRegionName(city);
			product.setNettopoType("ff8081815c6be399015c6beba0940011");
			
			Device device = new Device();
			device.setDeviceName(activateBean.getRelatedOltName());
			device.setDeviceType("OLT");
			
			List<Olt> attribute = new ArrayList<Olt>();
			device.setAttribute(attribute);
			
			Olt olt = new Olt();
			olt.setEms(activateBean.getStandardName());
			olt.setAdminIp(activateBean.getRelatedOltDevip());
			olt.setPonPort(activateBean.getOltPortName());
			olt.setPosL1Name(posL1Name);
			olt.setPosL1Ratio(posL1Ratio);
			olt.setPosL2Name("1");
			olt.setPosL2Ratio("1");
			olt.setPosPonPort("1");
			olt.setOnuOper(isAdd ? "1" : "2");
			olt.setOnuType("1");
			olt.setOnuPassword(activateBean.getLogicid());
			olt.setOnuId(activateBean.getRelatedOnuCuid());
			olt.setOnuName(activateBean.getOnuName());
			olt.setOnuCustPort(activateBean.getOnuPortName());
			olt.setSvlan(activateBean.getSvlan());
			olt.setCvlan(activateBean.getCvlan());
			olt.setOnuFactory(activateBean.getOnuVender());
			attribute.add(olt);
			
			NewResData newResData = new NewResData();
			List<Device> deviceList = new ArrayList<Device>();
			newResData.setDeviceList(deviceList);
			deviceList.add(device);
			fulfillMsg.setNewResData(newResData);
			
			pBossStr = activeReq.toXml();
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserException("调用激活,拼接报文出错"+e.getMessage());
		}
		return pBossStr;
	}
	
	
	public RofhActivate saveInitializeData(RofhBean rofhBean,boolean isAdd) {
		
		PonWayAttemp attempPonWay = attempPonWayDao.findByProductCuid(rofhBean.getProduct().getCuid());
		if (attempPonWay != null){
			
			RofhActivate activateBean = new RofhActivate();
			activateBean.setRelatedOltCuid(attempPonWay.getRelatedOltCuid());
			Map<String, String> map = activeMapper.getOltById(attempPonWay.getRelatedOltCuid());
		    activateBean.setRelatedOltDevip((String) map.get("RELATED_OLT_DEVIP"));
			activateBean.setRelatedOltName((String) map.get("RELATED_OLT_NAME"));
			activateBean.setStandardName((String) map.get("EMS_NAME"));// EMS的值需要确定
			activateBean.setOltPortName(ptpDao.findLabelCnByCuid(attempPonWay.getOltPonPortCuid()));
			activateBean.setRelatedSheetCuid(rofhBean.getOrder().getCrmSheetNo());
			activateBean.setRelatedTaskCuid(rofhBean.getOrder().getCrmTaskId());
			activateBean.setRelatedOrderCuid(rofhBean.getOrder().getCuid());
			activateBean.setActivateState("10");
			activateBean.setDataType("1");
			activateBean.setRelatedOnuCuid(attempPonWay.getRelatedOnuCuid());
			activateBean.setRelatedOnuPortCuid(attempPonWay.getOnuPortCuid());
			
			activateBean.setOnuName(attempOnuDao.findLabelCnById(attempPonWay.getRelatedOnuCuid()));
			
			activateBean.setOnuPortName(ptpDao.findLabelCnByCuid(attempPonWay.getOnuPortCuid()));
			
			activateBean.setSvlan(attempPonWay.getSvlan());
			activateBean.setCvlan(attempPonWay.getCvlan());
			activateBean.setRelatedOltPortCuid(attempPonWay.getOltPonPortCuid());
			activateBean.setOnuVender((String) map.get("DLABEL_CN"));
			activateBean.setLogicid(attempPonWay.getAccountPassword());
			if(isAdd){
				activateBean.setActivateTime(new Date());
			}else{
				activateBean.setDeactivateTime(new Date());
			}
			
			activeDao.save(activateBean);
			return activateBean;
		} else {
			return null;
		}
	}

	
	public String netActivate(RofhBean rofhBean,boolean isAdd){
		
		RofhActivate activateBean ;
		List<RofhActivate> list = activeDao.findByOrderid(rofhBean.getProduct().getRelatedOrderCuid());
		
		if(list != null && list.size() > 0){
			
			activateBean = list.get(0);
		}else if (isAdd){//代表激活，还没有对应的 激活数据
			
			try {
			
				activateBean = saveInitializeData(rofhBean,isAdd);
			} catch (Exception e) {
				
				logger.error("激活数据异常，不影响流程",e);
				return "pon口不存在";
			}
		} else {
			
			return "未找到对应的激活数据";
		}

		return netActivate(rofhBean,activateBean,isAdd);
	}
	
	
	/**
	 * 激活状态
	 * [10未激活,20激活申请成功{22成功，23失败，24正在激活中}，21激活失败]
	 * [30去激活申请成功{32成功，33失败，34正在去激活中}，31激活失败]
	 */
	public String getInform(String system, String msgType, String msgBody) {
		
		String errorMessage = "";
		long startTime = System.currentTimeMillis();
		Date startDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		logger.info("======激活调用家客修改激活状态接口开始======,报文信息：" + msgBody);
		
		try {
			ActiveMsgBody activeResult = ActiveMsgBody.toBean(msgBody);;

			// 通过环节ID取出所有激活数据根据CUID便利修改每条数据对应的激活状态
			RofhActivate activateBean = activeDao.findOne(activeResult.getInformMsg().getRelId());
			
			ActiveThreadUtil.instance.remove(activateBean.getCuid());
		
			if (activeResult.getInformMsg().getRtCode().equals("000")) {
				
				activateBean.setActivateStatus("已完成");
			} else {
				
				activateBean.setActivateStatus("激活异常");
			}
			activateBean.setReturnActivateTime(new Date());
			
			String code = activeResult.getInformMsg().getRtCode();
			
			if (WebServiceConstant.ACTIVATESTATE.get(activateBean.getActivateState() + "-" + code) != null) {
				
				activateBean.setActivateState(WebServiceConstant.ACTIVATESTATE.get(activateBean.getActivateState() + "-" + code));
				
			}
			activateBean.setActivateResult(activeResult.getInformMsg().getRtMessage());
			activeDao.save(activateBean);
			
			RofhProduct product = attempProductDao.findByOrderId(activateBean.getRelatedOrderCuid());
			if (product != null ){
				
				RofhBean processBean = new RofhBean();
				RofhOrder order = orderDao.findOne(activateBean.getRelatedOrderCuid());
				processBean.setProduct(product);
				processBean.setOrder(order);
				processBean.setActiveMsg(activeResult.getInformMsg().getRtMessage());

				String xml = builder.buildFinishMsg(processBean);
				finishRmTaskAsynService.sendXmlToPboss(order.getCrmTaskId(),xml,processBean.getProduct().getRemark());
				
			}
		} catch (Exception e) {
			
			logger.error("",e);
			errorMessage = "解析报文出错！";
			return opdetailCreator("001", errorMessage, "");
		}
		Date endDate  = new Date();
		logger.info("======调用激活接口结束======开始时间："+sdf.format(startDate)+",结束时间："+sdf.format(endDate)+",花费时间："+ (System.currentTimeMillis() - startTime) + " 毫秒");
		return opdetailCreator("000", "", "");
	}
	
	
	private String opdetailCreator(String rtCode,String rtMessage,String rtEntity) {
		
		ActiveResult result = new ActiveResult();
		result.setRtCode(rtCode);
		result.setRtEntity(rtEntity);
		result.setRtMessage(rtMessage);
		return result.toXml();
	}


	public String netActivate(RofhBean processBean,RofhActivate activateBean,boolean isAdd){

		ActiveResult activeResult = null;

		try {

			String msgBody = getMsgBody(processBean,activateBean,isAdd);
			logger.info("激活接口开始调用：" + new Date() + ",报文信息：" + msgBody);
			String result = activeServiceUtil.send(msgBody);
		    logger.info("激活接口返回结果：" + result);
			activeResult = ActiveResult.toBean(result);
		}	catch (Exception e) {
			
			logger.error("调用激活接口失败",e);
			updateActivateByState(activateBean,"调用激活接口失败","0");
			return "调用激活接口调用失败";
		}
		
		String rtCode = activeResult.getRtCode();
		if("000".equals(rtCode)){
			
			logger.info("激活接口业务处理成功");
			updateActivateByState(activateBean,activeResult.getRtMessage(),"1");
			if(isAdd){
				ActiveThreadUtil.instance.putThread(activateBean.getCuid());
			}
			return null;
		}else {//当激活返回值等于001 -1修改生成激活数据
			updateActivateByState(activateBean,activeResult.getRtMessage(),"0");
		}	
		
		return activeResult.getRtMessage();
	}
	
	public void updateActivateByState(RofhActivate activateBean,String error,String state){
		try {
			logger.info("调用激活接口返回值" + state + "...astate" + activateBean.getActivateState() + "error信息：" + error);
			String oldState = activateBean.getActivateState();
			// 1激活成功修改激活状态
			if("1".equals(state)){
				if(oldState.equals("10") || oldState.equals("21")){
					activateBean.setActivateState("20");
					activateBean.setActivateResult("激活申请成功");
				}else if(oldState.equals("22") || oldState.equals("31")){
					activateBean.setActivateState("30");
					activateBean.setActivateResult("去激活申请成功");
				}
				activateBean.setActivateStatus("已派发");
			}else if("0".equals(state)){
				if(oldState.equals("10"))	{
					activateBean.setActivateState("21");
					activateBean.setActivateResult(error);
				}else if(oldState.equals("22")){
					activateBean.setActivateState("31");
					activateBean.setActivateResult(error);
				}
				activateBean.setActivateStatus("激活失败");
			}
			activeDao.save(activateBean);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	
}
