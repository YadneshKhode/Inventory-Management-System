package com.accenture.lkm.services;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.PaymentReportBeanDateWise;
import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.business.bean.VendorWisePuchaseBean;
import com.accenture.lkm.dao.ReportsDAO;
import com.accenture.lkm.entity.PaymentEntity;
import com.accenture.lkm.entity.PurchaseEntity;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * Implementation class for ReportsService to deal with all reports related
 * detail.
 *
 */
@Service
public class ReportsServiceImpl implements ReportsService {

	private static Logger LOGGER = Logger.getLogger(ReportsServiceImpl.class);

	@Autowired
	private ReportsDAO reportsDAO;
	List<PurchaseEntity> purchaseEntity;

	/*
	 * This method call the ReportsDAO class getDateWisePurchaseDetails method to
	 * fetches purchase details between the given to and from date.
	 * 
	 * @param from
	 * 
	 * @param to
	 * 
	 * @return List<PurchaseEntity>
	 */
	@Override
	public List<PurchaseBean> getDateWisePurchaseDetails(String from, String to) {
		LOGGER.info("Execution Started [getDateWisePurchaseDetails()] with from:[" + from + "] to:" + to + "]");
		List<PurchaseEntity> purchaseEntityList = reportsDAO.getDateWisePurchaseDetails(Date.valueOf(from),
				Date.valueOf(to));
		List<PurchaseBean> purchaseBeanList = new ArrayList<PurchaseBean>();
		for (PurchaseEntity purchaseEntity : purchaseEntityList) {
			PurchaseBean purchaseBean = new PurchaseBean();
			BeanUtils.copyProperties(purchaseEntity, purchaseBean);
			purchaseBeanList.add(purchaseBean);
		}
		LOGGER.info("Execution over [getDateWisePurchaseDetails()] with from:[" + from + "] to:" + to + "]");
		return purchaseBeanList;
	}

	/*
	 * This method calls the ReportsDAO class getVendorPurchaseReport method to
	 * fetches purchase details for a given vendor name. If to and from date is
	 * given then it filter the data between the given dates.
	 * 
	 * @param from
	 * 
	 * @param to
	 * 
	 * @param vendorName
	 * 
	 * @return List<PurchaseEntity>
	 */
	@Override
	public List<VendorWisePuchaseBean> getVendorWisePurchaseDetails(String from, String to, String vendorName) {
		LOGGER.info("Execution Started [getVendorWisePurchaseDetails()] with from:[" + from + "] to:" + to
				+ "] + vendorName [" + vendorName + "]");
		List<VendorWisePuchaseBean> vendorWisePuchaseBeanList = reportsDAO.getVendorPurchaseReport(
				from != null && !from.equals("") ? Date.valueOf(from) : null,
				to != null && !to.equals("") ? Date.valueOf(to) : null, vendorName);
		LOGGER.info("Execution over [getVendorWisePurchaseDetails()] with from:[" + from + "] to:" + to
				+ "] + vendorName [" + vendorName + "]");
		return vendorWisePuchaseBeanList;

	}

	/*
	 * This method is used to invoke getVendorPmtHistoryDetails method of ReportsDAO
	 * class.
	 * 
	 * @param from
	 * 
	 * @param to
	 * 
	 * @param vendorname
	 * 
	 * @param status
	 * 
	 * @return List<PaymentReportBeanDateWise>
	 */

