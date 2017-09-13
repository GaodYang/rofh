package com.boco.rofh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boco.rofh.entity.RofhProductSf;


public interface ProductSfDao extends ProductDao<RofhProductSf>{

	@Query("select t from #{#entityName}  t where t.accountName = :name")
	public List<RofhProductSf> findByAccountNameSf(@Param("name")String name);
}
