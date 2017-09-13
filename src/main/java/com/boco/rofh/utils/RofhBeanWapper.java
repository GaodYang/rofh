package com.boco.rofh.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.constant.WebServiceConstant.ProdSerType;
import com.boco.rofh.dao.ProdInfoDao;
import com.boco.rofh.dao.ProductDao;
import com.boco.rofh.entity.ProdServInfo;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhCustomer;
import com.boco.rofh.entity.RofhOrder;
import com.boco.rofh.entity.RofhProduct;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.webservice.pojo.ConfigTaskReq;
import com.boco.rofh.webservice.pojo.ConfigTaskReq.ProdInfo;
import com.boco.rofh.webservice.pojo.ConfigTaskReq.ReqAttr;
import com.boco.rofh.webservice.pojo.ConfigTaskReq.ReqInfo;


@Service
public class RofhBeanWapper {

	private static final String DEFAULTFORMAT = "yyyy-MM-dd HH:mm:ss";
	
	@Autowired
	private ProductDao<RofhProductAttemp> attempProductDao;
	
	@Autowired
	private ProdInfoDao prodInfoDao;
	
	private List<ProdServInfo> prodSerList;
	
	private Map<String,String> prodSerMap;
	/**
	 * 将请求实体转换为RofhProcessBean，都是为了复用流程啊
	 * @return
	 */
	public RofhBean Wapper2RofhBean(ConfigTaskReq taskReq,String regionId){
		
		ReqInfo reqInfo = taskReq.getReqInfo();
		ProdInfo prodInfo = taskReq.getProdInfoList().get(0);
		Map<String,String> detialAttrs = buildDetialAttr(reqInfo.getReqAttrList(),prodInfo.getProdAttrList());
		taskReq.setDetialMap(detialAttrs);
		
		RofhBean bean = new RofhBean();
		RofhCustomer customer = buildRofhCustomer(detialAttrs,prodInfo);
		bean.setCustomer(customer);
		
		RofhOrder order = buildOrder(reqInfo);
		bean.setOrder(order);
		
		RofhProduct product = buildRofhProduct(prodInfo, detialAttrs);
		bean.setProduct(product);
		product.setRemark(regionId);
		
		bean.setRegionId(regionId);
		
		bean.setAction(taskReq.getReqInfo().getActCode());
		bean.setDistrictId(reqInfo.getDistrictId());
		
		return bean;
	}
	
	
	/**
	 * 将扩展信息封装
	 * @param list1
	 * @param list2
	 * @return
	 */
	private Map<String,String> buildDetialAttr(List<ReqAttr> list1,List<ReqAttr> list2){
		
		Map<String,String> map = new HashMap<String, String>();
		
		for(ReqAttr req : list1){
			
			map.put(req.getAttrCode(), req.getAttrVal());
		}
		
		for(ReqAttr req : list2){
			
			map.put(req.getAttrCode(), req.getAttrVal());
		}
		
		return map;
	}
	
	
	/**
	 * 创建客户信息
	 * @param map
	 * @param reqInfo
	 * @param prodInfo
	 * @return
	 */
	private RofhCustomer buildRofhCustomer(Map<String,String> map,ProdInfo prodInfo){
		
		
		RofhCustomer rofhCustomer = new RofhCustomer();
		rofhCustomer.setCustomerContact(map.get("contact_man_1"));
		rofhCustomer.setContactNumber(map.get("contact_tel_1"));
		rofhCustomer.setLabelCn(map.get("cust_name"));
		
		rofhCustomer.setRelatedBmclasstypeCuid("T_ROFH_CUSTOMER");

		rofhCustomer.setCustomerCode(prodInfo.getProdInsId());//yonghu bianma
		rofhCustomer.setSecContactName(map.get("contact_man_2"));
		rofhCustomer.setSecContactNumber(map.get("contact_tel_2"));
		
		return rofhCustomer;
	}
	
	/**
	 * 创建订单信息
	 * @param processBean
	 * @param opDetail
	 * @return
	 */
	private RofhOrder buildOrder(ReqInfo reqInfo) {
		
		RofhOrder order = new RofhOrder();

		order.setCrmSheetNo(reqInfo.getSrcOrderId());
		order.setCrmTaskId(reqInfo.getSrcTaskId());
	
		order.setLimitTime(DateFormatUtils.format(reqInfo.getAlarmDate(), DEFAULTFORMAT));
		
		order.setOrderGroupId(reqInfo.getSrcOrderGrpId());
		return order;
	}
	
	
	/**
	 * 创建产品信息
	 * @param reqInfo
	 * @param prodInfo
	 * @param map
	 * @return
	 */
	private RofhProduct buildRofhProduct(ProdInfo prodInfo,Map<String, String> map) {
		
		String accessNum = map.get("main_access_numb");
		String fullName = StringUtils.isBlank(accessNum) ? prodInfo.getAccessNum() : RofhUtil.getAccountPrefix(prodInfo.getProdSrvSpecCode()) + accessNum;
		RofhProductAttemp attempProduct = attempProductDao.findByAccountName(fullName);
		attempProduct = attempProduct == null ? new RofhProductAttemp() : attempProduct;
		
		attempProduct.setProductCode(prodInfo.getProdInsId());
		attempProduct.setAccountName(fullName);
		String bandwidth = map.get("bandwidth");
		attempProduct.setBroadandWidth(bandwidth.substring(0, bandwidth.indexOf("@") == -1 ? bandwidth.length() : bandwidth.indexOf("@")));//带宽
		attempProduct.setInstallAddress(map.get("install_addr"));//用户标准地址（安装地址）
		attempProduct.setSaleType(prodInfo.getProdSrvSpecCode());//专业服务编码
		//报俊字段
		attempProduct.setSnCode(prodInfo.getOnuId());
		attempProduct.setStbMac(prodInfo.getStbMac());
		
		return attempProduct;
	}
	
	
	@PostConstruct
	private void init(){
		
		prodSerList = prodInfoDao.findAll();
		
		prodSerMap = new HashMap<>();
		for(ProdServInfo info : prodSerList){
			prodSerMap.put(info.getProdSrvCode(), info.getProdSrvType());
		}
		prodSerMap.put(WebServiceConstant.BusinessType.IPTV,ProdSerType.IPTV.name());
		prodSerMap.put(WebServiceConstant.BusinessType.IMS,ProdSerType.IMS.name());
		
	}
	
	public List<ProdServInfo> getProdServInfo(){
		
		return prodSerList;
	}
	
	public Map<String,String> getProdSerMap(){
		
		return prodSerMap;
	}
	
}
