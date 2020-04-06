package com.accenture.lkm.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.accenture.lkm.business.bean.DateWisePaymentReportBean;
import com.accenture.lkm.business.bean.DateWisePurchaseReportBean;
import com.accenture.lkm.business.bean.DateWiseVendorPurchaseReportBean;
import com.accenture.lkm.business.bean.PaymentReportBeanDateWise;
import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.business.bean.VendorWisePuchaseBean;
import com.accenture.lkm.business.bean.VendorWisePuchaseReportBean;
import com.accenture.lkm.exceptions.MicroServiceException;
import com.accenture.lkm.services.ReportsService;
import com.accenture.lkm.web.client.MaterialCategoryConsumer;
import com.accenture.lkm.web.client.MaterialTypeConsumer;
import com.accenture.lkm.web.client.UnitServiceConsumer;
import com.accenture.lkm.web.client.VendorServiceConsumer;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * A controller class for handling the request coming from
 * DateWisePurchaseReport.jsp
 *
 */
@Controller
public class ReportsController {

	private static Logger LOGGER = Logger.getLogger(ReportsController.class);

	@Autowired
	private ReportsService reportsService;

	@Autowired
	private MaterialCategoryConsumer materialCategoryConsumer;

	@Autowired
	private UnitServiceConsumer unitServiceConsumer;

	@Autowired
	private MaterialTypeConsumer materialTypeConsumer;

	@Autowired
	private VendorServiceConsumer vendorServiceConsumer;
	
