package com.boco.rofh.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boco.rofh.entity.AnWbs;

public interface WbsDao extends JpaRepository<AnWbs, String>{

	@Transactional@Modifying@Query("update AnWbs set used_port_num = used_port_num + 1 where cuid = :id")
	void updateWbsOccupyById(@Param("id")String id);
	
	@Transactional@Modifying@Query("update AnWbs set used_port_num = used_port_num - 1 where cuid = :id")
	void updateWbsFreeById(@Param("id")String id);
	
	@Transactional@Modifying@Query("update AnWbs set used_port_num = used_port_num - 1 where cuid in :id")
	void updateWbsFreeByIdBatch(@Param("id")List<String> id);
}
