package com.boco.rofh.webservice.task;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.entity.AnOnu;
import com.boco.rofh.entity.AnOnuAttemp;
import com.boco.rofh.entity.PonWay;
import com.boco.rofh.entity.PonWayAttemp;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.entity.RofhProductHis;
import com.boco.rofh.entity.RofhProductSf;

/**
 * 资源报俊
 * @author wiggler
 *
 */
public abstract class AbstractCompleteTask extends AbstractResourceTask{


	/**
	 * 装机流程归档处理
	 * 移机流程归档处理
	 */
	protected void installComplete(RofhProductSf rofhProduct){
		
		PonWayAttemp attempPonWay = attempPonWayDao.findByProductCuid(rofhProduct.getCuid());
		if(attempPonWay == null){
			return;
		}
		/**
		 * 虚拟onu转移到正式表中
		 */
		if(!rofhProduct.getAccessMode().equals(WebServiceConstant.AccessMode.WBS)){
			
			String portid = rofhProduct.getAccessPort();
			ptpDao.updatePortState(portid, WebServiceConstant.PtpState.实占);
			if(rofhProduct.getAccessMode().equals(WebServiceConstant.AccessMode.FTTH) || rofhProduct.getAccessMode().equals(WebServiceConstant.AccessMode.XDSL)){
				
				AnOnuAttemp attempOnu = attempOnuDao.findOne(attempPonWay.getRelatedOnuCuid());
				AnOnu onu = new AnOnu();
				BeanUtils.copyProperties(attempOnu, onu);
				attempOnuDao.delete(attempOnu);
				onuDao.save(onu);
			}
		}
		
		PonWay ponWay = new PonWay();
		BeanUtils.copyProperties(attempPonWay, ponWay);
		attempPonWayDao.delete(attempPonWay);
		ponWayDao.save(ponWay);
	}
	
	protected void removeComplete(RofhProductSf rofhProduct) {
		
		
		PonWay ponway = ponWayDao.findByProductCuid(rofhProduct.getCuid());
		if(ponway == null){
			return;
		}
		if(rofhProduct.getAccessMode().equals(WebServiceConstant.AccessMode.FTTB) || rofhProduct.getAccessMode().equals(WebServiceConstant.AccessMode.XDSL)){
		
			onuDao.delete(ponway.getRelatedOnuCuid());
		
			ptpDao.delete(ponway.getOnuPortCuid());
			ptpDao.delete(ponway.getOnuUpPortCuid());

		}
		else if(rofhProduct.getAccessMode().equals(String.valueOf(WebServiceConstant.AccessMode.WBS))){
			
			//删除虚拟cpe
			cpeDao.delete(ponway.getRelatedOnuCuid());
			wbsDao.updateWbsFreeById(ponway.getRelatedOltCuid());
		}
		//删除ponway？
		ponWayDao.delete(ponway.getCuid());
	}
	
	protected RofhProductSf moveAttemp2SfProduct(RofhProductAttemp productAttemp){
		
		RofhProductSf productSf = new RofhProductSf();
		BeanUtils.copyProperties(productAttemp, productSf);
		productSf.setProductStatus(WebServiceConstant.ProductStatus.在网);
		productSf.setBusinessState(WebServiceConstant.BusinessState.已归档);
		productSf.setFinishTime(new Date());
		sfProductDao.save(productSf);
		attempProductDao.delete(productAttemp);
		
		return productSf;
	}
	
	protected void moveSf2HisProduct(RofhProductSf productSf){
		
		RofhProductHis productHis = new RofhProductHis();
		BeanUtils.copyProperties(productSf, productHis);
		productHis.setProductStatus(WebServiceConstant.ProductStatus.退网);
		productHis.setBusinessState(WebServiceConstant.BusinessState.已归档);
		productHis.setFinishTime(new Date());
		hisProductDao.save(productHis);
		sfProductDao.delete(productSf);
		
	} 
	
	protected void moveAttemp2HisProduct(RofhProductAttemp productAttemp){
		
		RofhProductHis productHis = new RofhProductHis();
		BeanUtils.copyProperties(productAttemp, productHis);
		productHis.setProductStatus(WebServiceConstant.ProductStatus.退网);
		productHis.setBusinessState(WebServiceConstant.BusinessState.已归档);
		productHis.setFinishTime(new Date());
		hisProductDao.save(productHis);
		attempProductDao.delete(productAttemp);
		
	}
}
