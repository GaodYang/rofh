package com.boco.rofh.webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.mapper.AddressMapper;
import com.boco.rofh.webservice.pojo.QueryAddressAttrReq;
import com.boco.rofh.webservice.pojo.QueryAddressAttrResult;

/**
 * 
 * @author gaoyang  2017年10月17日
 *
 * @Description: 查询地域属性
 *
 */
@Service
public class QueryAddressAttrService extends BaseRofhWebService<QueryAddressAttrReq,QueryAddressAttrResult>{

	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	protected QueryAddressAttrResult doBusiness(QueryAddressAttrReq req) {
		
		QueryAddressAttrResult result = new QueryAddressAttrResult();
		
		String type = addressMapper.queryRegionType(req.getStandardAddrId());
		type = type == null ? "0" : type;
		result.setRegionType(type);
		
		return result;
	}

	
}
