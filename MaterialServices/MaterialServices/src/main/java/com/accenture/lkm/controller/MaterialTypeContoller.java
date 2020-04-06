package com.accenture.lkm.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.business.bean.MaterialTypeBean;
import com.accenture.lkm.service.MaterialTypeService;

@RestController
public class MaterialTypeContoller {
	
	@Autowired
	private MaterialTypeService materialTypeService;
	
	@RequestMapping(value="type/controller/getTypeDetails",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<MaterialTypeBean>> getTypeDetails(){
		Collection <MaterialTypeBean> listUnit = materialTypeService.getMaterialTypes();
		return new ResponseEntity<Collection<MaterialTypeBean>>(listUnit, HttpStatus.OK);
	}
	
	@RequestMapping(value="type/controller/getTypeDetailsByCategoryId/{categoryId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<MaterialTypeBean>> getTypesBasedOnCategoryId(@PathVariable("categoryId") String categoryId){
		Collection <MaterialTypeBean> listUnit = materialTypeService.getMaterialTypes(categoryId);
		return new ResponseEntity<Collection<MaterialTypeBean>>(listUnit, HttpStatus.OK);
	}

}
