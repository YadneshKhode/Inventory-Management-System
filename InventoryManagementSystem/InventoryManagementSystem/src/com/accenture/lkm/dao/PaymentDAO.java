package com.accenture.lkm.dao;

import java.util.List;

import com.accenture.lkm.entity.PaymentEntity;

public interface PaymentDAO {
	
	
	public List<PaymentEntity> getPaymentDetails(Integer purchaseId);
	public PaymentEntity insertPaymentDetail(PaymentEntity paymentEntity) throws Exception;

}
