package com.boco.rofh.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.constant.WebServiceConstant.ProductAction;
import com.boco.rofh.dao.AddressDao;
import com.boco.rofh.dao.PonWayDao;
import com.boco.rofh.dao.ProductDao;
import com.boco.rofh.dao.PtpDao;
import com.boco.rofh.entity.PonWayAttemp;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhProduct;
import com.boco.rofh.entity.RofhProductSf;
import com.boco.rofh.mapper.RofhConfigMapper;
import com.boco.rofh.webservice.pojo.FinishRmTaskAsynReq;
import com.boco.rofh.webservice.pojo.FinishRmTaskAsynReq.FProdInfo;
import com.boco.rofh.webservice.pojo.FinishRmTaskAsynReq.Res;
import com.boco.rofh.webservice.pojo.FinishRmTaskAsynReq.ResAttr;
import com.boco.rofh.webservice.pojo.FinishRmTaskAsynReq.Ressrv;
import com.boco.rofh.webservice.pojo.RofhNameSpaceBean;

@Service
public class FinishMsgBuilder {

	@Autowired
	private ProductDao<RofhProductSf> sfProductDao;
	
	@Autowired
	private PonWayDao<PonWayAttemp> attempPonWayDao;
	
	@Autowired
	private PtpDao ptpDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private RofhConfigMapper configMapper;

	public String buildFinishMsg(RofhBean processBean){
		
		RofhProduct product = processBean.getProduct();
		
		FinishRmTaskAsynReq asynReq = new FinishRmTaskAsynReq();
		
		List<FProdInfo> prodList = new ArrayList<FProdInfo>();
		asynReq.setProdList(prodList);
		FProdInfo prodInfo = new FProdInfo();
		prodInfo.setProdInsId(product.getProductCode());
		prodList.add(prodInfo);
		
		List<Ressrv> ressrvList = new ArrayList<Ressrv>();
		prodInfo.setRessrvList(ressrvList);
		
		ressrvList.add(makeData(processBean));
		if (ProductAction.移机.equals(product.getProductAction())){
			
			List<RofhProductSf> sfProductList = sfProductDao.findByProductCode(product.getProductCode());
			
			if(sfProductList != null && sfProductList.size() > 0){
				
				RofhProductSf  sfProduct = sfProductList.get(0);
				processBean.setProduct(sfProduct);
			}
			
			processBean.getProduct().setProductAction(ProductAction.拆机);
			ressrvList.add(makeData(processBean));
			
		}
		
		
		return asynReq.toXml();
	}
	
	private Ressrv makeData(RofhBean rofhBean){
		
		Ressrv ressrv = new Ressrv();
		ressrv.setRessrvCode("PORT_RES_SRV");
		ressrv.setRessrvId(rofhBean.getProduct().getAccountName());
		ressrv.setRessrvName("端口资源服务");
		ressrv.setRessrvState("P");
		
		List<Res> resList = new ArrayList<Res>();
		ressrv.setResList(resList);
		Res res = new Res();
		res.setResAct("add");
		res.setResSpecCode("E");
		res.setResSpecName("端口");
		res.setResState("P");
		resList.add(res);
		
		List<ResAttr> resAttrList = new ArrayList<ResAttr>();
		res.setResAttrList(resAttrList);

		resAttrList.add(newResAttr("active_result",rofhBean.getActiveMsg()));
		resAttrList.add(newResAttr("terminal_code",""));
		resAttrList.add(newResAttr("terminal_name",""));
		
		
		
		if(rofhBean.getProduct().getAccessMode().equals(WebServiceConstant.AccessMode.WBS)){
			
			builderFinishWbsMsg(rofhBean.getProduct(),ressrv);
		}else{
			builderFinishFtthMsg(rofhBean.getProduct(),ressrv);
		}
		return ressrv;

	}
	
