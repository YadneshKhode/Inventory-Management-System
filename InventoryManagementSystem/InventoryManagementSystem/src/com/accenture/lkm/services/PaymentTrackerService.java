package com.accenture.lkm.services;

import java.util.List;

import com.accenture.lkm.business.bean.PaymentBean;
import com.accenture.lkm.business.bean.PurchaseBean;

public interface PaymentTrackerService {
	
	public List<PurchaseBean> getPurchaseDetails(String vendoreName) throws Exception;

	public List<PaymentBean> getPaymentDetails(Integer purchaseId) throws Exception;
	
	public PaymentBean savePaymentDetails(PaymentBean paymentBean) throws Exception;
	
	public void updatePurchaseStatus(PurchaseBean purchaseBean) throws Exception;

}
