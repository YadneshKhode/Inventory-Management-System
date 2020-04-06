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

import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.service.MaterialService;


@RestController
public class MaterialController {
	@Autowired
	private MaterialService materialService;
	
	@RequestMapping(value="material/controller/getMaterialCategories",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<MaterialCategoryBean>> getMaterialCategories(){
		Collection <MaterialCategoryBean> listMaterialCategory = materialService.getMaterialCategories();
		return new ResponseEntity<Collection<MaterialCategoryBean>>(listMaterialCategory, HttpStatus.OK);
	}
	
	@RequestMapping(value="material/controller/getMaterialCategoryById/{categoryId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MaterialCategoryBean> getMaterialCategoryById(@PathVariable("categoryId") String categoryId){
		MaterialCategoryBean materialCategory = materialService.getMaterialCategoryById(categoryId);
		return new ResponseEntity<MaterialCategoryBean>(materialCategory, HttpStatus.OK);
	}
}
