package com.boco.rofh.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boco.rofh.entity.Ptp;

public interface PtpDao extends JpaRepository<Ptp, String>{

	@Transactional@Modifying@Query("update Ptp set portState = :state where cuid = :id")
	void updatePortState(@Param("id")String id,@Param("state")String state);
	
	@Transactional@Modifying@Query("update Ptp set cvlan = :cvlan where cuid = :id")
	void updatePonPortCvlan(@Param("id")String id,@Param("cvlan")String cvlan);
	
	@Query("select labelCn from Ptp where cuid = :id")
	String findLabelCnByCuid(@Param("id") String id);
	
	@Transactional@Modifying@Query("update Ptp set portState = 1 where cuid in :id")
	void updatePortFreeBatch(@Param("id")List<String> id);
}
