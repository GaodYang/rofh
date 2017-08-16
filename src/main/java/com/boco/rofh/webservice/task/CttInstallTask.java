package com.boco.rofh.webservice.task;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.entity.RofhProduct;
import com.boco.rofh.utils.SpringUtil;

@Service
public class CttInstallTask extends AbstractInstallTask{
	
	/**
	 * 根据标准地址，进行端口的预占
	 * @param addrId
	 * @return
	 */
	synchronized public boolean occupyPort(RofhProduct rofhProduct){
	
			
		String addrId = rofhProduct.getRelatedCoverageAddrCuid();
			
		//查询端口
		List<Map<String,String>> list = resourceMapper.getFreePortCtt(addrId);
		if(list == null || list.size() == 0 || list.get(0).get("CUID") == null){
			
			return false;
		}
		
		String portCuid = list.get(0).get("CUID").toString();
		String neCuid = ObjectUtils.toString(list.get(0).get("RELATED_NE_CUID"));
		String type = list.get(0).get("TYPE").toString();
		
		rofhProduct.setAccessMode(type);
		rofhProduct.setAccessPort(portCuid);//端口ID
		rofhProduct.setAccessDevice(neCuid);//端口对应设备ID	
		
		//接口更新为预占
		ptpDao.updatePortState(portCuid, WebServiceConstant.PtpState.预占);
		
		return true;
					
	}
	
	protected void createAttempPonWay(RofhProduct rofhProduct) {

		Class<?> cls = FtthInstallTask.class;
		if(WebServiceConstant.AccessMode.PON_LAN.equals(rofhProduct.getAccessMode())){
			
			cls = FttbInstallTask.class;
		}
		
		((AbstractInstallTask)SpringUtil.getBean(cls)).createAttempPonWay(rofhProduct);
	}
	
	
	
}
