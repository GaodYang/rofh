package com.boco.rofh.webservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.mapper.AddressMapper;
import com.boco.rofh.webservice.pojo.QueryAddressParentIdsReq;
import com.boco.rofh.webservice.pojo.QueryAddressParentIdsResult;

@Service
public class QueryAddressParentIdsService  extends BaseRofhWebService<QueryAddressParentIdsReq,QueryAddressParentIdsResult>{

	@Autowired
	private AddressMapper addressMapper;
	
	
	@Override
	protected QueryAddressParentIdsResult doBusiness(QueryAddressParentIdsReq addressParentIdsReq) {
		
		
		String id = addressParentIdsReq.getNowAddrId();
		//判断是不是叶子节点
		String relId = addressMapper.queryRelAddrId(id);
		if(relId != null && !"".equals(relId)){
			id = relId;
		}
		
		String parentIds = addressMapper.queryAddrParentIds(id);
		String addrParentIds = getFinalIds(addressParentIdsReq.getRootAddrId(),parentIds);
		
		QueryAddressParentIdsResult addressParentIdsResult = new QueryAddressParentIdsResult();
		addressParentIdsResult.setAddrParentIds(addrParentIds);
		return addressParentIdsResult;
	}
	
	private String getFinalIds(String rootid,String pids){
		
		String[] b = pids.split(",");
		StringBuilder sb = new StringBuilder(rootid);
		
		for(int i = 2;i<b.length -1;i++){
			sb.append(",");
			sb.append(b[i]);
		}

		return sb.toString();
	}
	
	
}
