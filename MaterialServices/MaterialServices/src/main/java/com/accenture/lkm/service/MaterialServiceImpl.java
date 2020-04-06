package com.accenture.lkm.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.dao.MaterialCategoryDAO;
import com.accenture.lkm.entity.MaterialCategoryEntity;


@Service
public class MaterialServiceImpl implements MaterialService{
	@Autowired
	private MaterialCategoryDAO materialCategoryDAO;
	
	public List<MaterialCategoryBean> getMaterialCategories() {

		Collection<MaterialCategoryEntity> collec = materialCategoryDAO.findAll();

		List<MaterialCategoryBean> materialCategoryList = new ArrayList<MaterialCategoryBean>();
		for (MaterialCategoryEntity materialCategoryEntity : collec) {
			MaterialCategoryBean materialCategory = new MaterialCategoryBean();
			BeanUtils.copyProperties(materialCategoryEntity, materialCategory);
			materialCategoryList.add(materialCategory);
		}
		return materialCategoryList;		
	}
	
	public MaterialCategoryBean getMaterialCategoryById(String categoryId) {

		MaterialCategoryEntity materialCategoryEntity = materialCategoryDAO.findOne(categoryId);
		MaterialCategoryBean materialCategoryBean = new MaterialCategoryBean();
		BeanUtils.copyProperties(materialCategoryEntity, materialCategoryBean);		
		return materialCategoryBean;		
	}	
}
