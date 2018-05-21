package com.boco.rofh.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boco.rofh.entity.ActiveResp.Active;

public interface ActiveIptvDao extends JpaRepository<Active, String>{

}
