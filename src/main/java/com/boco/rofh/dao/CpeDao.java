package com.boco.rofh.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boco.rofh.entity.AnCpe;

public interface CpeDao extends JpaRepository<AnCpe, String>{

	@Transactional
	@Modifying
	@Query(value="delete from AnCpe where cuid = :id")
	public void delete(@Param("id")String id);
}