	List <PurchaseBean> purchaseIdList;

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method set the DateWisePurchaseReportBean into the model attribute and
	 * redirect to DateWisePurchaseReport.jsp.
	 * 
	 * @return
	 */
	@RequestMapping("loadDateWisePurchaseDetailPage.html")
	public ModelAndView showDateWisePurchaseDetailPage(HttpSession session) {
		LOGGER.info("Execution Started [ showDateWisePurchaseDetailPage()]");
		session.setAttribute("purchaseBeanList", new ArrayList<PurchaseBean>());	
		
		DateWisePurchaseReportBean dateWisePurchaseReportBean = new DateWisePurchaseReportBean();
		session.setAttribute("dateWisePurchaseReportBean", dateWisePurchaseReportBean);
		LOGGER.info("Execution over [ showDateWisePurchaseDetailPage()]");
		return new ModelAndView("DateWisePurchaseReport", "dateWisePurchaseReportBean", dateWisePurchaseReportBean);
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to fetch the purchase details between the from and to
	 * dates and redirect to the DateWisePurchaseReport.jsp to display the list
	 * 
	 * @param dateWisePurchaseReportBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDateWisePurchaseDetail.html", method = RequestMethod.POST)
	public ModelAndView getDateWisePurchaseDetails(
			@ModelAttribute("dateWisePurchaseReportBean") @Valid DateWisePurchaseReportBean dateWisePurchaseReportBean,
			BindingResult result, HttpSession session) throws Exception {
		LOGGER.info("Execution Started [getDateWisePurchaseDetails()] with dateWisePurchaseReportBean:["
				+ dateWisePurchaseReportBean + "]");
		ModelAndView view = new ModelAndView("DateWisePurchaseReport");

		if (!result.hasErrors()) {
			List<PurchaseBean> purchaseBeanList = reportsService.getDateWisePurchaseDetails(
					dateWisePurchaseReportBean.getFromDate(), dateWisePurchaseReportBean.getToDate());
			if (purchaseBeanList != null && purchaseBeanList.size() > 0) {
				Map<String, String> categoryMap = materialCategoryConsumer.getCategoryMap();
				Map<String, String> unitMap = unitServiceConsumer.getUnitMap();
				Map<String, String> typeMap = materialTypeConsumer.getCategoryTypeMap();
				for (PurchaseBean bean : purchaseBeanList) {
					bean.setMaterialCategoryName(categoryMap.get(bean.getMaterialCategoryId()));
					bean.setMaterialTypeName(typeMap.get(bean.getMaterialTypeId()));
					bean.setMaterialUnitName(unitMap.get(bean.getUnitId()));
				}
				view.addObject("purchaseBeanList", purchaseBeanList);
				session.setAttribute("purchaseBeanList", purchaseBeanList);
				session.setAttribute("dateWisePurchaseReportBean", dateWisePurchaseReportBean);
			} else {
				session.setAttribute("purchaseBeanList", new ArrayList<PurchaseBean>());
				view.addObject("message", "No records found in the given date range.");
			}
		}

		LOGGER.info("Execution over [getDateWisePurchaseDetails()] with dateWisePurchaseReportBean:["
				+ dateWisePurchaseReportBean + "]");
		return view;

	}
	
	/**
	 * This method will be called when user used pagination buttons to see next or previous records on DateWisePurchaseReport.jsp.
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDateWisePurchaseDetail.html", method = RequestMethod.GET)
	public ModelAndView returnDateWisePurchaseReport(HttpSession session) throws Exception {
		LOGGER.info("Execution Started [returnDateWisePurchaseReport()]");
		ModelAndView view = new ModelAndView("DateWisePurchaseReport", "dateWisePurchaseReportBean", session.getAttribute("dateWisePurchaseReportBean"));		
		
		LOGGER.info("Execution over [returnDateWisePurchaseReport()]");
		return view;

	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method set the VendorWisePuchaseReportBean into the model attribute and
	 * redirect to DateWisePurchaseReport.jsp.
	 * 
	 * @return
	 */
	@RequestMapping("loadVendorWisePurchaseReportPage.html")
	public ModelAndView showVendorWisePurchaseReportPage(HttpSession session) {
		LOGGER.info("Execution Started [ showVendorWisePurchaseReportPage()]");
		session.setAttribute("vendorWisePuchaseBeanList", new ArrayList<VendorWisePuchaseBean>());
		session.setAttribute("vendorBean", null);
		VendorWisePuchaseReportBean vendorWisePuchaseReportBean = new VendorWisePuchaseReportBean();
		session.setAttribute("vendorWisePuchaseReportBean", vendorWisePuchaseReportBean);
		LOGGER.info("Execution over [ showVendorWisePurchaseReportPage()]");
		return new ModelAndView("VendorWisePurchaseReport", "vendorWisePuchaseReportBean", vendorWisePuchaseReportBean);
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method returns the vendor list to be populated on the
	 * DateWisePurchaseReport.jsp getVendorBeanList method of VendorServiceConsumer
	 * is called to get the vendor list.
	 * 
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
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to fetch the purchase details for a given vendor and
	 * between the two given dates and redirect to the VendorWisePurchaseReport.jsp
	 * to display the list
	 * 
	 * @param vendorWisePuchaseReportBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getVendorWisePurchaseDetail.html", method = RequestMethod.POST)
	public ModelAndView getVendorWisePurchaseDetail(
			@ModelAttribute("vendorWisePuchaseReportBean") @Valid VendorWisePuchaseReportBean vendorWisePuchaseReportBean,
			BindingResult result, HttpSession session) throws Exception {
		LOGGER.info("Execution Started [getDateWisePurchaseDetails()] with vendorWisePuchaseReportBean:["
				+ vendorWisePuchaseReportBean + "]");
		ModelAndView view = new ModelAndView("VendorWisePurchaseReport");
		if (!result.hasErrors()) {
			List<VendorWisePuchaseBean> vendorWisePuchaseBeanList = reportsService.getVendorWisePurchaseDetails(
					vendorWisePuchaseReportBean.getFromDate(), vendorWisePuchaseReportBean.getToDate(),
					vendorWisePuchaseReportBean.getVendorName());
			if (vendorWisePuchaseBeanList != null && vendorWisePuchaseBeanList.size() > 0) {
				Map<String, String> categoryMap = materialCategoryConsumer.getCategoryMap();
				Map<String, String> unitMap = unitServiceConsumer.getUnitMap();
				Map<String, String> typeMap = materialTypeConsumer.getCategoryTypeMap();
				for (VendorWisePuchaseBean bean : vendorWisePuchaseBeanList) {
					bean.getPurchaseBean()
							.setMaterialCategoryName(categoryMap.get(bean.getPurchaseBean().getMaterialCategoryId()));
					bean.getPurchaseBean().setMaterialTypeName(typeMap.get(bean.getPurchaseBean().getMaterialTypeId()));
					bean.getPurchaseBean().setMaterialUnitName(unitMap.get(bean.getPurchaseBean().getUnitId()));
				}
				view.addObject("vendorWisePuchaseBeanList", vendorWisePuchaseBeanList);
				session.setAttribute("vendorWisePuchaseBeanList", vendorWisePuchaseBeanList);
				session.setAttribute("vendorWisePuchaseReportBean", vendorWisePuchaseReportBean);
			} else {
				session.setAttribute("vendorWisePuchaseBeanList", new ArrayList<VendorWisePuchaseBean>());
				view.addObject("message", "No records found for vendor name " +vendorWisePuchaseReportBean.getVendorName() + ".");
			}
			Map<String, VendorBean> vendorMap = vendorServiceConsumer.getVendorMap();

			view.addObject("vendorBean", vendorMap.get(vendorWisePuchaseReportBean.getVendorName()));
			session.setAttribute("vendorBean", vendorMap.get(vendorWisePuchaseReportBean.getVendorName()));
		}

		LOGGER.info("Execution over [getDateWisePurchaseDetails()] with vendorWisePuchaseReportBean:["
				+ vendorWisePuchaseReportBean + "]");
		return view;

	}
	
	/**
	 * This method will be called when user used pagination buttons to see next or previous records on VendorWisePurchaseReport.jsp.
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getVendorWisePurchaseDetail.html", method = RequestMethod.GET)
	public ModelAndView returnVendorWisePurchaseReport(HttpSession session) throws Exception {
		LOGGER.info("Execution Started [returnVendorWisePurchaseReport()]");
		ModelAndView view = new ModelAndView("VendorWisePurchaseReport", "vendorWisePuchaseReportBean", session.getAttribute("vendorWisePuchaseReportBean"));		
		LOGGER.info("Execution over [returnVendorWisePurchaseReport()]");
		return view;

	}
	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method set the dateWisePaymentReportBean into the model attribute and
	 * redirect to VendorPaymentHistoryReport.jsp.
	 * 
	 * @return
	 */
	@RequestMapping("loadDateWisePaymentDetailPage.html")
	public ModelAndView showVendorPmtHistReportPage(HttpSession session) {
		LOGGER.info("Execution Started [ showDateWisePaymenteDetailPage()]");
		session.setAttribute("paymentBeanList", new ArrayList<PaymentReportBeanDateWise>());
		DateWisePaymentReportBean dateWisePaymentReportBean = new DateWisePaymentReportBean();
		LOGGER.info("Execution over [ showDateWisePaymenteDetailPage()]");
		session.setAttribute("dateWisePaymentReportBean", dateWisePaymentReportBean);
		return new ModelAndView("VendorPaymentHistoryReport", "dateWisePaymentReportBean", dateWisePaymentReportBean);
	}	
	
	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to fetch the payment details between the from and to
	 * dates and redirect to the VendorPaymentHistoryReport.jsp to display the list
	 * 
	 * @param dateWisePurchaseReportBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDateWisePaymentDetail.html", method = RequestMethod.POST)
	public ModelAndView getVendorPmtHistoryDetails(
			@ModelAttribute("dateWisePaymentReportBean") @Valid DateWisePaymentReportBean dateWisePaymentReportBean,
			BindingResult result, ModelMap map, HttpSession session) throws Exception {
		LOGGER.info("Execution Started [dateWisePaymentReportBean()] with dateWisePaymentReportBean:["
				+ dateWisePaymentReportBean + "]");
		ModelAndView view = new ModelAndView("VendorPaymentHistoryReport");
		map.addAttribute(dateWisePaymentReportBean);
		if (!result.hasErrors()) {
			List<PaymentReportBeanDateWise> paymentBeanList = reportsService.getVendorPmtHistoryDetails(
					dateWisePaymentReportBean.getFromDate(), dateWisePaymentReportBean.getToDate(),
					dateWisePaymentReportBean.getVendorName(), dateWisePaymentReportBean.getStatus());
			if (paymentBeanList.size() != 0 || !(paymentBeanList.isEmpty())) {
				view.addObject("paymentBeanList", paymentBeanList);
				session.setAttribute("paymentBeanList", paymentBeanList);
				session.setAttribute("dateWisePaymentReportBean", dateWisePaymentReportBean);
			}

			else {
				view.addObject("message", "No records found for the given criteria.");
				session.setAttribute("paymentBeanList", new ArrayList<PaymentReportBeanDateWise>());
			}
		}

		LOGGER.info("Execution over [dateWisePaymentReportBean()] with dateWisePaymentReportBean:["
				+ dateWisePaymentReportBean + "]");
		return view;

	}
	
	/**
	 * This method will be called when user used pagination buttons to see next or previous records on VendorPaymentHistoryReport.jsp.
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDateWisePaymentDetail.html", method = RequestMethod.GET)
	public ModelAndView returnVendorPmtHistPage(HttpSession session) throws Exception {
		LOGGER.info("Execution Started [returnPaymentReport()]");
		ModelAndView view = new ModelAndView("VendorPaymentHistoryReport", "dateWisePaymentReportBean", session.getAttribute("dateWisePaymentReportBean"));		
		LOGGER.info("Execution over [returnPaymentReport()]");
		return view;

	}
	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method set the dateWiseVendorPurchaseReportBean into the model attribute and
	 * redirect to VendorPurchaseHistoryReport.jsp.
	 * 
	 * @return
	 */
	@RequestMapping("loadDateWiseVendorPurchaseReport.html")
	public ModelAndView showVendorPurchaseHistReportPage(HttpSession session) {
		LOGGER.info("Execution Started [ showDateWiseVendorPurchaseDetailPage()]");
		session.setAttribute("paymentBeanList", new ArrayList<PaymentReportBeanDateWise>());
		session.setAttribute("purchaseIdList",null);
		DateWiseVendorPurchaseReportBean dateWiseVendorPurchaseReportBean = new DateWiseVendorPurchaseReportBean();
		LOGGER.info("Execution over [ showDateWiseVendorPurchaseDetailPage()]");
		session.setAttribute("dateWiseVendorPurchaseReportBean", dateWiseVendorPurchaseReportBean);
		return new ModelAndView("VendorPurchaseHistoryReport", "dateWiseVendorPurchaseReportBean", dateWiseVendorPurchaseReportBean);
	}	
	
		
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method returns purchaseIdList based on vendorname to VendorPurchaseHistoryReport.jsp.
	 *  @return ModelAndView
	 */
	@RequestMapping(value = "getPurchaseIdList.html", method = RequestMethod.POST)
	public  ModelAndView generatePurchaseIdList(@ModelAttribute("dateWiseVendorPurchaseReportBean") DateWiseVendorPurchaseReportBean dateWiseVendorPurchaseReportBean,ModelMap map, HttpSession session) {
		LOGGER.info("Execution Started [generatePurchaseIdList()]");
		purchaseIdList=reportsService.getPurchaseIdList(dateWiseVendorPurchaseReportBean.getVendorName());
		ModelAndView view =new ModelAndView("VendorPurchaseHistoryReport");
		view.addObject("purchaseIdList",purchaseIdList);
		session.setAttribute("purchaseIdList",purchaseIdList);
		LOGGER.info("Execution over [generatePurchaseIdList()]");
		return view;
	}
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method returns the MaterialDetails into the model attribute and
	 * redirect to VendorPurchaseHistoryReport.jsp.
	 * 
	 * @return
	 * @throws MicroServiceException 
	 */
	@RequestMapping(value="loadMaterialDetails.html", method = RequestMethod.POST)
	public ModelAndView showMaterialDetails(@ModelAttribute("dateWiseVendorPurchaseReportBean") DateWiseVendorPurchaseReportBean dateWiseVendorPurchaseReportBean,ModelMap map, HttpSession session) throws MicroServiceException {
		LOGGER.info("Execution Started [ showDateWiseVendorPurchaseDetailPage()]");
		List<PurchaseBean>materialDetails=reportsService.getMaterialDetails(dateWiseVendorPurchaseReportBean.getTransactionId());
		ModelAndView view =new ModelAndView("VendorPurchaseHistoryReport");
		if (materialDetails != null && materialDetails.size() > 0) {
			Map<String, String> categoryMap = materialCategoryConsumer.getCategoryMap();
			Map<String, String> typeMap = materialTypeConsumer.getCategoryTypeMap();
			
			for (PurchaseBean bean : materialDetails) {
				dateWiseVendorPurchaseReportBean.setMaterialCategoryName(categoryMap.get(bean.getMaterialCategoryId()));
				dateWiseVendorPurchaseReportBean.setMaterialTypeName(typeMap.get(bean.getMaterialTypeId()));
				dateWiseVendorPurchaseReportBean.setBrandName(bean.getBrandName());
				dateWiseVendorPurchaseReportBean.setQuantity(bean.getQuantity());
				
			}
			view.addObject("dateWiseVendorPurchaseReportBean", dateWiseVendorPurchaseReportBean);
			session.setAttribute("dateWiseVendorPurchaseReportBean", dateWiseVendorPurchaseReportBean);
		} else {
			view.addObject("message", "No records found for the given criteria.");
		}
		map.addAttribute("dateWiseVendorPurchaseReportBean",dateWiseVendorPurchaseReportBean);
		map.addAttribute("purchaseIdList",purchaseIdList);
		LOGGER.info("Execution over [ showDateWiseVendorPurchaseDetailPage()]");
		return view;
	}
	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to fetch the payment details between the from and to
	 * dates and redirect to the VendorPurchaseHistoryReport.jsp to display the list
	 * 
	 * @param dateWiseVendorPurchaseReportBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDateWiseVendorPurchaseReport.html", method = RequestMethod.POST)
	public ModelAndView getVendorPurchaseHistDetails(
			@ModelAttribute("dateWiseVendorPurchaseReportBean") @Valid DateWiseVendorPurchaseReportBean dateWiseVendorPurchaseReportBean,
			BindingResult result,ModelMap map, HttpSession session) throws Exception {
		LOGGER.info("Execution Started [getDateWisePurchaseDetails()] with dateWiseVendorPurchaseReportBean:["
				+ dateWiseVendorPurchaseReportBean + "]");
		ModelAndView view = new ModelAndView("VendorPurchaseHistoryReport");
		session.setAttribute("dateWiseVendorPurchaseReportBean", dateWiseVendorPurchaseReportBean);
		map.addAttribute(dateWiseVendorPurchaseReportBean);
		map.addAttribute("purchaseIdList",purchaseIdList);
		if (!result.hasErrors()) {
			System.out.println("Inside controller"+dateWiseVendorPurchaseReportBean);
			List<PaymentReportBeanDateWise> paymentBeanList = reportsService.getTxIdWisePaymentDetails(
					dateWiseVendorPurchaseReportBean.getFromDate(), dateWiseVendorPurchaseReportBean.getToDate(),dateWiseVendorPurchaseReportBean.getVendorName(),dateWiseVendorPurchaseReportBean.getTransactionId());
			if(paymentBeanList.size()!=0)
			   {
				view.addObject("paymentBeanList", paymentBeanList);
				session.setAttribute("paymentBeanList", paymentBeanList);
			   }
			 else
			   {
				   view.addObject("message", "No records found for the given criteria."); 
				   session.setAttribute("paymentBeanList", new ArrayList<PaymentReportBeanDateWise>());
			   }
		}		
		LOGGER.info("Execution over [getDateWisePurchaseDetails()] with dateWiseVendorPurchaseReportBean:["
				+ dateWiseVendorPurchaseReportBean + "]");
		return view;

	}
	
	/**
	 * This method will be called when user used pagination buttons to see next or previous records on VendorPurchaseHistoryReport.jsp.
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getDateWiseVendorPurchaseReport.html", method = RequestMethod.GET)
	public ModelAndView returnVendorPurchaseHistReportPage(HttpSession session) throws Exception {
		LOGGER.info("Execution Started [returnPaymentReport()]");
		ModelAndView view = new ModelAndView("VendorPurchaseHistoryReport", "dateWiseVendorPurchaseReportBean", session.getAttribute("dateWiseVendorPurchaseReportBean"));		
		LOGGER.info("Execution over [returnPaymentReport()]");
		return view;

	}

}
