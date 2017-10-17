package com.boco.rofh.webservice;

import com.boco.rofh.utils.SpringUtil;

/**
 * 为了axis
 * @author gaoyang
 *
 */
public class CrmService implements ICrmService{

	private ICrmService crmService;
	
	public CrmService() {
		
		this.crmService = SpringUtil.getBean(ICrmService.class);
	}

	@Override
	public String queryAddressTree(String sReceiveMsg, String sRegionId) {
		
		return crmService.queryAddressTree(sReceiveMsg, sRegionId);
	}

	@Override
	public String queryAddress(String sReceiveMsg, String sRegionId) {
		
		return crmService.queryAddress(sReceiveMsg, sRegionId);
	}

	@Override
	public int queryAddressCount(String sReceiveMsg, String sRegionId) {
		
		return crmService.queryAddressCount(sReceiveMsg, sRegionId);
	}

	@Override
	public String queryBroadPlan(String sReceiveMsg, String sRegionId) {
		
		return crmService.queryBroadPlan(sReceiveMsg, sRegionId);
	}

	@Override
	public String queryUserPlan(String sReceiveMsg, String sRegionId) {
		
		return crmService.queryUserPlan(sReceiveMsg, sRegionId);
	}

	@Override
	public String occupyProdSrvInv(String sReceiveMsg, String sRegionId) {
		
		return crmService.occupyProdSrvInv(sReceiveMsg, sRegionId);
	}

	@Override
	public String queryAddressParentIds(String sReceiveMsg, String sRegionId) {
		
		return crmService.queryAddressParentIds(sReceiveMsg, sRegionId);
	}
	
	@Override
	public String queryAddressAttr(String sReceiveMsg, String sRegionId) {
		
		return crmService.queryAddressAttr(sReceiveMsg, sRegionId);
	}
	
}
