package com.boco.rofh.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boco.rofh.entity.RofhFullAddress;

public interface AddressDao extends JpaRepository<RofhFullAddress, String>{

	@Query("select labelCn from RofhFullAddress where cuid = :id")
	String findNameById(@Param("id")String id);
	
	@Query(value = "select bc.label_cn from business_community bc,t_rofh_full_address fa where bc.cuid = fa.related_community_cuid"
			+ " and fa.cuid = :id", nativeQuery = true)
	String getVillageById(@Param("id")String id);
	
	@Query("select r.cuid from RofhFullAddress r where r.objectId = :id ")
	String findIdByObjectId(@Param("id")BigDecimal id);

	@Query("select r from RofhFullAddress r where r.objectId = :id ")
	RofhFullAddress findByObjectId(@Param("id")BigDecimal bigDecimal);
	
	@Query(value = "select label_cn from district d where cuid = :id" , nativeQuery = true)
	String getDistrictNameById(@Param("id")String id);
}
