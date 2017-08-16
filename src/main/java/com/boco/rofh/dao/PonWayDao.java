package com.boco.rofh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.boco.rofh.entity.PonWayBase;

@NoRepositoryBean
public interface PonWayDao<T extends PonWayBase> extends JpaRepository<T, String>{

	@Query("select p from  #{#entityName} p where relatedProductCuid = :pid")
	public T findByProductCuid(@Param("pid") String pid);
}
