package com.accenture.lkm.web.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.lkm.business.bean.LoginBean;
import com.accenture.lkm.services.LoginService;

/**
 * <br/>
 *	CLASS DESCRIPTION:  <br/>
 *	A controller class for handling the login related request from the User Interface. <br/>
 *
 */

@Controller
public class LoginController {
	
	private static Logger LOGGER =  Logger.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * <br/>
	 * METHOD DESCRIPTION:<br/>
	 * This method set the LoginBean into the model attribute and loads the login page with user name and password fields.
	 * @return 
	 */
	@RequestMapping(value = "login.html", method = RequestMethod.GET)
	public ModelAndView login() {
		LOGGER.info("Execution Started [login()]");	
		LoginBean loginBean = new LoginBean();
		ModelAndView modelAndView = new ModelAndView("login", "loginBean", loginBean);
		LOGGER.info("Execution over [login()]");	
		return modelAndView;
	}
	
	/**
	 * <br/>
	 * METHOD DESCRIPTION:<br/>
	 * This method validates the user name and password entered by the user.
	 * If the username and password matches then it redirect the user to the Welcome screen.
	 * If validation fails, then the error message will get display on the same login screen.  
	 * @param loginBean
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "validateLogin.html", method = RequestMethod.POST)
	public ModelAndView validateLogin(@ModelAttribute @Valid LoginBean loginBean, BindingResult result) throws Exception {		
		LOGGER.info("Execution Started [validateLogin()]");		

		ModelAndView modelAndView = new ModelAndView();
		String pageName = "";
		if (result.hasErrors()) {
			pageName = "login";
		} else {
			
			LoginBean login = loginService.validateLogin(loginBean.getUsername());			
			if(login!= null && loginBean.getPassword().equals(login.getPassword())){
				pageName = "Welcome";
			}
			else {
				modelAndView.addObject("message", "Either Username or Password is incorrect. Please enter credentials again.");
				pageName = "login";
			}
		}
		modelAndView.setViewName(pageName);	
		LOGGER.info("Execution over [validateLogin()]");
		return modelAndView;
	}

}
