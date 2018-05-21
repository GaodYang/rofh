package com.boco.rofh.mapper;

import java.util.List;
import java.util.Map;

import com.boco.rofh.entity.RofhActivate;


public interface ActiveMapper {


	public String getDistrictById(String id);

	public Map<String, String> getOltById(String id);
	
	public List<RofhActivate> getAllActive();
}
