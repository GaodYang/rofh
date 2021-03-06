package com.boco.rofh.webservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant.ProdSerType;
import com.boco.rofh.redis.RedisTaskManager;
import com.boco.rofh.utils.ConfigReqPool;
import com.boco.rofh.utils.RofhBeanWapper;
import com.boco.rofh.webservice.pojo.ConfigTaskReq;
import com.boco.rofh.webservice.task.AbstractResourceTask;
import com.boco.rofh.webservice.task.ApInstallTask;
import com.boco.rofh.webservice.task.CttInstallTask;
import com.boco.rofh.webservice.task.FttbInstallTask;
import com.boco.rofh.webservice.task.FtthInstallTask;
import com.boco.rofh.webservice.task.ImsInstallTask;
import com.boco.rofh.webservice.task.IptvInstallTask;
import com.boco.rofh.webservice.task.RemoveTask;
import com.boco.rofh.webservice.task.ThreadPoolManager;

/**
 * 资源配置工单受理
 * @author gaoyang
 *
 */
@Service
public class ConfigTaskReqService extends BaseRofhWebService<ConfigTaskReq, Object>{
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigTaskReqService.class);
	
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
	private IptvInstallTask iptvInstallTask;
	@Autowired
	private ImsInstallTask imsInstallTask;
	@Autowired
	private RofhBeanWapper beanWapper;
	@Resource(type = RedisTaskManager.class)
	private ConfigReqPool configManager;
	
	@Override
	protected Object doBusiness(ConfigTaskReq req) {
		
		String regionId = getRegionId();
		
		List<ConfigTaskReq> taskList = new ArrayList<>();
		
		String groupId = req.getReqInfo().getSrcOrderGrpId();
		if(StringUtils.isNotBlank(groupId)){
			
			logger.info("executing groupId is " + groupId);
			Set<ConfigTaskReq> set = configManager.getTask(req,regionId);
			if(set != null && set.size() > 0){
				
				logger.info(groupId + " now size is " + set.size());
				taskList.addAll(set);
			}
		}
		else{
			
			runTask(req,regionId);
		}
		if(taskList.isEmpty()){
			
			return null;
		}
		
		ThreadPoolManager.getInstance().addTask(this.getClass(), 
				new Runnable() {
					@Override
					public void run() {
						
						for(ConfigTaskReq taskReq : taskList){
							
							runTask(taskReq,regionId);
						}
						
						configManager.removeTask(req,regionId);
					}
				});
		
		return null;
		
	}
	
	public void runTask(ConfigTaskReq req , String regionId){
		
		String key = "UNINSTALL";
		if(!"UNINSTALL".equals(req.getReqInfo().getActCode())){
			String pid = req.getProdInfoList().get(0).getProdSrvSpecCode();
			key = beanWapper.getProdSerMap().get(pid);
			key = StringUtils.isEmpty(key) ? "FTTB" : key;

		}
		
		logger.info(req.getProdInfoList().get(0).getAccessNum() + " doing : " + key);
		configResourceTask.get(key).doTask(req,regionId);
	}
	
	@PostConstruct
	private void init(){
		
		configResourceTask = new HashMap<String, AbstractResourceTask>();
		//装机移机
		configResourceTask.put(ProdSerType.FTTB.name(), fttbInstallTask);
		configResourceTask.put(ProdSerType.FTTH.name(), ftthInstallTask);
		configResourceTask.put(ProdSerType.WBS.name(), apInstallTask);
		configResourceTask.put(ProdSerType.CTT.name(), cttInstallTask);
		configResourceTask.put(ProdSerType.IPTV.name(), iptvInstallTask);
		configResourceTask.put(ProdSerType.IMS.name(), imsInstallTask);
		//拆机
		configResourceTask.put("UNINSTALL", removeTask);
	}
	
	
}
