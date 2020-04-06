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

import com.accenture.lkm.business.bean.UnitBean;
import com.accenture.lkm.service.UnitService;


@RestController
public class UnitController {
	
	@Autowired
	private UnitService unitService;
	
	@RequestMapping(value="unit/controller/getUnitDetails",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<UnitBean>> getUnitDetails(){
		Collection <UnitBean> listUnit = unitService.getUnits();
		return new ResponseEntity<Collection<UnitBean>>(listUnit, HttpStatus.OK);
	}
	
	@RequestMapping(value="unit/controller/getUnitsByCategoryId/{categoryId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<UnitBean>> getUnitsByCategoryId(@PathVariable("categoryId") String categoryId){
		Collection <UnitBean> listUnit = unitService.getUnits(categoryId);
		return new ResponseEntity<Collection<UnitBean>>(listUnit, HttpStatus.OK);
	}	

}
