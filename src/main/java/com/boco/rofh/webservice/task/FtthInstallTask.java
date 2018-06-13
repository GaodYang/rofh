package com.boco.rofh.webservice.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.dao.PosDao;
import com.boco.rofh.entity.AnOnuAttemp;
import com.boco.rofh.entity.AnPos;
import com.boco.rofh.entity.PonWayAttemp;
import com.boco.rofh.entity.Ptp;
import com.boco.rofh.entity.RofhProduct;
import com.boco.rofh.utils.MapUtil;
import com.boco.rofh.utils.RofhUtil;

@Service
public class FtthInstallTask extends AbstractInstallTask{

	private static final Logger logger = LoggerFactory
			.getLogger(FtthInstallTask.class);

	@Autowired
	private PosDao posDao;
	
	
	@Override
	protected void createAttempPonWay(RofhProduct rofhProduct) {
		
		//生成虚拟onu和端口
		Map<String,String> map = addVirtualONUandPort(rofhProduct);
		PonWayAttemp attempPonWay=new PonWayAttemp();

  		attempPonWay.setOnuPortType(new BigDecimal("14"));
  		attempPonWay.setRelatedLightCuid(rofhProduct.getAccessDevice());
  		attempPonWay.setLightPortCuid(rofhProduct.getAccessPort());//pos下联口CUID
  		attempPonWay.setRelatedOnuCuid(MapUtil.getStringValue(map, "ONU_ID"));
    	attempPonWay.setOnuPortCuid(MapUtil.getStringValue(map, "PTP_CUID"));
    	attempPonWay.setOnuUpPortCuid(MapUtil.getStringValue(map,"RELATED_ONU_UP_PTP"));
    	attempPonWay.setObjectid(ptpDao.getObjectId("PON_WAY"));
    	attempPonWay.setCreateTime(new Date());
    	attempPonWay.setAccountName(rofhProduct.getAccountName());
    	attempPonWay.setPonWayState(new BigDecimal("3"));
    	attempPonWay.setLabelCn(rofhProduct.getAccountName());

		///
		Map<String, String> linkInfo = null;
		
		String type = configMapper.getLinkTypeFtth(rofhProduct.getAccessDevice());
		
		if("pos-olt".equals(type)){
			
			linkInfo = configMapper.getLinkInfoFtthPOByCuid(rofhProduct.getAccessDevice());
		}else if("pos-pos-olt".equals(type)){
			
			linkInfo = configMapper.getLinkInfoFtthPPOByCuid(rofhProduct.getAccessDevice());
		}
		if (linkInfo != null) {	
				
			attempPonWay.setLightUpPortCuid(ObjectUtils.toString(linkInfo.get("RELATED_POS_UP_PTP")));
			attempPonWay.setOltPonPortCuid(ObjectUtils.toString(linkInfo.get("RELATED_OLT_DOWN_PTP")));
			attempPonWay.setRelatedOltCuid(ObjectUtils.toString(linkInfo.get("RELATED_OLT")));
					
			//////////////pos-pos-olt
			if("pos-pos-olt".equals(type)){
				
				attempPonWay.setSecondLightPortCuid(rofhProduct.getAccessPort());
				attempPonWay.setSecondLightUpPortCuid(ObjectUtils.toString(linkInfo.get("RELATED_TWO_POS_UP_PTP")));
				attempPonWay.setRelatedSecondLightCuid(rofhProduct.getAccessDevice());
				attempPonWay.setLightPortCuid(ObjectUtils.toString(linkInfo.get("RELATED_POS_DOWN_PTP")));
				attempPonWay.setRelatedLightCuid(ObjectUtils.toString(linkInfo.get("RELATED_POS")));
			}
			
		}
	
		attempPonWay.setAccountPassword(MapUtil.getStringValue(map, "PASSWORD"));
		attempPonWay.setSvlan(MapUtil.getStringValue(map, "SVLAN"));
		attempPonWay.setCvlan(MapUtil.getStringValue(map, "CVLAN"));
		attempPonWay.setLastModifyTime(new Date());
		attempPonWay.setRelatedProductCuid(rofhProduct.getCuid());
		
		attempPonWayDao.save(attempPonWay);
		
		rofhProduct.setSvlan(attempPonWay.getSvlan());
		rofhProduct.setCvlan(attempPonWay.getCvlan());
  		
	}
	/**
	 * 
	 * @param rofhProduct
	 */
	private Map<String,String> addVirtualONUandPort(RofhProduct rofhProduct) {
		
		//attemp_an_onu
		
    	String posCuid = rofhProduct.getAccessDevice();
    		
    	AnPos pos = posDao.findOne(posCuid);
    	Ptp ptp = ptpDao.findOne(pos.getRelatedPortCuid());
    	ptp = ptp == null ? new Ptp() : ptp;
    	
    	String cvlan = ptp.getCvlan();
    	cvlan = StringUtils.isEmpty(cvlan) ? "576" : cvlan;
    	cvlan = Integer.parseInt(cvlan) + 1 + "";
    	
    	String onuName =  createOnuName(rofhProduct,cvlan);
    	
    	String neFnd = pos.getFdn() + ":ONU=" + onuName;
    	
    	AnOnuAttemp onu = new AnOnuAttemp();
    	onu.setOnuId("");
    	onu.setLabelCn(onuName);
    	onu.setRelatedOltCuid(pos.getRelatedOltCuid());
    	onu.setRelatedPosCuid(pos.getCuid());
    	onu.setRelatedUpneCuid(pos.getCuid());
    	onu.setRelatedSiteCuid(pos.getRelatedSiteCuid());
    	onu.setRelatedAccessPoint(pos.getRelatedAccessPoint());
    	//onu.setRelatedCabCuid(pos.getRelatedCabCuid());
    	onu.setRelatedPosPortCuid(rofhProduct.getAccessPort());
    	onu.setRelatedUpnePortCuid(rofhProduct.getAccessPort());
		onu.setRelatedPonPortCuid(ptp.getCuid());
		onu.setRelatedOltPortCuid(ptp.getCuid());
		onu.setRelatedDistrictCuid(pos.getRelatedDistrictCuid());
		onu.setObjectid(ptpDao.getObjectId("AN_ONU"));
		onu.setCreateTime(new Date());
		onu.setPassword(RofhUtil.getRandomPw(8));
		onu.setFttx("2");
		onu.setAuthType(new BigDecimal("3"));
		onu.setRelatedEmsCuid(pos.getRelatedEmsCuid());
		onu.setFdn(neFnd);
		attempOnuDao.save(onu);
		
		//onu下联口
		String onuCuid = onu.getCuid();
		
		Ptp onuDowPtp = new Ptp();
		onuDowPtp.setObjectid(ptpDao.getObjectId("PTP"));
		onuDowPtp.setLabelCn(onuName + "下联口");
		onuDowPtp.setRelatedNeCuid(onuCuid);
		onuDowPtp.setPortState("2");
		onuDowPtp.setPortSubType(new BigDecimal("29"));
		onuDowPtp.setPortNo(new BigDecimal("1"));
		onuDowPtp.setVlan(cvlan);
		onuDowPtp.setRelatedEmsCuid(pos.getRelatedEmsCuid());
		onuDowPtp.setRelatedDistrictCuid(pos.getRelatedDistrictCuid());
		onuDowPtp.setFdn(neFnd + ":PTP=/rack=1/shelf=1/slot=2/port=1");
		onuDowPtp.setDevType(new BigDecimal("2"));
		onuDowPtp.setCreateTime(new Date());
		
		ptpDao.save(onuDowPtp);
		
		// onu上联口
		Ptp onuUpPtp = new Ptp();
		onuUpPtp.setObjectid(ptpDao.getObjectId("PTP"));
		onuUpPtp.setLabelCn(onuName+"上联口");
		onuUpPtp.setRelatedNeCuid(onuCuid);
		onuUpPtp.setPortState("2");
		onuUpPtp.setPortSubType(new BigDecimal("14"));
		onuUpPtp.setPortNo(new BigDecimal("1"));
		onuUpPtp.setRelatedEmsCuid(pos.getRelatedEmsCuid());
		onuUpPtp.setRelatedDistrictCuid(pos.getRelatedDistrictCuid());
		onuUpPtp.setFdn(neFnd + ":PTP=/rack=1/shelf=1/slot=1/port=1");
		onuUpPtp.setDevType(new BigDecimal("2"));
		onuUpPtp.setCreateTime(new Date());
		ptpDao.save(onuUpPtp);
		
		// 将pon口cvlan加一
		if(WebServiceConstant.AccessMode.FTTH.equals(rofhProduct.getAccessMode())){
			ptpDao.updatePonPortCvlan(ptp.getCuid(), cvlan);
		}
		
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("ONU_ID", onuCuid);
		dataMap.put("PTP_CUID", onuDowPtp.getCuid());
		dataMap.put("RELATED_ONU_UP_PTP", onuUpPtp.getCuid());
		dataMap.put("PASSWORD", onu.getPassword());
		dataMap.put("SVLAN", StringUtils.isEmpty(ptp.getSvlan()) ? "1025" : ptp.getSvlan());
		dataMap.put("CVLAN", cvlan);
		
		return dataMap;
	}

