package com.accenture.lkm.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.PaymentBean;
import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.dao.PaymentDAO;
import com.accenture.lkm.dao.PurchaseDAO;
import com.accenture.lkm.entity.PaymentEntity;
import com.accenture.lkm.entity.PurchaseEntity;

@Service
public class PaymentTrackerServiceImpl implements PaymentTrackerService {
	
	private static Logger LOGGER =  Logger.getLogger(PaymentTrackerServiceImpl.class);
	
	@Autowired
	private PurchaseDAO purchaseDAO;
	
	@Autowired
	private PaymentDAO paymentDAO;
	
	/* 
	 * This method fetches purchase detail list for a given vendor name from purchase table.
	 * @param vendoreName
	 * @return List<PurchaseBean>
	 */
	@Override
	public List<PurchaseBean> getPurchaseDetails(String vendorName) throws Exception {		
		LOGGER.info("Execution Started [getPurchaseDetails()] with vendoreName:["+vendorName+"]");
		List<PurchaseEntity> purchaseEntityList = purchaseDAO.getPurchaseDetails(vendorName);
		List<PurchaseBean> purchaseBeanList = new ArrayList<PurchaseBean>();
		for(PurchaseEntity purchaseEntity : purchaseEntityList) {
			PurchaseBean purchaseBean = new PurchaseBean();
			BeanUtils.copyProperties(purchaseEntity, purchaseBean);
			purchaseBean.setPurchaseDate(purchaseEntity.getPurchaseDate().toString());
			purchaseBeanList.add(purchaseBean);			
		}		
		LOGGER.info("Execution over [getPurchaseDetails()] with vendoreName:["+vendorName+"]");
		return purchaseBeanList;
	}

	/* 
	 * This method fetches payment details for a given purchase id from Payment table.
	 * @param purchaseId
	 * @return List<PaymentBean>
	 */
	@Override
	public List<PaymentBean> getPaymentDetails(Integer purchaseId) throws Exception {
		LOGGER.info("Execution Started [getPaymentDetails()] with purchaseId:["+purchaseId+"]");
		List<PaymentEntity> paymentEntityList = paymentDAO.getPaymentDetails(purchaseId);
		List<PaymentBean> paymentBeanList = new ArrayList<PaymentBean>();
		for(PaymentEntity paymentEntity : paymentEntityList) {
			PaymentBean paymentBean = new PaymentBean();
			BeanUtils.copyProperties(paymentEntity, paymentBean);
			paymentBean.setPurchaseId(paymentEntity.getPurchaseEntity().getPurchaseId());
			paymentBean.setEnabled("N");
			paymentBean.setPaidDate(paymentEntity.getPaidDate().toString());
			paymentBeanList.add(paymentBean);			
		}		
		LOGGER.info("Execution over [getPaymentDetails()] with purchaseId:["+purchaseId+"]");
		return paymentBeanList;
	}

	/* 
	 * This method save the payment related data into the Payment table.
	 * @param paymentBean
	 * @return PaymentBean
	 */
	@Override
	public PaymentBean savePaymentDetails(PaymentBean paymentBean) throws Exception {
		LOGGER.info("Execution Started [savePaymentDetails()] with paymentBean:[" + paymentBean + "]");

		PaymentEntity paymentEntity = new PaymentEntity();
		BeanUtils.copyProperties(paymentBean, paymentEntity);
		PurchaseEntity purchaseEntity = new PurchaseEntity();
		purchaseEntity.setPurchaseId(paymentBean.getPurchaseId());
		paymentEntity.setPurchaseEntity(purchaseEntity);		
		paymentEntity.setPaidDate(Date.valueOf(paymentBean.getPaidDate()));
		paymentEntity = paymentDAO.insertPaymentDetail(paymentEntity);
		paymentBean.setPaidId(paymentEntity.getPaidId());
		LOGGER.info("Execution over [savePaymentDetails()] with paymentBean:[" + paymentBean + "]");
		return paymentBean;
	}
	
	/* 
	 * This method update the purchase related data into the Purchase table.
	 * @param purchaseBean
	 * @return void
	 */
	@Override
	public void updatePurchaseStatus(PurchaseBean purchaseBean) throws Exception {
		LOGGER.info("Execution Started [updatePurchaseStatus()] with purchaseBean:[" + purchaseBean + "]");
		PurchaseEntity purchaseEntity = new PurchaseEntity();
		BeanUtils.copyProperties(purchaseBean, purchaseEntity);	
		purchaseEntity.setPurchaseDate(Date.valueOf(purchaseBean.getPurchaseDate()));
		purchaseDAO.updatePurchaseDetail(purchaseEntity);
		LOGGER.info("Execution over [updatePurchaseStatus()] with purchaseBean:[" + purchaseBean + "]");

		
	}

	
	
	

}
