package com.boco.rofh.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;
import org.apache.axis.encoding.XMLType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.controller.ActiveMsg.Device;
import com.boco.rofh.dao.ActiveDao;
import com.boco.rofh.dao.ActiveIptvDao;
import com.boco.rofh.dao.CustomerDao;
import com.boco.rofh.dao.OrderDao;
import com.boco.rofh.dao.ProductDao;
import com.boco.rofh.entity.ActiveResp;
import com.boco.rofh.entity.RofhActivate;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhCustomer;
import com.boco.rofh.entity.RofhOrder;
import com.boco.rofh.entity.RofhProduct;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.mapper.ActiveMapper;
import com.boco.rofh.utils.XStreamUtil;
import com.boco.rofh.webservice.service.ActiveNetService;

@Controller
@RequestMapping("/active")
public class ActiveController {

	@Autowired
	private ProductDao<RofhProductAttemp> productDao;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ActiveNetService activeNetService;
	
	@Autowired
	private ActiveDao activeDao;
	
	@Autowired
	private ActiveMapper activeMapper;
	
	@Autowired
	private ActiveIptvDao activeIptvDao;
	
	@Value("${activeUrl}")
	private String activeUrl;
	
	private static final Logger logger = LoggerFactory.getLogger(ActiveController.class);
	
	
	@RequestMapping("/reActive")
	public @ResponseBody String reActive(String orderId){
		
		RofhProduct product = productDao.findByOrderId(orderId);
		
		if(product == null){
			
			return "用户宽带数据不存在！";
		}
		if(!WebServiceConstant.AccessMode.FTTH.equals(product.getAccessMode())){
			
			return "只有FTTH类型的宽带才能激活！";
		}
		
		RofhBean rofhBean = new RofhBean();
		rofhBean.setProduct(product);
		RofhOrder order = orderDao.findOne(product.getRelatedOrderCuid());
		rofhBean.setOrder(order);
		
		RofhCustomer customer = customerDao.findOne(product.getRelatedCustomerCuid());
		rofhBean.setCustomer(customer);
		
		try {
			
			return activeNetService.netActivate(rofhBean, true);
		} catch (Exception e) {
			
			return e.getMessage();
		}
	}
	
	@RequestMapping("/list")
	public @ResponseBody Page<RofhActivate> getAll(boolean _search,int rows,int page,String sord,String sidx
			,String searchField,String searchString,String searchOper){
		
		
		Pageable pagea = new PageRequest(page - 1, rows, Direction.fromString(sord),sidx );

		Page<RofhActivate> list = activeDao.findAll(new Specification<RofhActivate>() {
			
			@Override
			public Predicate toPredicate(Root<RofhActivate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			
				List<Predicate> list = new ArrayList<>();
				if(_search){
					
					list.add(cb.equal(root.get(searchField).as(String.class),  searchString ));
				}
				
				list.add(cb.notEqual(root.get("activateStatus").as(String.class),  "已完成" ));
				
				Predicate[] p = new Predicate[list.size()];  
				return cb.and(list.toArray(p));
			}
		},pagea);
		
		return list;
	}
	
	@RequestMapping("/doAll")
	public @ResponseBody String doAll() {
		
		List<RofhActivate> list = activeDao.findAll(new Specification<RofhActivate>() {
			
			@Override
			public Predicate toPredicate(Root<RofhActivate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
				List<Predicate> list = new ArrayList<>();
					
				list.add(cb.greaterThan(root.get("activateTime").as(Date.class),  getYesToday()));
				list.add(cb.equal(root.get("activateStatus").as(String.class),  "激活异常" ));
				
				Predicate[] p = new Predicate[list.size()];  
				return cb.and(list.toArray(p));
				
			}
		});
		
		logger.debug("待处理数据：" + list.size());
		
		list.forEach( active -> {
			this.reActive(active.getRelatedOrderCuid());
		});
		
		return "success";
	}
	
	
	@RequestMapping("/all")
	public @ResponseBody String all() {
		
		List<RofhActivate> list = activeMapper.getAllActive();
		logger.debug("激活总数为：" + list.size());
		Map<String,List<RofhActivate>> activeMap = new HashMap<>();
		list.forEach( active -> {
			String ems = active.getStandardName();
			if(activeMap.containsKey(ems)) {
				List<RofhActivate> activeList = activeMap.get(ems);
				activeList.add(active);
			} else {
				List<RofhActivate> activeList = new ArrayList<>();
				activeList.add(active);
				activeMap.put(ems, activeList);
			}
		} );
		
		activeMap.forEach((ems,activeList) -> {
			
			logger.debug("处理ems:"+ ems + ",共" + activeList.size() + "条数据");
			
			ActiveMsg activeMsg = new ActiveMsg();
			activeMsg.setType("4");
			activeMsg.setProductId("iptv-ims");
			Set<Device> deviceList = new HashSet<>();
			activeMsg.setDeviceList(deviceList);
			for(RofhActivate active : activeList){
				
				Device device = new Device();
				device.setEms(ems);
				device.setCvlan(active.getCvlan());
				device.setFactory(active.getOnuVender());
				device.setLabelDev(active.getRelatedOltDevip());
				device.setOltName(active.getRelatedOltName());
				device.setOnuName(active.getOnuName());
				device.setPassword(active.getLogicid());
				device.setPonPort(active.getOltPortName());
				device.setSvlan(active.getSvlan());
				deviceList.add(device);
				if(deviceList.size() == 100) {
					
					doActive(activeMsg);
					deviceList = new HashSet<>();
				}
			}
			if(deviceList.size() > 0) {
				
				doActive(activeMsg);
			}
			
			logger.debug(ems+"处理完成");
		});
		
		return "success";
	}
	
	private void doActive(ActiveMsg activeMsg) {
		
		
		try {
			String activeResult = this.sendActiveMsg(activeMsg);
		
			logger.debug("激活结果：" + activeResult);
			ActiveResp activeResp = XStreamUtil.fromXml(ActiveResp.class, activeResult, null, null);
			//插入去激活数据
			activeIptvDao.save(activeResp.getActiveList());
		} catch (Exception e) {
			
			logger.error("激活异常",e);
		}
	}
	
	private String sendActiveMsg(ActiveMsg active) throws Exception {

		String xml = XStreamUtil.toXml(active,null);
		logger.debug("进行激活：" + xml);
		return invoke(activeUrl,"","active",xml,false);
	}
	
	
	private String invoke(String url,String nameSpace,String method,String xml,boolean arg0) throws Exception {
		
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setUseSOAPAction(true);
		call.setOperationStyle(Style.WRAPPED);
		call.setOperationUse(Use.LITERAL);
		call.setTargetEndpointAddress(url);
		call.setOperationName(new QName(nameSpace,method));
		if(arg0) {
			call.addParameter((String) "arg0", XMLType.XSD_STRING, ParameterMode.IN);
		}
		return (String) call.invoke(new Object[]{xml});
	}

	private Date getYesToday() {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}
}
