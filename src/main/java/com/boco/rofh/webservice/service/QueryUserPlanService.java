package com.boco.rofh.webservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.dao.PonWayDao;
import com.boco.rofh.dao.ProductDao;
import com.boco.rofh.dao.PtpDao;
import com.boco.rofh.entity.PonWay;
import com.boco.rofh.entity.RofhProductSf;
import com.boco.rofh.mapper.RofhConfigMapper;
import com.boco.rofh.utils.MapUtil;
import com.boco.rofh.webservice.pojo.QueryUserPlanReq;
import com.boco.rofh.webservice.pojo.QueryUserPlanResult;
import com.boco.rofh.webservice.pojo.QueryUserPlanResult.ProdServInfo;
import com.boco.rofh.webservice.pojo.QueryUserPlanResult.ResInfo;
import com.boco.rofh.webservice.pojo.RofhNameSpaceBean;

/**
 * 用户占用资源
 * @author gaoyang
 *
 */
@Service
public class QueryUserPlanService extends BaseRofhWebService<QueryUserPlanReq,QueryUserPlanResult>{

	@Autowired
	private ProductDao<RofhProductSf> productSfDao;
	
	@Autowired
	private PonWayDao<PonWay> ponWayDao;
	
	@Autowired
	private RofhConfigMapper configMapper;
	
	@Autowired
	private PtpDao ptpDao;

	@Override
	protected QueryUserPlanResult doBusiness(QueryUserPlanReq req) {
		
		String code = req.getProdInsId();
		QueryUserPlanResult planResult = new QueryUserPlanResult();
		
		//通过productcode获取productcuid
		List<RofhProductSf> products = productSfDao.findByProductCode(code);
		if(products == null || products.isEmpty()){
			
			return planResult;
		}
		RofhProductSf product = products.get(0);
		//获取ponway
		PonWay ponway = ponWayDao.findByProductCuid(product.getCuid());
		if(ponway == null){
			
			return planResult;
		}
		//获取onu信息
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		
		String portCode = "";
		String portName = "";
		
		if(WebServiceConstant.AccessMode.WBS.equals(product.getAccessMode())){
			
			list = configMapper.getWbsInfoById(ponway.getRelatedOnuCuid());
			portCode = "0001";
			portName = "CPE-业务口-01";
			
		}else{
			
			list = configMapper.getOnuInfoById(RofhNameSpaceBean.buildBean(ponway.getCuid(), "", ""));
			portName = ptpDao.findLabelCnByCuid(ponway.getOnuPortCuid());
		}
		
		if(list == null || list.size() == 0){
			
			return planResult;
		}
		
		
		
		Map<String, String> map = list.get(0);
		List<ProdServInfo> prodSrvList = new ArrayList<ProdServInfo>();
		planResult.setProdSrvList(prodSrvList);
		ProdServInfo info = new ProdServInfo();
		prodSrvList.add(info);
		
		info.setInvId("1");
		info.setName(MapUtil.getStringValue(map, "NAME"));
		info.setObjSpecId(MapUtil.getStringValue(map, "OBJECTID"));
		info.setObjSpecName("端口");
		info.setResSrvSpecId(product.getSaleType());
		info.setResType(product.getBusinessType());
		
		ResInfo resInfo = new ResInfo();
		info.setResInfo(resInfo);
		
		resInfo.setBoardName(MapUtil.getStringValue(map, "CARDNAME"));
		resInfo.setBoardTypeId(MapUtil.getStringValue(map, "CMODEL"));
		resInfo.setEqptAddr(MapUtil.getStringValue(map, "LOCATION"));
		resInfo.setEqptAreaName(MapUtil.getStringValue(map, "AREANAME"));
		resInfo.setEqptKindId(product.getAccessMode());
	//	resInfo.setEqptKindName(eqptKindName);
		resInfo.setEqptName(MapUtil.getStringValue(map, "NAME"));
		resInfo.setPortExchId("");
		resInfo.setPortExchName("");
		resInfo.setPortId(portCode);
		resInfo.setPortName(portName);
		resInfo.setPortSerialNumber(portCode);
		resInfo.setRoomName(MapUtil.getStringValue(map, "ROOMNAME"));
		
		if(WebServiceConstant.AccessMode.FTTB.equals(product.getAccessMode())){
			//			设备种类名称
			resInfo.setEqptKindName("ONU");
			//PORT_TYPE_ID	端口规格
			resInfo.setPortTypeId("37");
			resInfo.setPortTypeName("电FE口");
			
		}else if (WebServiceConstant.AccessMode.FTTH.equals(product.getAccessMode())){
			//			设备种类名称
			resInfo.setEqptKindName("POS");
			//PORT_TYPE_ID	端口规格
			resInfo.setPortTypeId("29");
			resInfo.setPortTypeName("ETH");
			
		}else if(WebServiceConstant.AccessMode.WBS.equals(product.getAccessMode())){
			
//				设备种类名称
				resInfo.setEqptKindName("WBS");
				//PORT_TYPE_ID	端口规格
			//	resInfo.setPortTypeId("29");
			//	resInfo.setPortTypeName("ETH");
		}
		
		
		info.setResType(resInfo.getPortTypeName());//add:
			
		
		return planResult;
	}
}
