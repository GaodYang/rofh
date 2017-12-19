package com.boco.rofh.mapper;

import java.util.List;
import java.util.Map;

import com.boco.rofh.webservice.pojo.QueryAddressReq;

public interface AddressMapper {

	List<Map<String, String>> queryAddressTreeById(String id);

	String queryObjectIdByRelId(String id);

	Map<String, String> queryAddressByStdId(String stdAddrId);

	String queryNameByDistrictId(String districtId);

	List<Map<String, String>> queryAddress(QueryAddressReq req);

	int queryAddressCount(QueryAddressReq req);

	String queryRelAddrId(String id);

	String queryAddrParentIds(String id);
	
	List<Map<String,String>> queryRegionInfo();
	
	String queryCountyInfo(Map<String,String> map);
	
	Map<String,Object> queryRegionType(String id);
}
