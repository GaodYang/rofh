package com.boco.rofh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.boco.rofh.entity.AnOnuBase;

@NoRepositoryBean
public interface OnuDao<T extends AnOnuBase> extends JpaRepository<T, String>,JpaSpecificationExecutor<T>{

	@Query("select t.labelCn from #{#entityName} t where t.cuid = :id")
	public String findLabelCnById(@Param("id")String id);
}
