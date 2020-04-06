package com.accenture.lkm.dao;

import com.accenture.lkm.entity.LoginEntity;

public interface LoginDAO {
	
	public LoginEntity findLoginDetails(String username);

}
