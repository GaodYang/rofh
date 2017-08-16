package com.boco.rofh.webservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.mapper.AddressMapper;
import com.boco.rofh.utils.RofhUtil;
import com.boco.rofh.webservice.pojo.QueryAddressReq;
import com.boco.rofh.webservice.pojo.QueryAddressResult;
import com.boco.rofh.webservice.pojo.QueryAddressResult.StdAddr;

/**
 * 标准地址查询 服务
 * @author gaoyang
 *
 */
@Service
public class QueryAddressService extends BaseRofhWebService<QueryAddressReq,QueryAddressResult>{



	private final static Logger logger = Logger.getLogger(QueryAddressService.class);
	
	@Autowired
	private AddressMapper addressMapper;
	
	
	/**
	 * 4.3 标准地址查询 接口（模糊方式）
	 * @param sReceiveMsg
	 * @return
	 */
	
	@Override
	protected QueryAddressResult doBusiness(QueryAddressReq req) {
		
		/**
		 * 2016年11月2日改造
		 * 添加 stdAddrId
		 */
		String stdAddrId = req.getStdAddrId();
		
		QueryAddressResult addressRes = new QueryAddressResult();
		List<StdAddr> addList = new ArrayList<StdAddr>();
		addressRes.setResstdaddrlist(addList);
		
		//这是新增流程，查询地市id
		if(StringUtils.isNotBlank(stdAddrId)){
			
			Map<String, String>  map = addressMapper.queryAddressByStdId(stdAddrId);
			if(map != null){
				
				StdAddr add = new StdAddr();
				add.setTopicWord(req.getTopicWord());
				add.setStdAddrTypeId(req.getStdAddrTypeId());
				add.setDetailAllName(ObjectUtils.toString(map.get("NAME")));
				add.setDetailName(RofhUtil.getAddressDetialInfo(add.getDetailAllName()));
				add.setDetailAllSpell(ObjectUtils.toString(map.get("PINYIN")));
				add.setDetailSpell(RofhUtil.getAddressDetialInfo(add.getDetailAllSpell()));
				add.setDistrictId(ObjectUtils.toString(map.get("DISTRICTID")));
				add.setDistrictName(ObjectUtils.toString(map.get("DISTRICTNAME")));
				add.setId(ObjectUtils.toString(map.get("OBJECT_ID")));
				add.setName(ObjectUtils.toString(map.get("ROOM_NO")));
				addList.add(add);
				addressRes.setResstdaddrlist(addList);
			}
		}
		else{
			String districtId = req.getDistrictId();
			if(StringUtils.isNotBlank(districtId)){
				
				String name = addressMapper.queryNameByDistrictId(districtId);
				req.setDetailName(ObjectUtils.toString(name.replace("|", "")) + "%" + ObjectUtils.toString(req.getDetailName()));
				logger.info("name is :" + name);
				
			}
			req.setDetailSpell(ObjectUtils.toString(req.getDetailSpell()) + "%" + ObjectUtils.toString(req.getSpell()));
			req.setDetailSpell(req.getDetailSpell().toUpperCase());
			req.setDetailName(ObjectUtils.toString(req.getDetailName()) + "%" + ObjectUtils.toString(req.getName()));
			
			List<Map<String,String>>  list = addressMapper.queryAddress(req);
			
			//总数
	//		int count = Integer.parseInt((queryAddressServiceDAO.queryAddressCount((QueryAddressReq)req.getMsgContent()).get(0).get("TOTALCOUNT").toString()));
	//		addressRes.setCounts(count);
			//详情列表
			for(Map<String,String> map : list){
				
				StdAddr add = new StdAddr();
				add.setTopicWord(req.getTopicWord());
				add.setStdAddrTypeId(req.getStdAddrTypeId());
				add.setDetailAllName(ObjectUtils.toString(map.get("NAME")));
				add.setDetailName(RofhUtil.getAddressDetialInfo(add.getDetailAllName()));
				add.setDetailAllSpell(ObjectUtils.toString(map.get("PINYIN")));
				add.setDetailSpell(RofhUtil.getAddressDetialInfo(add.getDetailAllSpell()));
				add.setDistrictId(ObjectUtils.toString(map.get("DISTRICTID")));
				add.setDistrictName(ObjectUtils.toString(map.get("DISTRICTNAME")));
				add.setId(ObjectUtils.toString(map.get("OBJECT_ID")));
				add.setName(ObjectUtils.toString(map.get("ROOM_NO")));
				addList.add(add);
			}
		
		}
		
		return addressRes;
		
	}

	
	
}
 