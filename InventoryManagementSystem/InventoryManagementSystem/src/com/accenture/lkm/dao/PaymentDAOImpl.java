package com.accenture.lkm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.lkm.entity.PaymentEntity;
import com.accenture.lkm.entity.PurchaseEntity;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * Implementation class for PaymentDAO to perform the CRUD operation on Payment
 * table <br/>
 *
 */
@Repository
public class PaymentDAOImpl implements PaymentDAO {

	private static Logger LOGGER = Logger.getLogger(PaymentDAOImpl.class);

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	/*
	 * This method fetches the payment data based on the given purchase id from Payment table.
	 * 
	 * @param paymentEntity
	 * 
	 * @return PaymentEntity
	 */
	@Override
	public List<PaymentEntity> getPaymentDetails(Integer purchaseId) {
		LOGGER.info("Execution Started [getPaymentDetails()] with purchaseId:[" + purchaseId + "]");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery("select m from PaymentEntity m where m.purchaseEntity.purchaseId = ?1");
		query.setParameter(1, purchaseId);
		List<PaymentEntity> paymentEntityList = query.getResultList();
		LOGGER.info("Execution over [getPaymentDetails()] with purchaseId:[" + purchaseId + "]");
		return paymentEntityList;
	}
	
	/*
	 * This method inserts the data into the payment table for a purchase done by
	 * the user.
	 * 
	 * @param PaymentEntity
	 * 
	 * @return PaymentEntity
	 */
	@Override
	public PaymentEntity insertPaymentDetail(PaymentEntity paymentEntity) throws Exception {
		LOGGER.info("Execution Started [insertPaymentDetail()] with paymentEntity:[" + paymentEntity + "]");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction et = entityManager.getTransaction();
		try {
			et.begin();
			PurchaseEntity purchaseEntity = entityManager.find(paymentEntity.getPurchaseEntity().getClass(),
					paymentEntity.getPurchaseEntity().getPurchaseId());
			paymentEntity.setPurchaseEntity(purchaseEntity);

			if (paymentEntity.getPaidId() == null || paymentEntity.getPaidId() == 0) {
				entityManager.persist(paymentEntity);
				et.commit();
			} else {
				entityManager.merge(paymentEntity);
				et.commit();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			et.rollback();
		} finally {
			entityManager.close();
		}

		LOGGER.info("Execution over [insertPaymentDetail()] with paymentEntity:[" + paymentEntity + "]");
		return paymentEntity;
	}

}
