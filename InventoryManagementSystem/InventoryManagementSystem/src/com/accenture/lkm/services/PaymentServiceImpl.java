package com.accenture.lkm.services;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.PaymentBean;
import com.accenture.lkm.dao.PaymentDAO;
import com.accenture.lkm.entity.PaymentEntity;
import com.accenture.lkm.entity.PurchaseEntity;

@Service
public class PaymentServiceImpl implements PaymentService {
	private static Logger LOGGER = Logger.getLogger(PurchaseServiceImpl.class);
	@Autowired
	private PaymentDAO paymentDAO;

	@Override
	public void savePaymentDetails(PaymentBean paymentBean) throws Exception {
		LOGGER.info("Execution Started [savePaymentDetails()] with paymentBean:[" + paymentBean + "]");

		PaymentEntity paymentEntity = new PaymentEntity();
		BeanUtils.copyProperties(paymentBean, paymentEntity);
		PurchaseEntity purchaseEntity = new PurchaseEntity();
		purchaseEntity.setPurchaseId(paymentBean.getPurchaseId());
		paymentEntity.setPurchaseEntity(purchaseEntity);		
		paymentEntity = paymentDAO.insertPaymentDetail(paymentEntity);

		LOGGER.info("Execution over [savePaymentDetails()] with paymentBean:[" + paymentBean + "]");
	}

}
