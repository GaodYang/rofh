package com.boco.rofh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boco.rofh.entity.RofhActivate;

public interface ActiveDao extends JpaRepository<RofhActivate, String>,JpaSpecificationExecutor<RofhActivate>{

	@Query("select r from  RofhActivate r where relatedOrderCuid = :id")
	RofhActivate findByOrderid(@Param("id")String id);

}
