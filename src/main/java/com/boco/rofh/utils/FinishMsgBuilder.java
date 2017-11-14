package com.boco.rofh.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.constant.WebServiceConstant.DataSource;
import com.boco.rofh.constant.WebServiceConstant.ProductAction;
import com.boco.rofh.dao.AddressDao;
import com.boco.rofh.dao.PonWayDao;
import com.boco.rofh.dao.ProductDao;
import com.boco.rofh.dao.PtpDao;
import com.boco.rofh.entity.PonWay;
import com.boco.rofh.entity.PonWayAttemp;
import com.boco.rofh.entity.PonWayBase;
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
	private PonWayDao<PonWay> ponWayDao;
	
	@Autowired
	private PtpDao ptpDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private RofhConfigMapper configMapper;
	
	@Autowired
	private GenerateProductData generateProductData;
	
	@Value("${iptv.iptvuv}")
	private String iptvAdmVlan;
	
	@Value("${iptv.iptvvlan}")
	private String iptvOnVlan;
	
	@Value("${iptv.igmpvlan}")
	private String iptvLiveVlan;

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
			RofhProductSf sfProduct;
			if(sfProductList == null || sfProductList.isEmpty()){
				
				//throw new UserException(product.getAccountName() + "缺少存量数据");
				sfProduct = generateProductData.createPbossProduct(processBean);
			}else{
				sfProduct = sfProductList.get(0);
			}
			processBean.setProduct(sfProduct);
			processBean.getProduct().setProductAction(ProductAction.拆机);
			ressrvList.add(makeData(processBean));
			
		}
		
		
		return asynReq.toXml();
	}
	
	private Ressrv makeData(RofhBean rofhBean){
		
		RofhProduct product = rofhBean.getProduct();
		
		Ressrv ressrv = new Ressrv();
		ressrv.setRessrvCode("PORT_RES_SRV");
	//	ressrv.setRessrvId(product.getAccountName());
		ressrv.setRessrvId("10086");
		ressrv.setRessrvName("端口资源服务");
		
		List<Res> resList = new ArrayList<Res>();
		ressrv.setResList(resList);
		Res res = new Res();
		
		if(WebServiceConstant.ProductAction.拆机.equals(product.getProductAction())){
			ressrv.setRessrvState("F");
			res.setResState("A");
			res.setResAct("remove");
		}else{
			ressrv.setRessrvState("P");
			res.setResState("P");
			res.setResAct("add");
		}
		
		res.setResSpecCode("E");
		res.setResSpecName("端口");
		
		resList.add(res);
		
		List<ResAttr> resAttrList = new ArrayList<ResAttr>();
		res.setResAttrList(resAttrList);

		resAttrList.add(newResAttr("fttx_mode",product.getAccessMode()));
		resAttrList.add(newResAttr("active_result",rofhBean.getActiveMsg()));
		
		Map<String,String> maintainMap = null;
		if(product.getRelatedCoverageAddrCuid() != null){
			maintainMap = configMapper.getMaintainNameByAddr(product.getRelatedCoverageAddrCuid());
		}
		maintainMap = maintainMap == null ? new HashMap<>() : maintainMap;
		String maintain = maintainMap.get("MAINTAIN");
		String villages = maintainMap.get("VILLAGES");
		String sdtname = maintainMap.get("LABEL_CN");
		
		String city = addressDao.getDistrictNameById(product.getBusinessCityD());
		resAttrList.add(newResAttr("city_name",city));
		//area_id	所属区域ID
		resAttrList.add(newResAttr("area_id",product.getBusinessCityD()));
		//area_name	所属区域名称
		resAttrList.add(newResAttr("area_name",city));
		resAttrList.add(newResAttr("county_name",addressDao.getDistrictNameById(product.getBusinessCountyD())));
		resAttrList.add(newResAttr("maintain_name",StringUtils.isBlank(maintain) ? product.getRelatedMaintainCuid() : maintain));
		resAttrList.add(newResAttr("villages",StringUtils.isBlank(villages) ? product.getCellName() : villages));
		resAttrList.add(newResAttr("std_addr_name",StringUtils.isBlank(sdtname) ? product.getCellAddress() : sdtname));
		resAttrList.add(newResAttr("eqpt_kind",product.getSaleType()));
		
		if(WebServiceConstant.AccessMode.IPTV.equals(product.getAccessMode())){
			//组播VLAN IGMPVLAN=1031; 用户VLANIPTVUV=48; 点播VLANIPTVVLAN=1032
			resAttrList.add(newResAttr("iptv_adm_vlan",this.iptvAdmVlan));
			resAttrList.add(newResAttr("iptv_live_vlan",this.iptvLiveVlan));
			resAttrList.add(newResAttr("iptv_on_vlan",this.iptvOnVlan));
		}
		
		
		if(WebServiceConstant.AccessMode.WBS.equals(product.getAccessMode())){
			
			builderFinishWbsMsg(product,ressrv);
		}else{
			builderFinishFtthMsg(product,ressrv);
		}
		return ressrv;

	}
	
	private void builderFinishFtthMsg(RofhProduct product,Ressrv ressrv){
		
		Res res = ressrv.getResList().get(0);
		List<ResAttr> resAttrList = res.getResAttrList();
		
		
		PonWayBase ponway = new PonWayAttemp();
		
		List<Map<String, String>> list = new ArrayList<>();
		
		if(!WebServiceConstant.ProductAction.拆机.equals(product.getProductAction())){
			
			if(StringUtils.isNotBlank(product.getRelatedSheetCuid())){
				
				if(DataSource.SF.name().equals(product.getDataSource())){
					
					ponway = ponWayDao.findByProductCuid(product.getRelatedSheetCuid());
				}else{
					
					ponway = attempPonWayDao.findByProductCuid(product.getRelatedSheetCuid());
				}
			}else{
				
				ponway = attempPonWayDao.findByProductCuid(product.getCuid());
			}
			
			if(ponway != null){
			
				if(DataSource.SF.name().equals(product.getDataSource())){
					
					list = configMapper.getOnuInfoById(RofhNameSpaceBean.buildBean(ponway.getCuid(), "", ""));
				}
				else if(WebServiceConstant.AccessMode.FTTB.equals(product.getAccessMode()) || WebServiceConstant.AccessMode.PON_LAN.equals(product.getAccessMode())){
				
					list = configMapper.getOnuInfoById(RofhNameSpaceBean.buildBean(ponway.getCuid(), "ATTEMP_", ""));
				}
				else {
					
					list = configMapper.getOnuInfoById(RofhNameSpaceBean.buildBean(ponway.getCuid(), "ATTEMP_", "ATTEMP_"));
				}
			}else{
				
				ponway = new PonWayAttemp();
			}
		}
		
		//端口编码
		String portCode = ponway.getOnuPortCuid();
		resAttrList.add(newResAttr("port_code",portCode));
		//端口名称
		resAttrList.add(newResAttr("onu_port_name",ptpDao.findLabelCnByCuid(portCode)));
		
		resAttrList.add(newResAttr("svlan",ObjectUtils.toString(ponway.getSvlan())));
		resAttrList.add(newResAttr("cvlan",ObjectUtils.toString(ponway.getCvlan())));
		
			
		Map<String, String> map = list.isEmpty() ? new HashMap<>() : list.get(0);

		
		res.setResCode(ObjectUtils.toString(map.get("NAME")));
		res.setResId(ObjectUtils.toString(map.get("OBJECTID")));
		//eqpt_name	设备名称
		resAttrList.add(newResAttr("eqpt_name",ObjectUtils.toString(map.get("NAME"))));
		resAttrList.add(newResAttr("eqpt_code",ObjectUtils.toString(map.get("NAME"))));
		//设备ID
		resAttrList.add(newResAttr("eqpt_id",ObjectUtils.toString(map.get("OBJECTID"))));
		//room_id	机房ID
		resAttrList.add(newResAttr("room_id", ObjectUtils.toString(map.get("ROOMID"))));
		//room_name	机房名称
		resAttrList.add(newResAttr("room_name",ObjectUtils.toString(map.get("ROOMNAME"))));

		resAttrList.add(newResAttr("olt_ip",ObjectUtils.toString(map.get("DEV_IP"))));
		resAttrList.add(newResAttr("olt_port",ObjectUtils.toString(map.get("PONNAME"))));
		resAttrList.add(newResAttr("onu_auth_type",ObjectUtils.toString(map.get("AUTH_TYPE"))));
		resAttrList.add(newResAttr("onu_auth_value",ObjectUtils.toString(map.get("VALUE"))));
		resAttrList.add(newResAttr("onu_type",ObjectUtils.toString(map.get("FTTX"))));
		
		//add 2017 6 8 
		resAttrList.add(newResAttr("onu_name",ObjectUtils.toString(map.get("ONUNAME"))));
		resAttrList.add(newResAttr("port_name",ObjectUtils.toString(map.get("POSPORT"))));
		
		resAttrList.add(newResAttr("olt_name",ObjectUtils.toString(map.get("OLTNAME"))));
		resAttrList.add(newResAttr("onu_dev_type",ObjectUtils.toString(map.get("AMODEL"))));
		resAttrList.add(newResAttr("onu_id",ObjectUtils.toString(map.get("ONU_ID"))));
		resAttrList.add(newResAttr("opticalfiberboxesname",ObjectUtils.toString(map.get("RELATEDCAB"))));
		resAttrList.add(newResAttr("opticalfiberboxesterminals",""));

		
	}
	
	
	private void builderFinishWbsMsg(RofhProduct product,Ressrv ressrv){
		
		Res res = ressrv.getResList().get(0);
		List<ResAttr> resAttrList = res.getResAttrList();
	
		PonWayAttemp ponway = new PonWayAttemp() ;
		
		List<Map<String, String>> list = new ArrayList<>();

		if(!WebServiceConstant.ProductAction.拆机.equals(product.getProductAction())){
			
			ponway = attempPonWayDao.findByProductCuid(product.getCuid()) ;
			list = configMapper.getWbsInfoById(ponway.getRelatedOnuCuid());
		}
		
		//端口编码
		resAttrList.add(newResAttr("port_code","0001"));
		//端口名称
		resAttrList.add(newResAttr("onu_port_name","CPE-业务口-01"));
		//eqpt_code	设备编码
		resAttrList.add(newResAttr("svlan",ObjectUtils.toString(ponway.getSvlan())));
		resAttrList.add(newResAttr("cvlan",ObjectUtils.toString(ponway.getCvlan())));
			
		Map<String, String> map = list.isEmpty() ? new HashMap<>() : list.get(0);
		
		res.setResCode(ObjectUtils.toString(map.get("NAME")));
		res.setResId(ObjectUtils.toString(map.get("OBJECTID")));
		//eqpt_name	设备名称
		resAttrList.add(newResAttr("eqpt_name",ObjectUtils.toString(map.get("NAME"))));
		resAttrList.add(newResAttr("eqpt_code",ObjectUtils.toString(map.get("NAME"))));
		//设备ID
		resAttrList.add(newResAttr("eqpt_id",ObjectUtils.toString(map.get("OBJECTID"))));
		//room_id	机房ID
		resAttrList.add(newResAttr("room_id",ObjectUtils.toString(map.get("ROOMID"))));
		//room_name	机房名称
		resAttrList.add(newResAttr("room_name",ObjectUtils.toString(map.get("ROOMNAME"))));
		resAttrList.add(newResAttr("olt_ip",ObjectUtils.toString(map.get("DEV_IP"))));
		resAttrList.add(newResAttr("olt_port",ObjectUtils.toString(map.get("PONNAME"))));
		resAttrList.add(newResAttr("onu_auth_type",ObjectUtils.toString(map.get("AUTH_TYPE"))));
		resAttrList.add(newResAttr("onu_auth_value",ObjectUtils.toString(map.get("VALUE"))));
		resAttrList.add(newResAttr("onu_type",ObjectUtils.toString(map.get("FTTX"))));
		
		// add 2017 06 09
		resAttrList.add(newResAttr("onu_name",ObjectUtils.toString(map.get("CPENAME"))));
		resAttrList.add(newResAttr("port_name",ObjectUtils.toString(map.get("PONNAME"))));
		resAttrList.add(newResAttr("olt_name",""));
		resAttrList.add(newResAttr("onu_dev_type",ObjectUtils.toString(map.get("N_MODEL"))));
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
