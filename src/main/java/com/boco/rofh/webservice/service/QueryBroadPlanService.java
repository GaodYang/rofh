package com.boco.rofh.webservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.dao.AddressDao;
import com.boco.rofh.mapper.ResourceMapper;
import com.boco.rofh.webservice.pojo.QueryBroadPlanReq;
import com.boco.rofh.webservice.pojo.QueryBroadPlanResult;
import com.boco.rofh.webservice.pojo.QueryBroadPlanResult.ProdServInfo;

/**
 * 4.4.2 设备资源及端口查询
 * @author wiggler
 *
 */
@Service
public class QueryBroadPlanService extends BaseRofhWebService<QueryBroadPlanReq,QueryBroadPlanResult>{

	@Autowired
	private ResourceMapper resourceMapper;
	
	@Autowired
	private AddressDao addressDao;
	
	/**
	 * 设备资源及端口查询
	 * @param 
	 * @return
	 */
	@Override
	protected QueryBroadPlanResult doBusiness(QueryBroadPlanReq broadPlanReq) {
		
		String cuid = addressDao.findIdByObjectId(new BigDecimal(broadPlanReq.getStandardAddrId()));
		
		QueryBroadPlanResult broadPlanResult = new QueryBroadPlanResult();
		
		if(cuid == null){
			
			return broadPlanResult;
		}
		List<Map<String,String>> list = resourceMapper.queryBroadPlan(cuid);
		
	
		List<ProdServInfo> prodSrvList = new ArrayList<ProdServInfo>();
		
		for(Map<String,String> map : list){
			
			ProdServInfo info = new ProdServInfo();
			String num = ObjectUtils.toString(map.get("NUM"));
			info.setInvNum("0".equals(num) ? "-1" : num);
			info.setProdSrvCode(ObjectUtils.toString(map.get("PROD_SRV_CODE")));
			info.setProdSrvName(ObjectUtils.toString(map.get("PROD_SRV_NAME")));
			prodSrvList.add(info);
			
		}
		if(prodSrvList.size() == 0){
			return broadPlanResult;
		}
		
		broadPlanResult.setProdSrvList(prodSrvList);
		return broadPlanResult;
			
	}
	
}
