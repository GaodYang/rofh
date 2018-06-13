package com.boco.rofh.webservice.service;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.exception.UserException;
import com.boco.rofh.mapper.AddressMapper;
import com.boco.rofh.webservice.pojo.QueryAddressAttrReq;
import com.boco.rofh.webservice.pojo.QueryAddressAttrResult;
import com.boco.rofh.webservice.pojo.QueryAddressAttrResult.CommunityInfo;

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
		
		String addrId = req.getStandardAddrId();
		if(StringUtils.isEmpty(addrId)){
			
			result.setRegionType("0");
			return result;
		}
		
		Map<String,Object> map = addressMapper.queryRegionType(addrId);
		if(map == null || map.isEmpty()) {
			
			throw new UserException(addrId + "地址不存在！");
		}
		String type = ObjectUtils.toString(map.get("TYPE"));
		type = type == null ? "0" : type;
		result.setRegionType(type);
		
		CommunityInfo info = new CommunityInfo();
		result.setCommunityInfo(info);
		String communityType = ObjectUtils.toString(map.get("COMMUNITY_TYPE"));
		info.setCommunityType(communityType);
		
		if("1".equals(communityType)){
			
			info.setCompletionTime((Date)map.get("COMPLETION_TIME"));
		}
		
		return result;
	}

	
}
