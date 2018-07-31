package com.boco.rofh.webservice.task;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.constant.WebServiceConstant.ProducrAction;
import com.boco.rofh.entity.AnOnu;
import com.boco.rofh.entity.AnOnuAttemp;
import com.boco.rofh.entity.PonWay;
import com.boco.rofh.entity.PonWayAttemp;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.entity.RofhProductHis;
import com.boco.rofh.entity.RofhProductSf;
import com.boco.rofh.exception.UserException;

/**
 * 资源报俊
 * @author wiggler
 *
 */
public abstract class AbstractCompleteTask extends AbstractResourceTask{

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractCompleteTask.class);

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
		if(!WebServiceConstant.AccessMode.WBS.equals(rofhProduct.getAccessMode())){
			
			String portid = rofhProduct.getAccessPort();
			ptpDao.updatePortState(portid, WebServiceConstant.PtpState.实占);
			if(WebServiceConstant.AccessMode.FTTH.equals(rofhProduct.getAccessMode()) || WebServiceConstant.AccessMode.XDSL.equals(rofhProduct.getAccessMode())){
				
				AnOnuAttemp attempOnu = attempOnuDao.findOne(attempPonWay.getRelatedOnuCuid());
				AnOnu onu = new AnOnu();
				BeanUtils.copyProperties(attempOnu, onu);
				attempOnuDao.delete(attempOnu);
				try {
					
					onuDao.save(onu);
				} catch (Exception e) {
					logger.error("虚拟onu与存量数据冲突！");
				}
				
			}
		}
		
		PonWay ponWay = new PonWay();
		BeanUtils.copyProperties(attempPonWay, ponWay);
		attempPonWayDao.delete(attempPonWay);
		ponWayDao.save(ponWay);
	}
	
	protected void removeComplete(RofhProductSf rofhProduct) {
		
		if(!WebServiceConstant.AccessMode.WBS.equals(rofhProduct.getAccessMode())){
			
			String portid = rofhProduct.getAccessPort();
			if(StringUtils.isNotBlank(portid)){		
				
				ptpDao.updatePortState(rofhProduct.getAccessPort(),WebServiceConstant.PtpState.空闲);
			}
		}
		
		PonWay ponway = ponWayDao.findByProductCuid(rofhProduct.getCuid());
		if(ponway == null){
			return;
		}
		if(WebServiceConstant.AccessMode.FTTH.equals(rofhProduct.getAccessMode()) || WebServiceConstant.AccessMode.XDSL.equals(rofhProduct.getAccessMode())){
		
			onuDao.delete(ponway.getRelatedOnuCuid());
		
			ptpDao.delete(ponway.getOnuPortCuid());
			ptpDao.delete(ponway.getOnuUpPortCuid());

		}
		else if(WebServiceConstant.AccessMode.WBS.equals(rofhProduct.getAccessMode())){
			
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
	
	@Override
	protected void execute(RofhBean rofhBean){
		
		String accountName = rofhBean.getProduct().getAccountName();
		
		if(rofhBean.getProduct().getCuid() == null || rofhBean.getProduct().getProductAction() == null){
			//这是撤单
			return;
		}
		if(!rofhBean.getAction().equals(rofhBean.getProduct().getProductAction())){
			
			throw new UserException("操作有误，" + accountName + "当前产品状态为：" + ProducrAction.getName(rofhBean.getProduct().getProductAction()) + "！");
		}
		
		if(isExistId(accountName)){
			
			throw new UserException(accountName + "正在处理中！");
		}
		try{
			
			logger.info("正在报俊：" + accountName);
			this.doBusiness(rofhBean);
			logger.info("完成报俊：" + accountName);
		}catch(Exception e){
			
			logger.error("Task error !",e);
			throw new UserException(accountName + "报俊出错！" + e.getMessage());
		}finally{
			removeId(accountName);
		}
		
	}
}
