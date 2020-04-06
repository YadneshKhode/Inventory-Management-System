package com.accenture.lkm.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * <br/>
 *	CLASS DESCRIPTION:  <br/>
 *	A controller class for redirecting to the Welcome.jsp screen when user click Home in the menu bar.<br/>
 *
 */
@Controller
public class HomeController {

	private static Logger LOGGER =  Logger.getLogger(HomeController.class);
	
	/**
	 * <br/>
	 * METHOD DESCRIPTION:<br/>
	 * This method redirect to Welcome.jsp when a user click on Home in the menu bar.
	 * @return 
	 */
	@RequestMapping(value = "welcome.html", method = RequestMethod.GET)
	public ModelAndView showHomePage() {
		LOGGER.info("Execution Started [login()]");			
		ModelAndView modelAndView = new ModelAndView("Welcome");
		LOGGER.info("Execution over [login()]");	
		return modelAndView;
	}
}
