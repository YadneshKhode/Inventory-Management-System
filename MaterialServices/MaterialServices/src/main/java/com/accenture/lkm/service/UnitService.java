package com.accenture.lkm.service;

import java.util.List;

import com.accenture.lkm.business.bean.UnitBean;

public interface UnitService {
	List<UnitBean> getUnits(String categoryId);
	List<UnitBean> getUnits();
}
