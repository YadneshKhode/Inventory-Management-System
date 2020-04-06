package com.accenture.lkm.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.lkm.business.bean.VendorWisePuchaseBean;
import com.accenture.lkm.entity.PaymentEntity;
import com.accenture.lkm.entity.PurchaseEntity;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * Implementation class for ReportsDAO which deals with all the reports related
 * queries<br/>
 *
 */
@Repository
public class ReportsDAOImpl implements ReportsDAO {
	private static Logger LOGGER = Logger.getLogger(ReportsDAOImpl.class);

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	/*
	 * This method fetches purchase details between the given dates.
	 * 
	 * @param from
	 * 
	 * @param to
	 * 
	 * @return List<PurchaseEntity>
	 */
	@Override
	public List<PurchaseEntity> getDateWisePurchaseDetails(Date from, Date to) {
		LOGGER.info("Execution Started [getDateWisePurchaseDetails()] with from:[" + from + "], to;[" + to + "]");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager
				.createQuery("select m from PurchaseEntity m where m.purchaseDate >= ?1 and m.purchaseDate <= ?2 ");
		query.setParameter(1, from);
		query.setParameter(2, to);
		List<PurchaseEntity> purchaseEntityList = query.getResultList();
		LOGGER.info("Execution over [getDateWisePurchaseDetails()] with from:[" + from + "], to;[" + to + "]");
		return purchaseEntityList;
	}

	/*
	 * This method fetches purchase details for a given vendor name. If to and from
	 * date is given then it fetches data between the given dates.
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
	public List<VendorWisePuchaseBean> getVendorPurchaseReport(Date from, Date to, String vendorName) {
		LOGGER.info("Execution Started [getVendorPurchaseReport()] with from:[" + from + "], to;[" + to
				+ "] vendorName [" + vendorName + "]");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String q = "select m.*, m.purchase_Amount - ifnull(n.totalPaidAmount, 0) as balance from Purchase as m left outer join (select sum(paid_Amount) as totalPaidAmount, purchase_Id from Payment group by purchase_Id) as n on m.purchase_id = n.purchase_id where m.vendor_name = ?1";
		if (from != null) {
			q = q.concat(" and  m.purchase_date >= '" + from + "'");
		}
		if (to != null) {
			q = q.concat(" and  m.purchase_date <= '" + to + "'");
		}
		Query query = entityManager.createNativeQuery(q);
		query.setParameter(1, vendorName);
		List<Object[]> resultList = query.getResultList();
		List<VendorWisePuchaseBean> vendorWisePuchaseBeanList = new ArrayList<>();
		for (Object[] row : resultList) {
			vendorWisePuchaseBeanList.add(new VendorWisePuchaseBean(row));
		}
		LOGGER.info("Execution over [getVendorPurchaseReport()] with from:[" + from + "], to;[" + to + "]");
		return vendorWisePuchaseBeanList;
	}

	/*
	 * This method fetches payment history based on the given Vendor Name and
	 * Payment Status. If to and from date is given then it fetches data between the
	 * given dates.
	 * 
	 * @param from
	 * 
	 * @param to
	 * 
	 * @return List<PaymentEntity>
	 */
	@Override
	public List<PaymentEntity> getVendorPmtHistoryDetails(Date from, Date to, String vendorName, String status) {
		LOGGER.info("Execution Started [getDateWisePaymentDetails()] with from:[" + from + "], to;[" + to + "vendor"
				+ vendorName + " ]");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String q = "select p.* from payment as p where p.paid_id in ( select max(paid_id) from payment where purchase_id in (select purchase_id from purchase where vendor_name = ?1 and status = ?2) group by purchase_id)";
		if (from != null) {
			q = q.concat(" and  p.paid_date >= '" + from + "'");
		}
		if (to != null) {
			q = q.concat(" and  p.paid_date <= '" + to + "'");
		}
		Query query = entityManager.createNativeQuery(q, PaymentEntity.class);
		query.setParameter(1, vendorName);
		query.setParameter(2, status);		
		List<PaymentEntity> purchaseEntity = query.getResultList();

		LOGGER.info("Execution over [getDateWisePaymentDetails()] with from:[" + from + "], to;[" + to + "vendor"
				+ vendorName + "]");
		return purchaseEntity;
	}

	/*
	 * This method fetches Purchase id list based on the given Vendor Name.
	 * 
	 * @param from
	 * 
	 * @param to
	 * 
	 * @return List <PurchaseEntity>
	 */
	@Override
	public List<PurchaseEntity> getPurchaseIdList(String vendorName) {
		LOGGER.info("Execution Started [getPurchaseIdList()] ");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createQuery("select p from PurchaseEntity p where p.vendorName =?1");
		query.setParameter(1, vendorName);

		List<PurchaseEntity> purchaseEntity = query.getResultList();
		LOGGER.info("Execution Over  [getPurchaseIdList()]");
		return purchaseEntity;
	}

	/*
	 * This method fetches vendor purchase details based on transactionId.
	 * 
	 * @param transactionId
	 * 
	 * @return List <PurchaseEntity>
	 */
	@Override
	public List<PurchaseEntity> getMaterialDetails(String transactionId) {
		LOGGER.info("Execution Started [getMaterialDetails()] ");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createQuery("select p from PurchaseEntity p where p.transactionId =?1");
		query.setParameter(1, transactionId);

		List<PurchaseEntity> purchaseEntity = query.getResultList();

		LOGGER.info("Execution Over [getMaterialDetails()] ");
		return purchaseEntity;
	}

	/*
	 * This method fetches payment history based on the given Vendor Name and
	 * Transaction Id. If to and from date is given then it fetches data between the
	 * given dates.
	 * 
	 * @param from
	 * 
	 * @param to
	 * 
	 * @return List<PaymentEntity>
	 */
	@Override
	public List<PaymentEntity> getTxIdWisePaymentDetails(Date from, Date to, String vendorName, String transactionid) {
		LOGGER.info("Execution Started [getTxIdWisePaymentDetails()] with from:[" + from + "], to;[" + to + "vendor"
				+ vendorName + " ]");

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String q = "select p from PaymentEntity p where p.purchaseEntity.purchaseId in(select u.purchaseId from PurchaseEntity u where u.transactionId=?1 and u.vendorName=?2)";
		if (from != null) {
			q = q.concat(" and  p.paidDate >= '" + from + "'");
		}
		if (to != null) {
			q = q.concat(" and  p.paidDate <= '" + to + "'");
		}
		Query query = entityManager.createQuery(q, PaymentEntity.class);
		query.setParameter(1, transactionid);
		query.setParameter(2, vendorName);

		List<PaymentEntity> purchaseEntity = query.getResultList();

		LOGGER.info("Execution over [getTxIdWisePaymentDetails()] with from:[" + from + "], to;[" + to + "vendor"
				+ vendorName + "]");
		return purchaseEntity;
	}

}
