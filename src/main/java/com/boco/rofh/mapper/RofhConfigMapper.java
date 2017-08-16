package com.boco.rofh.mapper;

import java.util.List;
import java.util.Map;

import com.boco.rofh.webservice.pojo.RofhNameSpaceBean;

public interface RofhConfigMapper {

	String getLinkTypeFtth(String id);

	Map<String, String> getLinkInfoFtthPOByCuid(String id);

	Map<String, String> getLinkInfoFtthPPOByCuid(String id);

	Map<String, String> getLinkInfoFttbByCuid(String id);

	Map<String, String> getMaintainNameByAddr(String id);

	List<Map<String, String>> getWbsInfoById(String eqptCode);

	List<Map<String, String>> getOnuInfoById(RofhNameSpaceBean buildBean);

}
