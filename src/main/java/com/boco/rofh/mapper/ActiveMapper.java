package com.boco.rofh.mapper;

import java.util.Map;


public interface ActiveMapper {


	public String getDistrictById(String id);

	public Map<String, String> getOltById(String id);
}
