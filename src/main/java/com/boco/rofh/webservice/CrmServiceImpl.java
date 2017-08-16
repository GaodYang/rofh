package com.boco.rofh.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.webservice.service.OccupyProdService;
import com.boco.rofh.webservice.service.QueryAddressCountService;
import com.boco.rofh.webservice.service.QueryAddressParentIdsService;
import com.boco.rofh.webservice.service.QueryAddressService;
import com.boco.rofh.webservice.service.QueryAddressTreeService;
import com.boco.rofh.webservice.service.QueryBroadPlanService;
import com.boco.rofh.webservice.service.QueryUserPlanService;

/**
 * 4 外围系统资源与CRM接口 服务
 * @author gaoyang
 *
 */
@Service
public class CrmServiceImpl implements ICrmService{

	@Autowired
	private QueryAddressTreeService queryAddressTreeService;
	
	@Autowired
	private QueryAddressService queryAddressService;
	
	@Autowired
	private QueryAddressCountService queryAddressCountService;
	
	@Autowired
	private QueryAddressParentIdsService queryAddressParentIdsService;
	
	@Autowired
	private QueryBroadPlanService queryBroadPlanService;
	
	@Autowired
	private OccupyProdService occupyProdService;
	
	@Autowired
	private QueryUserPlanService queryUserPlanService;

	/**
	 * 4.2 标准地址查询（树结构方式）
	 * @param sReceiveMsg
	 * @return
	 */
	public String queryAddressTree(String sReceiveMsg,String sRegionId){
		
		return queryAddressTreeService.doProcess(sReceiveMsg,sRegionId);
	}
	
	/**
	 * 4.3 标准地址查询（模糊方式）
	 * @param sReceiveMsg
	 * @return
	 */
	public String queryAddress(String sReceiveMsg,String sRegionId){
		
		return queryAddressService.doProcess(sReceiveMsg,sRegionId);
	}
	
	/**
	 * 4.3 标准地址查询（模糊方式）总数
	 * @param sReceiveMsg
	 * @return
	 */
	public Integer queryAddressCount(String sReceiveMsg,String sRegionId){
		
		return queryAddressCountService.doProcess(sReceiveMsg);
	}
	/**
	 * 查询资源和端口
	 * @param sReceiveMsg
	 * @return
	 */
	public String queryBroadPlan(String sReceiveMsg,String sRegionId){
		
		return queryBroadPlanService.doProcess(sReceiveMsg, sRegionId);
	}
	
	/**
	 * 用户端口占用查询
	 * @param sReceiveMsg
	 * @return
	 */
	public String queryUserPlan(String sReceiveMsg,String sRegionId){
		
		return queryUserPlanService.doProcess(sReceiveMsg, sRegionId);
	}
	/**
	 * 资源端口占用
	 * @param sReceiveMsg
	 * @return
	 */
	public String occupyProdSrvInv(String sReceiveMsg,String sRegionId){
		
		return occupyProdService.doProcess(sReceiveMsg, sRegionId);
	}
	
	/**
	 * 树形结构，查询父地址
	 * @param sReceiveMsg
	 * @param sRegionId
	 * @return
	 */
	public String queryAddressParentIds(String sReceiveMsg,String sRegionId){
		
		return queryAddressParentIdsService.doProcess(sReceiveMsg, sRegionId);
	}
	
}
