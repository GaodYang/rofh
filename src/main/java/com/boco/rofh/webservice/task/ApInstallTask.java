package com.boco.rofh.webservice.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.entity.AnCpe;
import com.boco.rofh.entity.PonWayAttemp;
import com.boco.rofh.entity.RofhProduct;
import com.boco.rofh.utils.MapUtil;

@Service
public class ApInstallTask extends AbstractInstallTask	{
	
	private static final Logger logger = LoggerFactory
			.getLogger(ApInstallTask.class);
	
	
	@Override
	protected void createAttempPonWay(RofhProduct rofhProduct) {
		
		Map<String,String> map = addVirtualCpe(rofhProduct);
		
		
		PonWayAttemp attempPonWay=new PonWayAttemp();

  		
  		attempPonWay.setRelatedOnuCuid(MapUtil.getStringValue(map, "CPE_CUID"));
	
    	attempPonWay.setCreateTime(new Date());
    	attempPonWay.setAccountName(rofhProduct.getAccountName());
    	attempPonWay.setPonWayState(new BigDecimal("3"));
    	attempPonWay.setLabelCn(rofhProduct.getAccountName());

    	attempPonWay.setRelatedOltCuid(rofhProduct.getAccessDevice());
		///
		attempPonWay.setLastModifyTime(new Date());
		attempPonWay.setRelatedProductCuid(rofhProduct.getCuid());
		
		attempPonWay.setOnuPortCuid(rofhProduct.getAccessPort());
		
		attempPonWayDao.save(attempPonWay);
		
	}
	
	@Override
	synchronized public boolean occupyPort(RofhProduct rofhProduct) {
		
		//查询端口
		List<Map<String,Object>> list = resourceMapper.getFreePortWbs(rofhProduct.getRelatedCoverageAddrCuid());
		if(list == null || list.size() == 0 || list.get(0).get("NUM") == null || MapUtil.getIntegerValue(list.get(0), "NUM") <= 0){
			
			return false;
		}
		
		String neCuid = ObjectUtils.toString(list.get(0).get("RELATED_NE_CUID"));
		rofhProduct.setAccessDevice(neCuid);//端口对应设备ID
		rofhProduct.setAccessPort(ObjectUtils.toString(list.get(0).get("CUID")));
		//获取接入方式 T_LOGIC_DEVICE_WBS
		rofhProduct.setAccessMode(WebServiceConstant.AccessMode.WBS);
		rofhProduct.setProductType(WebServiceConstant.ProductType.无线宽带);
		wbsDao.updateWbsOccupyById(neCuid);
		
		return true;
	}
	
	/**
	 * 新增虚拟cpe?
	 * @param rofhProduct
	 */
	private Map<String,String> addVirtualCpe(RofhProduct rofhProduct){
		
		AnCpe cpe = new AnCpe();
		cpe.setLabelCn(createCpeName(rofhProduct));
		cpe.setRelatedWbsCuid(rofhProduct.getAccessDevice());
		cpe.setSetupTime(new Date());
	//	cpe.setObjectid("");
		
		cpeDao.save(cpe);
		Map<String,String> map = new HashMap<String,String>();
		map.put("CPE_CUID", cpe.getCuid());
		
		return map;
	}
	
	
	/**
     * 生成CPE名称
     * 标准地址去掉省级和地市”+‘-’+‘业务区分标识’+‘-’+‘CPE001’+’-‘+’厂家编码’
     * @param dataMap
     * @return
     */
    public String createCpeName(RofhProduct rofhProduct){
    	
    	//
    	String addrId = rofhProduct.getRelatedCoverageAddrCuid();
    	//标准地址去掉省级和地市
    	String addrFullName = addressDao.findNameById(addrId);
    	String names[] = addrFullName.split("\\|");
    	StringBuilder name = new StringBuilder();
    	if(names.length > 2){
    		
    		for(int i = 2; i < names.length ; i++){
    			
    			name.append(names[i]);
    		}
    	}
    	name.append("-ZD-CPE001-");
    	//厂家编码
    	String vendorCode = resourceMapper.getDeviceVendorCodeByWbsId(rofhProduct.getAccessDevice());
    	name.append(vendorCode);
    	
    	logger.info("virtual cpe name is " + name);
		return name.toString();    	
    }
}
