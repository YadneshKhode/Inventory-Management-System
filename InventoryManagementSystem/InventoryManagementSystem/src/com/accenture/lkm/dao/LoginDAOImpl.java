package com.accenture.lkm.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.lkm.entity.LoginEntity;

/**
 *  <br/>
 *	CLASS DESCRIPTION:  <br/>
 *	Implementation class for LoginDAO to perform the select operation on logindetail table <br/>
 *
 */
@Repository
public class LoginDAOImpl implements LoginDAO {

	private static Logger LOGGER = Logger.getLogger(PaymentDAOImpl.class);

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	/* 
	 * This method fetch the data from logindetail table for a given username.
	 * @param username
	 * @return LoginEntity
	 */
	@Override
	public LoginEntity findLoginDetails(String username) {

		LOGGER.info("Execution Started [findLoginDetails()] with username:[" + username + "]");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		LoginEntity loginDetailEntity = null;
		try {
			loginDetailEntity = entityManager.find(LoginEntity.class, username);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		LOGGER.info("Execution over [findLoginDetails()] with username:[" + username + "]");
		return loginDetailEntity;

	}

}
