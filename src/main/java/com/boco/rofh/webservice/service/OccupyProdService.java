package com.boco.rofh.webservice.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.dao.AddressDao;
import com.boco.rofh.dao.ProdInfoDao;
import com.boco.rofh.dao.ProductDao;
import com.boco.rofh.entity.ProdServInfo;
import com.boco.rofh.entity.RofhFullAddress;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.exception.UserException;
import com.boco.rofh.webservice.pojo.GetResourceReq;
import com.boco.rofh.webservice.task.AbstractInstallTask;
import com.boco.rofh.webservice.task.ApInstallTask;
import com.boco.rofh.webservice.task.CttInstallTask;
import com.boco.rofh.webservice.task.FttbInstallTask;
import com.boco.rofh.webservice.task.FtthInstallTask;

/**
 * 4.6 业务资源预占接口
 * @author gaoyang
 *
 */
@Service
@Transactional
public class OccupyProdService extends BaseRofhWebService<GetResourceReq,Object>{

	
	private Map<String,AbstractInstallTask> taskMap = new HashMap<String,AbstractInstallTask>();
	
	@Autowired
	private ProductDao<RofhProductAttemp> productDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private FttbInstallTask fttbInstallTask;
	@Autowired
	private FtthInstallTask ftthInstallTask;
	@Autowired
	private ApInstallTask apInstallTask;
	@Autowired
	private CttInstallTask cttInstallTask;
	@Autowired
	private ProdInfoDao prodInfoDao;
	
	@Override
	protected Object doBusiness(GetResourceReq getResourceReq) {
		
		RofhProductAttemp rofhProduct = new RofhProductAttemp();
		//请求信息入库
		rofhProduct.setAccountName(getResourceReq.getAccount());
		//判断重复
		if(productDao.isExistByAccount(rofhProduct.getAccountName()) != null){
			
			//throw new UserException("用户" + rofhProduct.getAccountName() + "正在开通中！");
			return null;
		}
		//设置地址信息
		RofhFullAddress fullAddress = addressDao.findByObjectId(new BigDecimal(getResourceReq.getStdAddrId()));
		if (fullAddress != null) {
			rofhProduct.setRelatedCoverageAddrCuid(fullAddress.getCuid());
			rofhProduct.setBusinessProvinceD(fullAddress.getProvince());
			rofhProduct.setBusinessCityD(fullAddress.getCity());
			rofhProduct.setBusinessCountyD(fullAddress.getCounty());
		}else{
			
			throw new UserException("地址不可用！");
		}
		
		//进行预占
		ProdServInfo info = prodInfoDao.findOne(getResourceReq.getProdSrvCode());
		String type = info.getProdSrvType();
		type = StringUtils.isEmpty(type) ? "FTTB" : type;
		
		boolean flag = taskMap.get(type).occupyPort(rofhProduct);
		if(!flag){
			
			throw new UserException("没有空闲端口！");
		}
		
		//设置默认属性
		rofhProduct.setBusinessType(WebServiceConstant.BusinessType.宽带);
		rofhProduct.setRelatedBmclasstypeCuid("ROFH_PRODUCT");
		rofhProduct.setCellName(addressDao.getVillageById(rofhProduct.getRelatedCoverageAddrCuid()));
		rofhProduct.setDataSource("1");
		rofhProduct.setPreemptTime(new Date());
		
		productDao.save((RofhProductAttemp)rofhProduct);
		
		return null;
	}
	
	public Map<String, AbstractInstallTask> getTaskMap() {
		return taskMap;
	}



	public void setTaskMap(Map<String, AbstractInstallTask> taskMap) {
		this.taskMap = taskMap;
	}
	
	@Override
	public String doProcess(String xml, String regionId) {
		
		return super.doProcess(xml, regionId);
	}
	
	@PostConstruct
	private void init(){
		
		taskMap.put("FTTB", fttbInstallTask);
		taskMap.put("FTTH", ftthInstallTask);
		taskMap.put("WBS", apInstallTask);
		taskMap.put("CTT", cttInstallTask);
	}
}
