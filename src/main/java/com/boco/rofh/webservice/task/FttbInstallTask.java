package com.boco.rofh.webservice.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.entity.PonWayAttemp;
import com.boco.rofh.entity.RofhProduct;
import com.boco.rofh.utils.RofhUtil;

@Service
public class FttbInstallTask extends AbstractInstallTask{

	private static final Logger logger = LoggerFactory
			.getLogger(FttbInstallTask.class);
	
	/**
	 * 根据标准地址，进行端口的预占
	 * @param addrId
	 * @return
	 */
	public boolean occupyPort(RofhProduct rofhProduct){
	
			
		String addrId = rofhProduct.getRelatedCoverageAddrCuid();
			
		//查询端口
		List<Map<String,String>> list = resourceMapper.getFreePortFttb(addrId);
		if(list == null || list.size() == 0 || list.get(0).get("CUID") == null){
			
			return false;
		}
		
		String portCuid = list.get(0).get("CUID").toString();
		String neCuid = ObjectUtils.toString(list.get(0).get("RELATED_NE_CUID"));
		
		rofhProduct.setAccessPort(portCuid);//端口ID
		//获取接入方式 T_LOGIC_DEVICE_ONU
		rofhProduct.setAccessMode(WebServiceConstant.AccessMode.FTTB);
		rofhProduct.setAccessDevice(neCuid);//端口对应设备ID
		rofhProduct.setProductType(WebServiceConstant.ProductType.宽带业务);
		
		//接口更新为预占
		ptpDao.updatePortState(portCuid, WebServiceConstant.PtpState.预占);
		
		return true;
					
	}
	
	protected void createAttempPonWay(RofhProduct rofhProduct) {

		// PON_WAY信息
		String neCuid = rofhProduct.getAccessDevice();

		PonWayAttemp ponWay = new PonWayAttemp();		
		ponWay.setCreateTime(new Date());
		ponWay.setObjectid(ptpDao.getObjectId("PON_WAY"));
		ponWay.setAccountName(rofhProduct.getAccountName());
		ponWay.setOnuPortCuid(rofhProduct.getAccessPort());
		ponWay.setRelatedOnuCuid(rofhProduct.getAccessDevice());
		ponWay.setPonWayState(new BigDecimal("3"));
		ponWay.setLabelCn(rofhProduct.getAccountName());
		ponWay.setObjectid(ptpDao.getObjectId("PON_WAY"));
		ponWay.setAccountPassword(RofhUtil.getRandomPw(8));
		//ponWay.setAccount_password("mu74b2a3");
		///ONU-POS-OLT
		Map<String, String> linkInfo = null;
		
		linkInfo = configMapper.getLinkInfoFttbByCuid(neCuid);
		
		if (linkInfo != null) {

			ponWay.setOnuUpPortCuid(ObjectUtils.toString(linkInfo.get("RELATED_ONU_UP_PTP")));
			ponWay.setLightPortCuid(ObjectUtils.toString(linkInfo.get("RELATED_POS_DOWN_PTP")));
			ponWay.setRelatedLightCuid(ObjectUtils.toString(linkInfo.get("RELATED_POS")));
			ponWay.setLightUpPortCuid(ObjectUtils.toString(linkInfo.get("RELATED_POS_UP_PTP")));
			ponWay.setOltPonPortCuid(ObjectUtils.toString(linkInfo.get("OLT_PON_PORT_CUID")));
			ponWay.setRelatedOltCuid(ObjectUtils.toString(linkInfo.get("RELATED_OLT")));

		}
	
		ponWay.setLastModifyTime(new Date());
		ponWay.setRelatedProductCuid(rofhProduct.getCuid());
/*		
		ponWay.setSvlan("2012");
		ponWay.setCvlan("370");*/
		
		attempPonWayDao.save(ponWay);
		
		rofhProduct.setSvlan(ponWay.getSvlan());
		rofhProduct.setCvlan(ponWay.getCvlan());
	}
	
	
	
}
