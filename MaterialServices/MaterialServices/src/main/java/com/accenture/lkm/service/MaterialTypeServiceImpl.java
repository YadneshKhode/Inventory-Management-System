package com.accenture.lkm.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.MaterialTypeBean;
import com.accenture.lkm.dao.MaterialTypeDAO;
import com.accenture.lkm.entity.MaterialTypeEntity;
@Service
public class MaterialTypeServiceImpl implements MaterialTypeService{
	
	@Autowired
	private MaterialTypeDAO materialTypeDAO;

	@Override
	public List<MaterialTypeBean> getMaterialTypes(String categoryId) {

		Collection<MaterialTypeEntity> collec = materialTypeDAO.findByCategoryId(categoryId);

		List<MaterialTypeBean> materialTypeList = new ArrayList<MaterialTypeBean>();
		for (MaterialTypeEntity materialTypeEntity : collec) {
			MaterialTypeBean materialType = new MaterialTypeBean();
			BeanUtils.copyProperties(materialTypeEntity, materialType);
			materialType.setCategoryId(materialTypeEntity.getMaterialCategoryEntity().getCategoryId());
			materialTypeList.add(materialType);
		}
		return materialTypeList;		
	}

	@Override
	public List<MaterialTypeBean> getMaterialTypes() {
		Collection<MaterialTypeEntity> collec = materialTypeDAO.findAll();

		List<MaterialTypeBean> materialTypeList = new ArrayList<MaterialTypeBean>();
		for (MaterialTypeEntity materialTypeEntity : collec) {
			MaterialTypeBean materialType = new MaterialTypeBean();
			BeanUtils.copyProperties(materialTypeEntity, materialType);
			materialType.setCategoryId(materialTypeEntity.getMaterialCategoryEntity().getCategoryId());
			materialTypeList.add(materialType);
		}
		return materialTypeList;	
	}

	
}
