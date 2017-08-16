package com.boco.rofh.webservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.mapper.AddressMapper;
import com.boco.rofh.utils.MapUtil;
import com.boco.rofh.webservice.pojo.QueryAddressTreeReq;
import com.boco.rofh.webservice.pojo.QueryAddressTreeResult;
import com.boco.rofh.webservice.pojo.QueryAddressTreeResult.ResStdAddrInfo;

/**
 * 标准地址查询 服务
 * @author gaoyang
 *
 */
@Service
public class QueryAddressTreeService extends BaseRofhWebService<QueryAddressTreeReq,QueryAddressTreeResult>{


	@Autowired
	private AddressMapper addressMapper;
	
	/**
	 * 4.2 标准地址查询 接口（树结构方式）
	 * @param sReceiveMsg
	 * @return
	 */
	
	@Override
	protected QueryAddressTreeResult doBusiness(QueryAddressTreeReq req) {
		
		String id = req.getNowAddrId();
		//addressTreeReq.getChildDeep(); 目前默认为1
		String districtId = req.getDistrictId();
		QueryAddressTreeResult addressTreeResult = new QueryAddressTreeResult();
		
		
		List<Map<String, String>> list = addressMapper.queryAddressTreeById(id);
		
		ResStdAddrInfo resStdAddrInfo = new ResStdAddrInfo();
		addressTreeResult.setResStdAddrInfo(resStdAddrInfo);
		resStdAddrInfo.setDistrictId(districtId);
		resStdAddrInfo.setId(id);
		resStdAddrInfo.setRegionId(getRegionId());
		List<ResStdAddrInfo> subAddrList = new ArrayList<ResStdAddrInfo>();
		resStdAddrInfo.setSubAddrList(subAddrList);
		
		if(list != null && list.size() != 0){
			
			ResStdAddrInfo childInfo;
			for(Map<String, String> map : list){
				
				childInfo = new ResStdAddrInfo();
				childInfo.setDetailAllName(MapUtil.getStringValue(map, "FULL_NAME"));
				childInfo.setDetailAllSpell(MapUtil.getStringValue(map, "FULL_SPELL"));
				childInfo.setDetailName(getDetialInfo(childInfo.getDetailAllName()));
				childInfo.setDetailSpell(getDetialInfo(childInfo.getDetailAllSpell()));
				childInfo.setDistrictId(districtId);
				childInfo.setId(MapUtil.getStringValue(map, "ID"));
				childInfo.setName(MapUtil.getStringValue(map, "NAME"));
				childInfo.setParentAddrId(id);
				childInfo.setRegionId(getRegionId());
				childInfo.setSpell(MapUtil.getStringValue(map, "SPELL"));
				//叶子结点
				/*String relid = MapUtil.getStringValue(map, "RELID");
				if(StringUtils.isNotBlank(relid))
				{
					String objectId = addressMapper.queryObjectIdByRelId(relid);
					childInfo.setId(objectId);
				}*/
				
				subAddrList.add(childInfo);
			}
			
		}
		
		return addressTreeResult;
	}

	
	private String getDetialInfo(String str){
		
		String s[] = str.split("\\|");
		StringBuilder sb = new StringBuilder();
		for(int i = 3 ; i<s.length ; i++){
			sb.append(s[i]);
		}
		return sb.toString();
	}
}
 