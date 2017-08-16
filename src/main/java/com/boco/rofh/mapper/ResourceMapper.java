package com.boco.rofh.mapper;

import java.util.List;
import java.util.Map;

public interface ResourceMapper {


	List<Map<String, String>> queryBroadPlan(String id);

    List<Map<String, String>> getFreePortFttb(String id);
	
	List<Map<String, String>> getFreePortCtt(String id);
	
	List<Map<String, String>> getFreePortFtth(String id);

	String getDeviceVendorCodeByPosId(String id);
	
	String getDeviceVendorCodeByWbsId(String id);

	List<Map<String, Object>> getFreePortWbs(String id);
}
