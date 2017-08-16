package com.boco.rofh.webservice.service;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.mapper.AddressMapper;
import com.boco.rofh.webservice.pojo.CommonRootReq;
import com.boco.rofh.webservice.pojo.QueryAddressReq;

/**
 * 标准地址查询 服务
 * @author gaoyang
 *
 */
@Service
public class QueryAddressCountService{


	private final static Logger logger = Logger.getLogger(QueryAddressCountService.class);
	
	@Autowired
	private AddressMapper addressMapper;
	
	
	/**
	 * 4.3 标准地址查询 接口（模糊方式）
	 * @param sReceiveMsg
	 * @return
	 */

	private int doBusiness(QueryAddressReq req) {
		
		String districtId = req.getDistrictId();
		if(StringUtils.isNotBlank(districtId)){
			
			String name = addressMapper.queryNameByDistrictId(districtId);
			req.setDetailName(ObjectUtils.toString(name.replace("|", "")) + "%" + ObjectUtils.toString(req.getDetailName()));
			logger.info("name is :" + name);
			
		}
		req.setDetailSpell(ObjectUtils.toString(req.getDetailSpell()) + "%" + ObjectUtils.toString(req.getSpell()));
		req.setDetailSpell(req.getDetailSpell().toUpperCase());
		req.setDetailName(ObjectUtils.toString(req.getDetailName()) + "%" + ObjectUtils.toString(req.getName()));
		int count = addressMapper.queryAddressCount(req);
		logger.info("查询记录总数为：" + count);
		//总数
		return count;
		
		
	}

	
	
	public Integer doProcess(String xml){

		//将报文信息转换为实体
		CommonRootReq<QueryAddressReq> root = new CommonRootReq<QueryAddressReq>();
		int result = -1;
		try{
			//解析
			root = root.toBean(xml, QueryAddressReq.class);
		}catch(Exception e){
			
			logger.error("报文解析失败！", e);
			return -1;
		}
		
		logger.info("报文转换后的实体为:" + root);
		
		try {
			//业务逻辑
			result = doBusiness(root.getMsgContent());
				
		}  catch (Exception e) {
			
			logger.error("系统异常！" ,e);
			return -1;
		}
		
		
		//结果消息返回
		return result;
		
	}
	
}
 