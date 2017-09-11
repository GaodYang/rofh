package com.boco.rofh.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boco.rofh.dao.ProductDao;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhProduct;
import com.boco.rofh.entity.RofhProductSf;
import com.boco.rofh.mapper.AddressMapper;

@Component
public class GenerateProductData {

	@Autowired
	private ProductDao<RofhProductSf> productDao;
	
	@Autowired
	private AddressMapper addressMapper;
	
	private Map<String,String> cityMap;
	/**
	 * 捏造存量数据
	 * @param rofhProduct
	 * @return
	 */
	public RofhProductSf createPbossProduct(RofhBean rofhBean){
		
		RofhProductSf sfProduct = new RofhProductSf();
		BeanUtils.copyProperties(rofhBean.getProduct(), sfProduct);
		
		sfProduct.setBusinessCityD(cityMap.get(rofhBean.getRegionId()));
		Map<String,String> map = new HashMap<>();
		map.put("id", rofhBean.getDistrictId());
		map.put("city", sfProduct.getBusinessCityD());
		sfProduct.setBusinessCountyD(addressMapper.queryCountyInfo(map));
		sfProduct.setRelatedMaintainCuid("缺少数据，请联系pboss");
		sfProduct.setCellName("缺少数据，请联系pboss");
		sfProduct.setCellAddress(sfProduct.getInstallAddress());
		
		
		productDao.save(sfProduct);
		
		return sfProduct;
	}
	
	@PostConstruct
	public void init(){
		
		cityMap = new HashMap<>();
		List<Map<String,String>> list = addressMapper.queryRegionInfo();
		list.forEach((map) -> {
			String key = map.get("CODE");
			String value = map.get("CUID");
			cityMap.put(key, value);
		});
	}
}
