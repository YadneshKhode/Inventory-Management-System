package com.accenture.lkm.services;

import java.util.List;

import com.accenture.lkm.business.bean.PaymentReportBeanDateWise;
import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.business.bean.VendorWisePuchaseBean;
import java.text.ParseException;

public interface ReportsService {
	public List<PurchaseBean> getDateWisePurchaseDetails(String from, String to);
	public List<VendorWisePuchaseBean> getVendorWisePurchaseDetails(String from, String to, String vendorName);
	List<PaymentReportBeanDateWise> getVendorPmtHistoryDetails(String from, String to,String vendorName,String status) throws ParseException;
	List <PurchaseBean> getPurchaseIdList(String vendorName);
	List<PaymentReportBeanDateWise> getTxIdWisePaymentDetails(String from, String to,String vendorName,String transactionid) throws ParseException;
	List <PurchaseBean> getMaterialDetails(String transactionId);
}
