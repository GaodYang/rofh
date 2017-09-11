package com.boco.rofh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.dao.ActiveDao;
import com.boco.rofh.dao.CustomerDao;
import com.boco.rofh.dao.OrderDao;
import com.boco.rofh.dao.ProductDao;
import com.boco.rofh.entity.RofhActivate;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhCustomer;
import com.boco.rofh.entity.RofhOrder;
import com.boco.rofh.entity.RofhProduct;
import com.boco.rofh.entity.RofhProductAttemp;
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
}
