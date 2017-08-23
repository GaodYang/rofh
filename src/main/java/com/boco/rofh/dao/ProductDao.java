package com.boco.rofh.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.boco.rofh.entity.RofhProduct;

@NoRepositoryBean
public interface ProductDao<T extends RofhProduct> extends JpaRepository<T, String>{

	@Query("select t from #{#entityName} t where t.productCode = :code")
	public List<T> findByProductCode(@Param("code")String code);
	
	@Query("select 1 from  #{#entityName} t where t.accountName = :name")
	public String isExistByAccount(@Param("name")String name);
	
	@Query("select t from #{#entityName} t where t.accountName = :name")
	public List<T> findByAccountName(@Param("name")String name);

	@Query("select t from #{#entityName} t where t.relatedOrderCuid = :id")
	public RofhProduct findByOrderId(@Param("id")String relatedOrderCuid);

	@Query("select t from  #{#entityName} t where t.productCode is null and  t.preemptTime < :date")
	public List<T> findAllOverTimeProduct(@Param("date")Date date);
}
