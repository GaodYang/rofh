package com.boco.rofh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boco.rofh.dao.OnuDao;
import com.boco.rofh.dao.PosDao;
import com.boco.rofh.dao.ProductDao;
import com.boco.rofh.entity.AnOnu;
import com.boco.rofh.entity.AnPos;
import com.boco.rofh.entity.RofhProductSf;
import com.boco.rofh.exception.UserException;
import com.boco.rofh.mapper.RelationMapper;
import com.boco.rofh.po.VillageResource;
import com.boco.rofh.utils.SpringUtil;
import com.boco.rofh.webservice.CrmServiceImpl;
import com.boco.rofh.webservice.ICrmService;

@Controller
@RequestMapping("/test")
public class test {

	@Autowired
	private OnuDao<AnOnu> onuDao;
	
	@Autowired
	private PosDao posDao;
	
	@Autowired
	private ProductDao<RofhProductSf> productDao;
	
	@Autowired
	private RelationMapper relationMapper;
	
	@Autowired
	private ICrmService crm;
	
	/**
	 * searchField=cuid&searchString=123&searchOper=eq	 ne bw bn ew en  cn nc nu nn in ni
	 * @param rows
	 * @param page
	 * @param sord
	 * @param sidx
	 * @return
	 */
	@RequestMapping("/onu")
	public @ResponseBody Page<AnOnu> getOnu(int rows,int page,String sord,String sidx){
		
		
		Pageable pagea = new PageRequest(page, rows, Direction.fromString(sord),sidx );

		return onuDao.findAll(pagea);
	}
	
	
	@RequestMapping("/relation")
	public @ResponseBody Map getRelation(){
	
		List<RofhProductSf> sfp = productDao.findAll();
		
		List<String> list = new ArrayList<>();
		for(RofhProductSf p : sfp){
			if(p.getAccessDevice() != null){
			list.add(p.getAccessDevice());
			}
		}
		
		
		List<AnOnu> onu = onuDao.findAll(list);
		
		
		List<Resss> list1 = new ArrayList<>();
		
		List<Link> list2 = new ArrayList<>();
		
		list = new ArrayList<>();
		for(AnOnu ao : onu){
			
			Resss res = new Resss();
			res.setId(ao.getCuid());
			res.setName(ao.getLabelCn());
			res.setCategory(0);
			res.setSymbol("roundRect");
			res.setSymbolSize(20);
			list1.add(res);
			
			Link link = new Link();
			link.setSource(ao.getCuid());
			link.setTarget(ao.getRelatedPosCuid());
			list2.add(link);
			
			if(ao.getRelatedPosCuid() != null){
				list.add(ao.getRelatedPosCuid());
			}
		}
		
		
		List<AnPos> pos = posDao.findAll(list);
		for(AnPos ap : pos){
			Resss res = new Resss();
			res.setId(ap.getCuid());
			res.setName(ap.getLabelCn());
			res.setCategory(1);
			res.setSymbol("rect");
			res.setSymbolSize(40);
			list1.add(res);

			Link link = new Link();
			link.setSource(ap.getCuid());
			link.setTarget(ap.getRelatedUpneCuid());
		//	list2.add(link);
			
		}
		Map<String,Object> map = new HashMap<>();
		map.put("data", list1);
		map.put("links", list2);
		return map;
	}
	
	public static class Link{
		String source;
		
		String target;

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getTarget() {
			return target;
		}

		public void setTarget(String target) {
			this.target = target;
		}
		
		@Override
		public int hashCode() {

			return new   HashCodeBuilder()//.appendSuper(super.hashCode())   
            .append(this.source)   
            .append(this.target)   
            .hashCode();   
		}
		
		@Override
		public boolean equals(Object obj) {
			
			if(!(obj instanceof Link))   {   
				  return   false;   
		    }   
			Link   rez   =   (Link)obj;   
		    return new EqualsBuilder()//.appendSuper(super.equals(obj))   
		    		.append(this.source,  rez.source)   
		    		.append(this.target,  rez.target)   
		    		.isEquals();   
		}
		
	}
	public static class Resss{
		
		String id;
		String name;
		int category;
		
		String symbol;
		
		int value = 20;
		
		int symbolSize;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getCategory() {
			return category;
		}

		public void setCategory(int category) {
			this.category = category;
		}

		public String getSymbol() {
			return symbol;
		}

		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public int getSymbolSize() {
			return symbolSize;
		}

		public void setSymbolSize(int symbolSize) {
			this.symbolSize = symbolSize;
		}
		
		
		@Override
		public int hashCode() {

			return new   HashCodeBuilder()//.appendSuper(super.hashCode())   
            .append(this.getId())   
            .hashCode();   
		}
		
