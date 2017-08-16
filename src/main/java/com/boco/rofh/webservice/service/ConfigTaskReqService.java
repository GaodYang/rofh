package com.boco.rofh.webservice.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.dao.ProdInfoDao;
import com.boco.rofh.entity.ProdServInfo;
import com.boco.rofh.webservice.pojo.ConfigTaskReq;
import com.boco.rofh.webservice.task.AbstractResourceTask;
import com.boco.rofh.webservice.task.ApInstallTask;
import com.boco.rofh.webservice.task.CttInstallTask;
import com.boco.rofh.webservice.task.FttbInstallTask;
import com.boco.rofh.webservice.task.FtthInstallTask;
import com.boco.rofh.webservice.task.RemoveTask;
import com.boco.rofh.webservice.task.ThreadPoolManager;

/**
 * 资源配置工单受理
 * @author gaoyang
 *
 */
@Service
public class ConfigTaskReqService extends BaseRofhWebService<ConfigTaskReq, Object>{
	
	
	private Map<String,AbstractResourceTask> configResourceTask;
	
	@Autowired
	private RemoveTask removeTask;
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
	protected Object doBusiness(final ConfigTaskReq req) {
		
		final String regionId = getRegionId();
		
		ThreadPoolManager.getInstance().addTask(this.getClass(), 
				new Runnable() {
					@Override
					public void run() {
						
						String key = "UNINSTALL";
						if(!"UNINSTALL".equals(req.getReqInfo().getActCode())){
							String pid = req.getProdInfoList().get(0).getProdSrvSpecCode();
							ProdServInfo info = prodInfoDao.findOne(pid);
							String type = info.getProdSrvType();
							type = StringUtils.isEmpty(type) ? "FTTB" : type;
							key = req.getReqInfo().getActCode() + type;
						}
						configResourceTask.get(key).doTask(req,regionId);
					}
				});
		
		return null;
		
	}
	
	@PostConstruct
	private void init(){
		
		configResourceTask = new HashMap<String, AbstractResourceTask>();
		//装机
		configResourceTask.put("INSTALLFTTB", fttbInstallTask);
		configResourceTask.put("INSTALLFTTH", ftthInstallTask);
		configResourceTask.put("INSTALLWBS", apInstallTask);
		configResourceTask.put("INSTALLCTT", cttInstallTask);
		//移机
		configResourceTask.put("MOVEFTTB", fttbInstallTask);
		configResourceTask.put("MOVEFTTH", ftthInstallTask);
		configResourceTask.put("MOVEWBS", apInstallTask);
		configResourceTask.put("MOVECTT", cttInstallTask);
		//拆机
		configResourceTask.put("UNINSTALL", removeTask);
	}
	
	
}
