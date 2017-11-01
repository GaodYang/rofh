package com.boco.rofh.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boco.rofh.entity.RofhOrder;

public interface OrderDao extends JpaRepository<RofhOrder, String>{

	RofhOrder findFirstByCrmSheetNoAndCrmTaskId(String crmSheetNo,String crmTaskId); 
}
