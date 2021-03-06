package com.boco.rofh.webservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant.ProdSerType;
import com.boco.rofh.dao.AddressDao;
import com.boco.rofh.mapper.ResourceMapper;
import com.boco.rofh.utils.MapUtil;
import com.boco.rofh.utils.RofhBeanWapper;
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
	
	@Autowired
	private RofhBeanWapper beanWapper;
	
	private List<ProdServInfo> serList;
	
	/**
	 * 设备资源及端口查询
	 * @param 
	 * @return
	 */
	@Override
	public QueryBroadPlanResult doBusiness(QueryBroadPlanReq broadPlanReq) {
		
		//标准地址id
		String cuid = addressDao.findIdByObjectId(new BigDecimal(broadPlanReq.getStandardAddrId()));
		
		QueryBroadPlanResult broadPlanResult = new QueryBroadPlanResult();
		broadPlanResult.setProdSrvList(serList);
		
		if(cuid == null){
			
			return broadPlanResult;
		}
		
		//获取管理箱体的网元
		List<String> cabIdList = resourceMapper.queryCoverNeByAddr(cuid);
		if(cabIdList == null || cabIdList.size() < 1){
			
			return broadPlanResult;
		}
		
		String cabId = cabIdList.get(0);
		//如果是wbs
		if(cabId.startsWith("AN_WBS")){
			
			Integer num = resourceMapper.queryWbsBroadPlan(cabId);
			if(num != null && num > 0){
				
				broadPlanResult.setProdSrvList(makeData(ProdSerType.WBS,num));
			}
			
			return broadPlanResult;
		}
		
		//正常查
		List<Map<String,Object>> list = resourceMapper.queryBroadPlan(cabId);
		Map<String,Object> bMap = list.get(0);
		int num = MapUtil.getIntegerValue(bMap, "NUM");
	
		if(num == 0){
			
			//查询箱体关联关系
			List<Map<String,Object>> fiberList = resourceMapper.queryFiberDpMapByDp(cabId);
			//是分光器
			if(fiberList != null && fiberList.size() > 0){
				
				ProdSerType type = ProdSerType.FTTH;
				int count = 0;
				for(Map<String,Object> fiberMap : fiberList){

					//取分光器信息
					Map<String,Object> posMap = resourceMapper.queryPosBroadPlan(fiberMap);
					type = ProdSerType.valueOf(MapUtil.getStringValue(posMap, "TYPE"));
					count += MapUtil.getIntegerValue(posMap, "NUM");
				}
				
				broadPlanResult.setProdSrvList(makeData(type,count));
				return broadPlanResult;
			}
		}else{
			broadPlanResult.setProdSrvList(makeData(ProdSerType.valueOf(bMap.get("TYPE").toString()),num));
		}
		return broadPlanResult;
			
	}
	
	private List<ProdServInfo> makeData(ProdSerType type,int num){
		
		List<ProdServInfo> prodSrvList = new ArrayList<ProdServInfo>();
	
		for(ProdServInfo info : serList){
			
			if(type.name().equals(info.getType()) && num > 0){
				
				ProdServInfo pinfo = info.clone();
				pinfo.setInvNum(num);
				prodSrvList.add(pinfo);
			}else{
				
				prodSrvList.add(info);
			}
		}
		
		return prodSrvList;
	}
	
	@PostConstruct
	private void init(){
		
		serList = new ArrayList<>();
		List<com.boco.rofh.entity.ProdServInfo> list = beanWapper.getProdServInfo();
		for(com.boco.rofh.entity.ProdServInfo pinfo : list){
			
			ProdServInfo info = new ProdServInfo();
			info.setInvNum(-1);
			info.setProdSrvCode(pinfo.getProdSrvCode());
			info.setProdSrvName(pinfo.getProdSrvName());
			info.setType(pinfo.getProdSrvType());
			serList.add(info);
		}
	}
	
	
}
