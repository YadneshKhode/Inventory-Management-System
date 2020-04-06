package com.accenture.lkm.service;

import java.util.List;

import com.accenture.lkm.business.bean.MaterialCategoryBean;



public interface MaterialService {
	List<MaterialCategoryBean> getMaterialCategories();
	public MaterialCategoryBean getMaterialCategoryById(String categoryId);
}
