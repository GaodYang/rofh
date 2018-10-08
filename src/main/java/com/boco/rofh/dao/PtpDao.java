package com.boco.rofh.dao;

import java.math.BigDecimal;
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
	
	@Query(value="select get_object_id(:tableName) from dual",nativeQuery=true)
	BigDecimal getObjectId(@Param("tableName") String tableName);
	
	@Transactional
	@Modifying
	@Query(value="delete from Ptp where cuid = :id")
	public void delete(@Param("id")String id);
	
	@Transactional
	@Modifying
	@Query(value="delete from Ptp where related_ne_cuid = :id and dev_type = 2")
	public void deleteByOnuId(@Param("id")String id);
}
