package com.boco.rofh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boco.rofh.entity.RofhCustomer;

public interface CustomerDao extends JpaRepository<RofhCustomer, String>{

	@Query("select r from RofhCustomer r  where customerCode = :code")
	RofhCustomer findByCustomerCode(@Param("code")String code);
}
