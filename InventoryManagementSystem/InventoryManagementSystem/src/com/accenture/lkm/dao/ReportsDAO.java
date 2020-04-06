package com.accenture.lkm.dao;

import java.sql.Date;
import java.util.List;

import com.accenture.lkm.business.bean.VendorWisePuchaseBean;
import com.accenture.lkm.entity.PaymentEntity;
import com.accenture.lkm.entity.PurchaseEntity;


public interface ReportsDAO {
	public List<PurchaseEntity> getDateWisePurchaseDetails(Date from, Date to);

	public List<VendorWisePuchaseBean> getVendorPurchaseReport(Date from, Date to, String vendorName);

	List<PaymentEntity> getVendorPmtHistoryDetails(Date from, Date to, String vendorName, String status);

	List<PurchaseEntity> getPurchaseIdList(String vendorName);

	List<PurchaseEntity> getMaterialDetails(String transactionId);

	List<PaymentEntity> getTxIdWisePaymentDetails(Date from, Date to, String vendorName, String transactionid);

}
