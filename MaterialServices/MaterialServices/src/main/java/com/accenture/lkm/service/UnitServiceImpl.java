package com.accenture.lkm.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.UnitBean;
import com.accenture.lkm.dao.UnitDAO;
import com.accenture.lkm.entity.UnitEntity;

@Service
public class UnitServiceImpl implements UnitService{	
	
	@Autowired
	private UnitDAO unitDAO;
	
	public List<UnitBean> getUnits(String categoryId) {

		Collection<UnitEntity> collec = unitDAO.findByCategoryId(categoryId);

		List<UnitBean> unitList = new ArrayList<UnitBean>();
		for (UnitEntity unitEntity : collec) {
			UnitBean unit = new UnitBean();
			BeanUtils.copyProperties(unitEntity, unit);
			unit.setCategoryId(unitEntity.getMaterialCategoryEntity().getCategoryId());
			unitList.add(unit);
		}
		return unitList;		
	}

	@Override
	public List<UnitBean> getUnits() {

		Collection<UnitEntity> collec = unitDAO.findAll();
		List<UnitBean> unitList = new ArrayList<UnitBean>();
		for (UnitEntity unitEntity : collec) {
			UnitBean unit = new UnitBean();
			BeanUtils.copyProperties(unitEntity, unit);
			unit.setCategoryId(unitEntity.getMaterialCategoryEntity().getCategoryId());
			unitList.add(unit);
		}
		return unitList;		
	}
	

}
