package com.boco.rofh.webservice.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boco.rofh.constant.WebServiceConstant;
import com.boco.rofh.entity.PonWayAttemp;
import com.boco.rofh.entity.RofhBean;
import com.boco.rofh.entity.RofhProductAttemp;
import com.boco.rofh.entity.RofhProductHis;
import com.boco.rofh.webservice.service.ActiveNetService;

@Service
public class CancelResourceTask extends AbstractResourceTask{

	@Autowired
	private ActiveNetService activeNetService;
	
	private static final Logger logger = LoggerFactory.getLogger(CancelResourceTask.class);
	
	@Override
	protected void doBusiness(RofhBean rofhBean) {
		
		RofhProductAttemp attempProduct = (RofhProductAttemp) rofhBean.getProduct();
		
		if (attempProduct.getAccessMode().equals(WebServiceConstant.AccessMode.FTTH)) {
			
			if(attempProduct.getProductAction().equals(WebServiceConstant.ProductAction.装机) || attempProduct.getProductAction().equals(WebServiceConstant.ProductAction.移机)){
				
				rofhBean.getProduct().setCuid(attempProduct.getCuid());
				rofhBean.getProduct().setRelatedOrderCuid(attempProduct.getRelatedOrderCuid());
				activeNetService.netActivate(rofhBean, "3", "2");
			}
		}
		
		moveRofhProductToHis(attempProduct);
	}
	
	// 撤单临时表迁移历史表
	private void moveRofhProductToHis(RofhProductAttemp product) {
		
		moveAttempToHis(product);
		/**
		 * 装机场景释放端口 拆机场景不释放端口
		 */
		if (!product.getProductAction().equals(WebServiceConstant.ProductAction.拆机)) {
			if(!WebServiceConstant.AccessMode.WBS.equals(product.getAccessMode())){
				
				ptpDao.updatePortState(product.getAccessPort(), WebServiceConstant.PtpState.空闲);
			}
			
			PonWayAttemp ponway = attempPonWayDao.findByProductCuid(product.getCuid());
			
			if(ponway == null){
				return ;
			}
			//删除虚拟onu
			if(product.getAccessMode().equals(WebServiceConstant.AccessMode.FTTH) || product.getAccessMode().equals(WebServiceConstant.AccessMode.XDSL)){
				
				attempOnuDao.delete(ponway.getRelatedOnuCuid());;
				//删除虚拟ptp
				ptpDao.delete(ponway.getOnuPortCuid());
				ptpDao.delete(ponway.getOnuUpPortCuid());
			
			}
			//删除虚拟cpe
			else if(product.getAccessMode().equals(WebServiceConstant.AccessMode.WBS)){
				
				cpeDao.delete(ponway.getRelatedOnuCuid());
				wbsDao.updateWbsFreeById(ponway.getRelatedOltCuid());
			}
			//删除ponway
			attempPonWayDao.delete(ponway.getCuid());
		}
	}
		
	private void moveAttempToHis(RofhProductAttemp product){
		
		RofhProductHis productHis = new RofhProductHis();
		BeanUtils.copyProperties(product, productHis);
		productHis.setFinishTime(new Date());
		productHis.setProductAction(WebServiceConstant.ProductAction.撤单);
		hisProductDao.save(productHis);
		attempProductDao.delete(product);
	}
	
}