	@Override
	public List<PaymentReportBeanDateWise> getVendorPmtHistoryDetails(String from, String to, String vendorName,
			String status) throws ParseException {
		LOGGER.info("Execution Started [getDateWisePaymentDetails()] with from:["
				+ from + "] to:" + to + "vendorName" + vendorName + "]");		
		PaymentReportBeanDateWise paidBean = null;
		List<PaymentEntity> paymentEntityList = null;
		paymentEntityList = reportsDAO.getVendorPmtHistoryDetails(
				from != null && !from.equals("") ? Date.valueOf(from) : null,
				to != null && !to.equals("") ? Date.valueOf(to) : null, vendorName, status);

		List<PaymentReportBeanDateWise> purchaseBeanList = new ArrayList<PaymentReportBeanDateWise>();
		for (PaymentEntity paymentEntity : paymentEntityList) {
			paidBean = new PaymentReportBeanDateWise();
			BeanUtils.copyProperties(paymentEntity, paidBean);
			purchaseBeanList.add(paidBean);
		}
		LOGGER.info("Execution over [getDateWisePaymentDetails()]  with from:["
				+ from + "] to:" + to + "]");
		return purchaseBeanList;
	}

	/*
	 * This method is used to invoke getPurchaseIdList method of ReportsDAO class.
	 * 
	 * @param vendorName
	 * 
	 * @return List <PurchaseBean>
	 */
	public List<PurchaseBean> getPurchaseIdList(String vendorName) {
		LOGGER.info("Execution started getPurchaseIdList ");
		List<PurchaseBean> puchaseBeanList = new ArrayList<PurchaseBean>();
		purchaseEntity = reportsDAO.getPurchaseIdList(vendorName);
		for (PurchaseEntity pentity : purchaseEntity) {
			PurchaseBean purchaseBean = new PurchaseBean();
			BeanUtils.copyProperties(pentity, purchaseBean);
			puchaseBeanList.add(purchaseBean);
		}
		LOGGER.info("Execution over getPurchaseIdList ");
		return puchaseBeanList;

	}

	/*
	 * This method is used to invoke getMaterialDetails method of ReportsDAO class.
	 * 
	 * @param from
	 * 
	 * @param transactionId
	 * 
	 * @return List <PurchaseBean>
	 */
	public List<PurchaseBean> getMaterialDetails(String transactionId) {
		LOGGER.info("Execution started getMaterialDetails ");
		List<PurchaseBean> puchaseBeanList = new ArrayList<PurchaseBean>();

		purchaseEntity = reportsDAO.getMaterialDetails(transactionId);
		for (PurchaseEntity pentity : purchaseEntity) {
			PurchaseBean purchaseBean = new PurchaseBean();
			BeanUtils.copyProperties(pentity, purchaseBean);
			puchaseBeanList.add(purchaseBean);
		}

		LOGGER.info("Execution over getMaterialDetails ");
		return puchaseBeanList;
	}

	/*
	 * This method is used to invoke getTxIdWisePaymentDetails method of ReportsDAO
	 * class.
	 * 
	 * @param from
	 * 
	 * @param to
	 * 
	 * @param vendorName
	 * 
	 * @param transactionId
	 * 
	 * @return List<PaymentReportBeanDateWise>
	 */
	@Override
	public List<PaymentReportBeanDateWise> getTxIdWisePaymentDetails(String from, String to, String vendorName,
			String transactionid) throws ParseException {
		LOGGER.info("Execution Started [getTxIdWisePaymentDetails()]  with from:[" + from + "] to:" + to + "vendorName"
				+ vendorName + "]");
		List<PaymentEntity> paymentEntityList = null;

		paymentEntityList = reportsDAO.getTxIdWisePaymentDetails(
				from != null && !from.equals("") ? Date.valueOf(from) : null,
				to != null && !to.equals("") ? Date.valueOf(to) : null, vendorName, transactionid);

		List<PaymentReportBeanDateWise> purchaseBeanList = new ArrayList<PaymentReportBeanDateWise>();
		for (PaymentEntity paymentEntity : paymentEntityList) {
			PaymentReportBeanDateWise paymentReportBeanDateWise = new PaymentReportBeanDateWise();
			BeanUtils.copyProperties(paymentEntity, paymentReportBeanDateWise);
			purchaseBeanList.add(paymentReportBeanDateWise);

		}
		LOGGER.info("Execution over [getTxIdWisePaymentDetails()] with from:[" + from + "] to:" + to + "]");
		return purchaseBeanList;
	}

}
