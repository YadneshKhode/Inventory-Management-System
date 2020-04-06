package com.accenture.lkm.services;

import com.accenture.lkm.business.bean.LoginBean;

public interface LoginService {
	
	public LoginBean validateLogin(String username);

}
