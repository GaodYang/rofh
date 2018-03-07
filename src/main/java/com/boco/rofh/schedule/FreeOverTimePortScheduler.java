package com.boco.rofh.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.dao.ProductDao;
import com.boco.rofh.dao.PtpDao;
import com.boco.rofh.dao.WbsDao;
import com.boco.rofh.entity.RofhProductAttemp;

@Component
public class FreeOverTimePortScheduler {

	
	private static final Logger logger = LoggerFactory.getLogger(FreeOverTimePortScheduler.class);
	
	@Autowired
	private ProductDao<RofhProductAttemp> attempProductDao;
	
	@Autowired
	private PtpDao ptpDao;
	
	@Autowired
	private WbsDao wbsDao;
	
	@Value("${server.port}")
	private String port;
	
	@Scheduled(cron="0 0 1 * * ?") 
	public void task(){
		
		logger.info("start FreeOverPortTimeTask...");
		if(!"9067".equals(port)){
			return;
		}
		//获取15天前的时间
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-15);
		Date date = calendar.getTime();
		logger.info("15天前的时间：" + date);
		//查询超时预占
		List<RofhProductAttemp> products = attempProductDao.findAllOverTimeProduct(date);
		
		if(products != null && products.size() > 0){
			
			List<String> ptps = new ArrayList<String>();
			List<String> wbs = new ArrayList<String>();
			for(RofhProductAttemp product : products){
				
				if(WebServiceConstant.AccessMode.WBS.equals(product.getAccessMode())){
					wbs.add(product.getAccessDevice());
				}else{
					ptps.add(product.getAccessPort());
				}
			}
			
			if(ptps.size() > 0){
				ptpDao.updatePortFreeBatch(ptps);
			}
			if(wbs.size() > 0){
				wbsDao.updateWbsFreeByIdBatch(wbs);
			}
			attempProductDao.deleteInBatch(products);
		}
		
		
		logger.info("end FreeOverPortTimeTask...");
	}
}