	/**
	 * 根据标准地址，进行端口的预占
	 * @param addrId
	 * @return
	 */
	public boolean occupyPort(RofhProduct rofhProduct){
		
		String addrId = rofhProduct.getRelatedCoverageAddrCuid();
		
		//查询端口
		List<Map<String,String>> list = resourceMapper.getFreePortFtth(addrId);
		if(list == null || list.size() == 0 || list.get(0).get("CUID") == null){
			
			return false;
		}
		
		String portCuid = list.get(0).get("CUID").toString();
		String neCuid = ObjectUtils.toString(list.get(0).get("RELATED_NE_CUID"));
		
		//获取接入方式 T_LOGIC_DEVICE_POS
		rofhProduct.setAccessMode(WebServiceConstant.AccessMode.FTTH);
		rofhProduct.setAccessDevice(neCuid);//端口对应设备ID
		rofhProduct.setAccessPort(portCuid);//端口ID
		rofhProduct.setProductType(WebServiceConstant.ProductType.宽带业务);
		
		//接口更新为预占
		ptpDao.updatePortState(portCuid, WebServiceConstant.PtpState.预占);
		
		return true;
		
	}
	
	/**
     * 生成ONU名称
     * 标准地址去掉省级和地市”+‘-’+‘业务区分标识’+‘-’+‘ONU001’+’-‘+’厂家编码’
     * @param dataMap
     * @return
     */
    public String createOnuName(RofhProduct rofhProduct,String cvlan){
    	
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
    	name.append("-ZD-ONU");
    //	name.append(String.format("%03d", Integer.parseInt(cvlan)));
    	name.append(RofhUtil.getRandomNum(5));
    	name.append("-");
    	//厂家编码
    	String vendorCode = resourceMapper.getDeviceVendorCodeByPosId(rofhProduct.getAccessDevice());
    	name.append(vendorCode);
    	
    	logger.info("virtual onu name is " + name);
		return name.toString();    	
    }
    
	
  
}
