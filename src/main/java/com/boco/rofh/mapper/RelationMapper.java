package com.boco.rofh.mapper;

import java.util.List;
import java.util.Map;

import com.boco.rofh.po.VillageResource;

public interface RelationMapper {

	public List<VillageResource> getVillageResource();
	
	public Map<String,String> getOnuLink(String id);
	
	public Map<String,String> getPosLink(String id);
}
