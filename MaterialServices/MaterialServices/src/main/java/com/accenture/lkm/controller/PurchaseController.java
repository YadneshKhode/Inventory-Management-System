package com.accenture.lkm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.service.PurchaseService;



@RestController
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;

	@RequestMapping(value="/purchase/controller/addPurchase",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> insertPurchaseDetail(@RequestBody PurchaseBean purchaseBean) {
		 String purchaseId = purchaseService.insertPurchaseDetails(purchaseBean);
		 return new ResponseEntity<String>("Purchase details added successfully with id:"+purchaseId,HttpStatus.CREATED);
	}

}
