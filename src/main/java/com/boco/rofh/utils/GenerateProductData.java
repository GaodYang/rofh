package com.boco.rofh.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.boco.rofh.dao.ProductSfDao;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhProductSf;
import com.boco.rofh.mapper.AddressMapper;

@Component
public class GenerateProductData {

	@Autowired
	private ProductSfDao productDao;
	
	@Autowired
	private AddressMapper addressMapper;
	
	private Map<String,String> cityMap;
	/**
	 * 捏造存量数据
	 * @param rofhProduct
	 * @return
	 */
	public RofhProductSf createPbossProduct(RofhBean rofhBean){
		
		List<RofhProductSf> list = productDao.findByAccountNameSf(rofhBean.getProduct().getAccountName());
		if(list != null && list.size() > 0){
			
			RofhProductSf sf  = list.get(0);
			sf.setProductCode(rofhBean.getProduct().getProductCode());
			productDao.save(sf);
			return sf;
		}
		
		RofhProductSf sfProduct = new RofhProductSf();
		BeanUtils.copyProperties(rofhBean.getProduct(), sfProduct);
		
		if(StringUtils.isNotBlank(rofhBean.getRegionId())){
			
			sfProduct.setBusinessCityD(cityMap.get("0" + rofhBean.getRegionId()));
			Map<String,String> map = new HashMap<>();
			map.put("id", rofhBean.getDistrictId());
			map.put("city", sfProduct.getBusinessCityD());
			sfProduct.setBusinessCountyD(addressMapper.queryCountyInfo(map));
			sfProduct.setCellAddress(sfProduct.getInstallAddress());
			sfProduct.setRelatedCustomerCuid("fake");
		}else{
			
			sfProduct.setCellAddress("存量地址缺失");
			sfProduct.setRelatedMaintainCuid("");
		}
		
		
		sfProduct.setCuid(null);
		
		
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