	private void builderFinishFtthMsg(RofhProduct product,Ressrv ressrv){
		
		Res res = ressrv.getResList().get(0);
		List<ResAttr> resAttrList = res.getResAttrList();
		/**
		 * 
		 */
		PonWayAttemp ponway = new PonWayAttemp();
		if(product.getProductAction().equals(WebServiceConstant.ProductAction.拆机)){
			ressrv.setRessrvState("F");
			res.setResState("A");
			res.setResAct("remove");
			//ponway = new PonWay();//getPonWayBO().getPonWayByProductCuid(sfdbproduct.getCuid());
		}else{
			
			ponway = attempPonWayDao.findByProductCuid(product.getCuid());
		}
		/////////////////////
		
		
		///
		//端口编码
		String portCode = ponway.getOnuPortCuid();
		resAttrList.add(newResAttr("port_code",portCode));
		//端口名称
		resAttrList.add(newResAttr("onu_port_name",ptpDao.findLabelCnByCuid(portCode)));
		
		
		//eqpt_code	设备编码
		String svlan = ponway.getSvlan();
		String cvlan = ponway.getCvlan();
		resAttrList.add(newResAttr("svlan",ObjectUtils.toString(svlan)));
		resAttrList.add(newResAttr("cvlan",ObjectUtils.toString(cvlan)));
	//	
		
		//
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		if(product.getAccessMode().equals(WebServiceConstant.AccessMode.FTTB) || WebServiceConstant.AccessMode.PON_LAN.equals(product.getAccessMode())){
			//			设备种类名称
			resAttrList.add(newResAttr("eqpt_kind",product.getSaleType()));
			if(product.getProductAction().equals(WebServiceConstant.ProductAction.拆机)){
				
				list = null;//getRofhConfigDAO().getOnuInfoById(RofhNameSpaceBean.buildBean(ponway.getCuid(), "", ""));
			}else{
				
				list = configMapper.getOnuInfoById(RofhNameSpaceBean.buildBean(ponway.getCuid(), "ATTEMP_", ""));
			}
			
		}else if (product.getAccessMode().equals(WebServiceConstant.AccessMode.FTTH) || WebServiceConstant.AccessMode.XDSL.equals(product.getAccessMode())){
			//			设备种类名称
			resAttrList.add(newResAttr("eqpt_kind",product.getSaleType()));
			if(product.getProductAction().equals(WebServiceConstant.ProductAction.拆机)){
				list = null;//getRofhConfigDAO().getOnuInfoById(RofhNameSpaceBean.buildBean(ponway.getCuid(), "", ""));
			}else{
				list = configMapper.getOnuInfoById(RofhNameSpaceBean.buildBean(ponway.getCuid(), "ATTEMP_", "ATTEMP_"));
			}
		}
		
		String eqpt_id = "";
		String eqpt_name = "";
		String room_id = "";	
		String room_name = "";	
		String area_id = "";	
		String area_name = "";	
		String olt_ip = "";
		String olt_port = "";
		String onu_auth_type = "";
		String onu_auth_value = "";
		String onu_type = "";
		
		String port_name = "";
		String olt_name = "";
		String onu_name = "";
		String onu_dev_type = "";
		String onu_id = "";
		String opticalfiberboxesname = "";
		String opticalfiberboxesterminals = "";
		
		resAttrList.add(newResAttr("fttx_mode",product.getAccessMode()));
		
		if(list != null && list.size() > 0){
			
			Map<String, String> map = list.get(0);
			eqpt_name = ObjectUtils.toString(map.get("NAME"));
			room_id = ObjectUtils.toString(map.get("ROOMID"));
			room_name = ObjectUtils.toString(map.get("ROOMNAME"));
			area_id = ObjectUtils.toString(map.get("AREAID"));
			area_name = ObjectUtils.toString(map.get("AREANAME"));
			eqpt_id =  ObjectUtils.toString(map.get("OBJECTID"));
			olt_ip = ObjectUtils.toString(map.get("DEV_IP"));
			olt_port = ObjectUtils.toString(map.get("PONNAME"));
			onu_auth_type = ObjectUtils.toString(map.get("AUTH_TYPE"));
			onu_auth_value = ObjectUtils.toString(map.get("VALUE"));
			onu_type = ObjectUtils.toString(map.get("FTTX"));
			
			///new
			port_name = ObjectUtils.toString(map.get("POSPORT"));
			olt_name = ObjectUtils.toString(map.get("OLTNAME"));
			onu_name = ObjectUtils.toString(map.get("ONUNAME"));
			onu_dev_type = ObjectUtils.toString(map.get("AMODEL"));
			onu_id = ObjectUtils.toString(map.get("ONU_ID"));
			opticalfiberboxesname = ObjectUtils.toString(map.get("RELATEDCAB"));

		}
		res.setResCode(eqpt_name);
		res.setResId(eqpt_id);
		//eqpt_name	设备名称
		resAttrList.add(newResAttr("eqpt_name",eqpt_name));
		resAttrList.add(newResAttr("eqpt_code",eqpt_name));
		//设备ID
		resAttrList.add(newResAttr("eqpt_id",eqpt_id));
		//room_id	机房ID
		resAttrList.add(newResAttr("room_id",room_id));
		//room_name	机房名称
		resAttrList.add(newResAttr("room_name",room_name));
		//area_id	所属区域ID
		resAttrList.add(newResAttr("area_id",area_id));
		//area_name	所属区域名称
		resAttrList.add(newResAttr("area_name",area_name));

		resAttrList.add(newResAttr("olt_ip",olt_ip));
		resAttrList.add(newResAttr("olt_port",olt_port));
		resAttrList.add(newResAttr("onu_auth_type",onu_auth_type));
		resAttrList.add(newResAttr("onu_auth_value",onu_auth_value));
		resAttrList.add(newResAttr("onu_type",onu_type));
		
		//add 2017 6 8 
		resAttrList.add(newResAttr("onu_name",onu_name));
		resAttrList.add(newResAttr("port_name",port_name));
		
		Map<String,String> map = configMapper.getMaintainNameByAddr(product.getRelatedCoverageAddrCuid());
		String maintain = map.get("MAINTAIN");
		String villages = map.get("VILLAGES");
		String sdtname = map.get("LABEL_CN");
		
		resAttrList.add(newResAttr("city_name",addressDao.getDistrictNameById(product.getBusinessCityD())));
		resAttrList.add(newResAttr("county_name",addressDao.getDistrictNameById(product.getBusinessCountyD())));

		resAttrList.add(newResAttr("maintain_name",StringUtils.isBlank(maintain) ? product.getRelatedMaintainCuid() : maintain));
		resAttrList.add(newResAttr("villages",StringUtils.isBlank(villages) ? product.getCellName() : villages));
		resAttrList.add(newResAttr("std_addr_name",StringUtils.isBlank(sdtname) ? product.getCellAddress() : sdtname));
		resAttrList.add(newResAttr("olt_name",olt_name));
		resAttrList.add(newResAttr("onu_dev_type",onu_dev_type));
		resAttrList.add(newResAttr("onu_id",onu_id));
		resAttrList.add(newResAttr("opticalfiberboxesname",opticalfiberboxesname));
		resAttrList.add(newResAttr("opticalfiberboxesterminals",opticalfiberboxesterminals));

		
	}
	
	
	private void builderFinishWbsMsg(RofhProduct product,Ressrv ressrv){
		
		Res res = ressrv.getResList().get(0);
		List<ResAttr> resAttrList = res.getResAttrList();
		/**
		 * 
		 */
		PonWayAttemp ponway = new PonWayAttemp();
		if(product.getProductAction().equals(WebServiceConstant.ProductAction.拆机)){
			
			ressrv.setRessrvState("F");
			res.setResState("A");
			res.setResAct("remove");//.getPonWayByProductCuid(sfdbproduct.getCuid());
		}else{
			
			ponway =  attempPonWayDao.findByProductCuid(product.getCuid()) ;
		}
		/////////////////////
		
		//端口编码
		resAttrList.add(newResAttr("port_code","0001"));
		//端口名称
		resAttrList.add(newResAttr("onu_port_name","CPE-业务口-01"));
		
		
		//eqpt_code	设备编码
		String eqptCode = ponway.getRelatedOnuCuid();
		String svlan = ponway.getSvlan();
		String cvlan = ponway.getCvlan();
		resAttrList.add(newResAttr("svlan",ObjectUtils.toString(svlan)));
		resAttrList.add(newResAttr("cvlan",ObjectUtils.toString(cvlan)));
	//	
		
		//
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
//		设备种类名称
		resAttrList.add(newResAttr("eqpt_kind",product.getSaleType()));
		
		if(product.getProductAction().equals(WebServiceConstant.ProductAction.拆机)){
			list = null;
		}
		else{
			list = configMapper.getWbsInfoById(eqptCode);
		}
		
		String eqpt_id = "";
		String eqpt_name = "";
		String room_id = "";	
		String room_name = "";	
		String area_id = "";	
		String area_name = "";	
		String olt_ip = "";
		String olt_port = "";
		String onu_auth_type = "";
		String onu_auth_value = "";
		String onu_type = "";
		
		String cpename = "";
		String model = "";
		
		resAttrList.add(newResAttr("fttx_mode",product.getAccessMode()));
		
		if(list != null && list.size() > 0){
			
			Map<String, String> map = list.get(0);
			eqpt_name = ObjectUtils.toString(map.get("NAME"));
			room_id = ObjectUtils.toString(map.get("ROOMID"));
			room_name = ObjectUtils.toString(map.get("ROOMNAME"));
			area_id = ObjectUtils.toString(map.get("AREAID"));
			area_name = ObjectUtils.toString(map.get("AREANAME"));
			eqpt_id =  ObjectUtils.toString(map.get("OBJECTID"));
			olt_ip = ObjectUtils.toString(map.get("DEV_IP"));
			olt_port = ObjectUtils.toString(map.get("PONNAME"));
			onu_auth_type = ObjectUtils.toString(map.get("AUTH_TYPE"));
			onu_auth_value = ObjectUtils.toString(map.get("VALUE"));
			onu_type = ObjectUtils.toString(map.get("FTTX"));
			
			cpename = ObjectUtils.toString(map.get("CPENAME"));
			model =  ObjectUtils.toString(map.get("N_MODEL"));
		}
		res.setResCode(eqpt_name);
		res.setResId(eqpt_id);
		//eqpt_name	设备名称
		resAttrList.add(newResAttr("eqpt_name",eqpt_name));
		resAttrList.add(newResAttr("eqpt_code",eqpt_name));
		//设备ID
		resAttrList.add(newResAttr("eqpt_id",eqpt_id));
		//room_id	机房ID
		resAttrList.add(newResAttr("room_id",room_id));
		//room_name	机房名称
		resAttrList.add(newResAttr("room_name",room_name));
		//area_id	所属区域ID
		resAttrList.add(newResAttr("area_id",area_id));
		//area_name	所属区域名称
		resAttrList.add(newResAttr("area_name",area_name));
		

		resAttrList.add(newResAttr("olt_ip",olt_ip));
		resAttrList.add(newResAttr("olt_port",olt_port));
		resAttrList.add(newResAttr("onu_auth_type",onu_auth_type));
		resAttrList.add(newResAttr("onu_auth_value",onu_auth_value));
		resAttrList.add(newResAttr("onu_type",onu_type));
		
		
		// add 2017 06 09
		resAttrList.add(newResAttr("onu_name",cpename));
		resAttrList.add(newResAttr("port_name",olt_port));
		
		Map<String,String> map = configMapper.getMaintainNameByAddr(product.getRelatedCoverageAddrCuid());
		String maintain = map.get("MAINTAIN");
		String villages = map.get("VILLAGES");
		String sdtname = map.get("LABEL_CN");

		resAttrList.add(newResAttr("maintain_name",StringUtils.isBlank(maintain) ? product.getRelatedMaintainCuid() : maintain));
		resAttrList.add(newResAttr("villages",StringUtils.isBlank(villages) ? product.getCellName() : villages));
		resAttrList.add(newResAttr("std_addr_name",StringUtils.isBlank(sdtname) ? product.getCellAddress() : sdtname));
		resAttrList.add(newResAttr("city_name",addressDao.getDistrictNameById(product.getBusinessCityD())));
		resAttrList.add(newResAttr("county_name",addressDao.getDistrictNameById(product.getBusinessCountyD())));
		
		resAttrList.add(newResAttr("olt_name",""));
		resAttrList.add(newResAttr("onu_dev_type",model));
		resAttrList.add(newResAttr("onu_id",""));
		resAttrList.add(newResAttr("opticalfiberboxesname",""));
		resAttrList.add(newResAttr("opticalfiberboxesterminals",""));

		
	}
	/**
	 * 
	 * @param code
	 * @param value
	 * @return
	 */
	private ResAttr newResAttr(String code,String value){
		
		ResAttr attr = new ResAttr();
		attr.setAttrCode(code);
		attr.setAttrVal(value);
		attr.setAttrValDesc(code);
		return attr;
	}	
}
