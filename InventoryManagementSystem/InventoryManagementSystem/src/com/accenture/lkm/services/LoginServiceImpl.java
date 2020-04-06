package com.accenture.lkm.services;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.LoginBean;
import com.accenture.lkm.dao.LoginDAO;
import com.accenture.lkm.entity.LoginEntity;

@Service
public class LoginServiceImpl implements LoginService {

	private static Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);

	@Autowired
	private LoginDAO loginDAO;	
	
	/* 
	 * This method is used to fetch the login details data for a given username.
	 * @param username
	 * @return LoginBean
	 */
	@Override
	public LoginBean validateLogin(String username) {
		LOGGER.info("Execution Started [validateLogin()] with username:[" + username + "]");
		LoginBean loginBean = new LoginBean();
		LoginEntity loginEntity = loginDAO.findLoginDetails(username);
		if (loginEntity != null) {
			BeanUtils.copyProperties(loginEntity, loginBean);
		}
		LOGGER.info("Execution over [validateLogin()] with username:[" + username + "]");
		return loginBean;
	}

}