		@Override
		public boolean equals(Object obj) {
			
			if(!(obj instanceof Resss))   {   
				  return   false;   
		    }   
			Resss   rez   =   (Resss)obj;   
		    return new EqualsBuilder()//.appendSuper(super.equals(obj))   
		    		.append(this.id,  rez.id)   
		    		.isEquals();   
		}
		@Override
		public String toString() {
			
			return ToStringBuilder.reflectionToString(this);
		}
		
		
	}
	
	@RequestMapping("/res")
	public @ResponseBody Map getVillageRes(){
		
		List<VillageResource> list = relationMapper.getVillageResource();
		
		
		Set<Resss> resList = new HashSet<>();

		
		List<Link> linkList = new ArrayList<>();

		for(VillageResource res : list){
			
			Resss ptp = new Resss();
			ptp.setId(res.getPtid());
			ptp.setCategory(1);
			ptp.setName(res.getPtname());
			ptp.setSymbol("roundRect");
			ptp.setSymbolSize(10);
			resList.add(ptp);
			
			Resss onu = new Resss();
			if("1".equals(res.getType())){
				
				onu.setId(res.getId());
				onu.setCategory(2);
				onu.setName(res.getName());
				onu.setSymbol("roundRect");
				onu.setSymbolSize(20);
				resList.add(onu);
				
				
				Map<String,String> map = relationMapper.getOnuLink(res.getId());
				
				if(map != null){
					Resss pos = new Resss();
					
					pos.setId(map.get("POSID"));
					pos.setCategory(3);
					pos.setName(map.get("POSNAME"));
					pos.setSymbol("roundRect");
					pos.setSymbolSize(30);
					resList.add(pos);
					
					Link link = new Link();
					link.setSource(res.getId());
					link.setTarget(pos.getId());
					linkList.add(link);
					
					
					Resss olt = new Resss();
					
					olt.setId(map.get("OLTID"));
					olt.setCategory(4);
					olt.setName(map.get("OLTNAME"));
					olt.setSymbol("roundRect");
					olt.setSymbolSize(40);
					resList.add(olt);
					
					
					link = new Link();
					link.setSource(pos.getId());
					link.setTarget(olt.getId());
					linkList.add(link);
				}
			}
			
			Resss pos = new Resss();
			if("2".equals(res.getType())){
				
				pos.setId(res.getId());
				pos.setCategory(3);
				pos.setName(res.getName());
				pos.setSymbol("roundRect");
				pos.setSymbolSize(30);
				resList.add(pos);
				
				Map<String,String> map = relationMapper.getPosLink(res.getId());
				if(map != null){
					
					Resss pos2 = new Resss();
					
					if(StringUtils.isNotBlank(map.get("POSID"))){
						pos2.setId(map.get("POSID"));
						pos2.setCategory(3);
						pos2.setName(map.get("POSNAME"));
						pos2.setSymbol("roundRect");
						pos2.setSymbolSize(30);
						resList.add(pos2);
						
						Link link = new Link();
						link.setSource(res.getId());
						link.setTarget(pos2.getId());
						linkList.add(link);
					}
					
					
					Resss olt = new Resss();
					
					olt.setId(map.get("OLTID"));
					olt.setCategory(4);
					olt.setName(map.get("OLTNAME"));
					olt.setSymbol("roundRect");
					olt.setSymbolSize(40);
					resList.add(olt);
					
					
					Link link = new Link();
					link.setSource(pos2.getId() == null ? pos.getId() : pos2.getId());
					link.setTarget(olt.getId());
					linkList.add(link);
				}
			}
			
			Resss village = new Resss();
			
			village.setId(res.getCuid());
			village.setCategory(0);
			village.setName(res.getVillage());
			village.setSymbol("roundRect");
			village.setSymbolSize(80);
			resList.add(village);
					
			
			Link link = new Link();
			link.setSource(res.getCuid());
			link.setTarget(res.getId());
			linkList.add(link);
			
			link = new Link();
			link.setSource(res.getPtid());
			link.setTarget(res.getId());
			linkList.add(link);
			
		}
		
		
		Map<String,Object> map  = new HashMap<>();
		map.put("data", resList);
		map.put("links", linkList);
		
		return map;
	}
	
	@Transactional
	@RequestMapping("/trans")
	public void trans(){
		
		
		((test33)SpringUtil.getBean(test33.class)).aa();
	
	}
	
	@RequestMapping("/tt")
	public void tt(){
		
		((ICrmService)SpringUtil.getBean(CrmServiceImpl.class)).queryAddress("34324", "454");
	}
}
