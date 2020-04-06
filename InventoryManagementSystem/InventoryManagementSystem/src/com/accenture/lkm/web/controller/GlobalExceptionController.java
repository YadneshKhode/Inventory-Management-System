package com.accenture.lkm.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.lkm.exceptions.MicroServiceException;

@ControllerAdvice
public class GlobalExceptionController {
	
	@ExceptionHandler(MicroServiceException.class)
	public ModelAndView handleAllException(Exception ex) {

		ModelAndView model = new ModelAndView("error");
		model.addObject("errMsg", ex.getMessage());

		return model;

	}

}
