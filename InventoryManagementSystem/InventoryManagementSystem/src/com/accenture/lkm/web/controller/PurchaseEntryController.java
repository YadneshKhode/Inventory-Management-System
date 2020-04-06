package com.accenture.lkm.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.lkm.business.bean.MaterialCategoryBean;
import com.accenture.lkm.business.bean.MaterialTypeBean;
import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.business.bean.UnitBean;
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.exceptions.MicroServiceException;
import com.accenture.lkm.services.PurchaseService;
import com.accenture.lkm.web.client.MaterialCategoryConsumer;
import com.accenture.lkm.web.client.MaterialTypeConsumer;
import com.accenture.lkm.web.client.UnitServiceConsumer;
import com.accenture.lkm.web.client.VendorServiceConsumer;

/**
 * <br/>
 *	CLASS DESCRIPTION:  <br/>
 *	A controller class for receiving and handling all material purchase related transactions from the 
 *  User Interface. <br/>
 *
 */
@Controller
@SessionAttributes({"purchaseBean"})
public class PurchaseEntryController {
	
	private static Logger LOGGER =  Logger.getLogger(PurchaseEntryController.class);
		
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private VendorServiceConsumer vendorServiceConsumer;
	
	@Autowired
	private MaterialCategoryConsumer materialCategoryConsumer;
	
	@Autowired
	private UnitServiceConsumer unitServiceConsumer;
	
	@Autowired
	private MaterialTypeConsumer materialTypeConsumer;
	
	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method set the PurchaseBean into the model attribute and redirect to PurchaseEntry.jsp.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "purchaseEntry.html", method = RequestMethod.GET)
	public ModelAndView purchaseEntry() throws Exception {		
		LOGGER.info("Execution Started [purchase()]");		
		PurchaseBean purchaseBean = new PurchaseBean();
		ModelAndView modelAndView = new ModelAndView("PurchaseEntry", "purchaseBean", purchaseBean);		
		LOGGER.info("Execution over [purchase()]");
		return modelAndView;
	}
	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method returns the vendor list to be populated on the PurchasEntry.jsp
	 * getVendorBeanList method of VendorServiceConsumer is called to get the vendor list.
	 * @return vendorList - List of vendor values
	 * @throws MicroServiceException 
	 */
	@ModelAttribute("vendorList")
	public List<VendorBean> generateVendorList() throws MicroServiceException {		
		LOGGER.info("Execution Started [ generateVendorList()]");
		List<VendorBean> vendorList = vendorServiceConsumer.getVendorBeanList();
		LOGGER.info("Execution over [ generateVendorList()]");
		return vendorList;
	}
	
	
	/**
	 *  METHOD DESCRIPTION: <br/>
	 *  This method returns the material unit and type list to be populated in PurchaseEntry.jsp on the basis of selected material category by user.
	 *  hitGetUnitsByCategoryId method of UnitServiceConsumer class is being called to get the list of material unit.
	 *  hitGetTypesBasedOnCategoryId method of MaterialTypeConsumer class is being called to get the list of material type. 
	 * @param purchaseBean
	 * @return
	 * @throws MicroServiceException 
	 */
	@RequestMapping(value = "getUnitAndTypeList.html", method = RequestMethod.POST)
	public  ModelAndView generateUnitAndTypeList(@ModelAttribute("purchaseBean") PurchaseBean purchaseBean, HttpSession session) throws MicroServiceException {
		LOGGER.info("Execution Started [generateUnitAndTypeList()]");
		List<UnitBean> unitList = unitServiceConsumer.hitGetUnitsByCategoryId(purchaseBean.getMaterialCategoryId());
		List<MaterialTypeBean> materialTypeList = materialTypeConsumer.hitGetTypesBasedOnCategoryId(purchaseBean.getMaterialCategoryId());	
		ModelAndView view =new ModelAndView("PurchaseEntry");
		view.addObject("unitList",unitList);
		session.setAttribute("unitList",unitList);
		view.addObject("materialTypeList",materialTypeList);	
		session.setAttribute("materialTypeList",materialTypeList);
		LOGGER.info("Execution over [generateUnitAndTypeList()]");
		return view;
	}

	/**	 
	 * METHOD DESCRIPTION: <br/>
	 * This method returns the material category list to be populated on the PurchasEntry.jsp
	 * getMaterialCategoryBeanList method of MaterialCategoryConsumer is called to get the material category list.
	 * @return
	 * @throws MicroServiceException 
	 */
	@ModelAttribute("categoryList")
	public List<MaterialCategoryBean> generateCategoryList() throws MicroServiceException {		
		LOGGER.info("Execution Started [generateCategoryList()]");
		List<MaterialCategoryBean> materialCategoryList = materialCategoryConsumer.getMaterialCategoryBeanList();		
		LOGGER.info("Execution over [generateCategoryList()]");
		return materialCategoryList;
	}
	
	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to insert purchase details filled on PurchaseEntry.jsp in to the purchase and payment table
	 * and redirect to the PurchaseSuccess.jsp
	 * @param purchaseBean
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addPurchaseDetail.html", method = RequestMethod.POST)
	public ModelAndView addPurchaseDetail(@ModelAttribute("purchaseBean") @Valid PurchaseBean purchaseBean,
			BindingResult result, ModelMap map, HttpSession
			 session) throws Exception {
		LOGGER.info("Execution Started [addPurchaseDetail()]");
		map.addAttribute(purchaseBean);
		ModelAndView modelAndView = new ModelAndView();
		if (!result.hasErrors()) {
			purchaseBean = purchaseService.addPurchaseDetails(purchaseBean);
			modelAndView.addObject("message", purchaseBean.getTransactionId());
			modelAndView.setViewName("PurchaseSuccess");
		} else {
			modelAndView.setViewName("PurchaseEntry");
		}		
		
		modelAndView.addObject("unitList",session.getAttribute("unitList"));
		modelAndView.addObject("materialTypeList", session.getAttribute("materialTypeList"));
		LOGGER.info("Execution over [addPurchaseDetail()]");
		return modelAndView;

	}
	
	

}
