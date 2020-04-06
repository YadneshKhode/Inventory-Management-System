package com.accenture.lkm.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.lkm.business.bean.PaymentBean;
import com.accenture.lkm.business.bean.PaymentCollectionBean;
import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.exceptions.MicroServiceException;
import com.accenture.lkm.services.PaymentTrackerService;
import com.accenture.lkm.validator.PaymentCollectionBeanValidator;
import com.accenture.lkm.web.client.MaterialTypeConsumer;
import com.accenture.lkm.web.client.VendorServiceConsumer;

/**
 * <br/>
 * CLASS DESCRIPTION: <br/>
 * A controller class for receiving and handling all payment related
 * transactions from the User Interface. <br/>
 *
 */
@Controller
@SessionAttributes("beanForPaymentTracking")
public class PaymentTrackerController {

	private static Logger LOGGER = Logger.getLogger(PaymentTrackerController.class);

	@Autowired
	private PaymentTrackerService paymentTrackerService;

	@Autowired
	private VendorServiceConsumer vendorServiceConsumer;

	@Autowired
	private MaterialTypeConsumer materialTypeConsumer;

	private List<PurchaseBean> purchaseList;
	private List<PaymentBean> paymentList = new ArrayList<PaymentBean>();

	@Autowired
	@Qualifier("paymentCollectionBeanValidator")
	private PaymentCollectionBeanValidator paymentCollectionBeanValidator;

	@InitBinder("paymentCollectionBean")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(paymentCollectionBeanValidator);
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method save or update the selected payment rows into the Payment table.
	 * If paid id is available in the PaymentBean then updation will be done else
	 * save will be done. Also, if the balance amount after payment becomes zero
	 * then the status will be updated to Paid in Purchase table. And if balance
	 * amount becomes negative after doing the payment, then the status will be set
	 * to Overpaid in Purchase table. After save or update it is redirected to
	 * PaymentTracker.jsp with a message saying Payment done successfully!
	 * 
	 * @param paymentCollectionBean
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveRow.html", method = RequestMethod.POST)
	public ModelAndView savePaymentRow(
			@ModelAttribute("paymentCollectionBean") @Validated PaymentCollectionBean paymentCollectionBean,
			BindingResult bindingResult, HttpSession session) throws Exception {
		LOGGER.info("Execution Started [savePaymentRow()]");
		PurchaseBean purchaseBean = (PurchaseBean) session.getAttribute("beanForPaymentTracking");
		ModelAndView view = new ModelAndView("PaymentTracker");
		List<PaymentBean> paymentList = paymentCollectionBean.getPaymentBeans();
		PurchaseBean newBean = (PurchaseBean) session.getAttribute("beanForPaymentTracking");
		double balance = newBean.getPurchaseAmount() - calculateTotalAmtPerPurchaseId(paymentList);
		Iterator<PaymentBean> iterator = paymentList.iterator();
		if (!bindingResult.hasErrors()) {
			while (iterator.hasNext()) {
				PaymentBean bean = iterator.next();
				if (bean.getChecked() != null && bean.getChecked().equals("Y")) {
					balance = balance - bean.getPaidAmount();
					bean.setBalance(balance);
					bean.setPurchaseId(purchaseBean.getPurchaseId());
					bean = paymentTrackerService.savePaymentDetails(bean);
					bean.setEnabled("N");
					bean.setChecked("");
				}
			}

			// for updating the status in Purchase table if the user paid completely or more
			// than the purchased amount
			updatingStausInDB(newBean, balance);

			newBean.setBalance(balance);
			session.setAttribute("beanForPaymentTracking", newBean);
			view.addObject("beanForPaymentTracking", newBean);
			view.addObject("message", "Payment done successfully!");
		}else {
			while (iterator.hasNext()) {
				PaymentBean bean = iterator.next();
				if(bean.getChecked().equals("Y")){
					bean.setChecked("N");
					bean.setEnabled("Y");
				}
			}
		}
		view.addObject("paymentCollectionBean", paymentCollectionBean);
		view.addObject("purchaseList", purchaseList);
		
		
		LOGGER.info("Execution over [savePaymentRow()]");
		return view;
	}

	/**
	 * This method is updating the status in Purchase table.
	 * 
	 * @param newBean
	 * @param balance
	 * @throws Exception
	 */
	private void updatingStausInDB(PurchaseBean newBean, double balance) throws Exception {
		if (balance == 0) {
			newBean.setStatus("Paid");
			paymentTrackerService.updatePurchaseStatus(newBean);
		} else if (balance < 0) {
			newBean.setStatus("Overpaid");
			paymentTrackerService.updatePurchaseStatus(newBean);
		}
	}

	/**
	 * This method is calculating the total amount paid for a purchase id from the
	 * given list of Payment details.
	 * 
	 * @param paymentList
	 * @return
	 */
	private double calculateTotalAmtPerPurchaseId(List<PaymentBean> paymentList) {
		double totalAmtPaid = 0.0;
		for (PaymentBean bean : paymentList) {
			if (bean.getPaidId() != null && !bean.getChecked().equals("Y")) {
				totalAmtPaid = totalAmtPaid + bean.getPaidAmount();
			}
		}
		return totalAmtPaid;
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method set the PurchaseBean into the model attribute and redirect to
	 * PaymentTracker.jsp.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "paymentTracker.html", method = RequestMethod.GET)
	public ModelAndView paymentTracker() throws Exception {
		LOGGER.info("Execution Started [paymentTracker()]");
		PurchaseBean purchaseBean = new PurchaseBean();

		ModelAndView modelAndView = new ModelAndView("PaymentTracker", "beanForPaymentTracking", purchaseBean);

		LOGGER.info("Execution over [paymentTracker()]");
		return modelAndView;
	}

	/**
	 * This method returns the vendor list to be populated on the PurchasEntry.jsp
	 * getVendorBeanList method of VendorServiceConsumer is called to get the vendor
	 * list.
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
	 * This method get the Purchase details for the selected vendor and redirect to
	 * PaymentTracker.jsp.
	 * 
	 * @param purchaseBean
	 * @param map
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "purchaseDetail.html", method = RequestMethod.POST)
	public ModelAndView getPurchaseDetails(@ModelAttribute("beanForPaymentTracking") PurchaseBean purchaseBean,
			ModelMap map, HttpSession session) throws Exception {
		LOGGER.info("Execution Started [getPurchaseDetails()] with purchaseBean:[" + purchaseBean + "]");
		PurchaseBean newPurchaseBean = new PurchaseBean();
		newPurchaseBean.setVendorName(purchaseBean.getVendorName());
		purchaseList = paymentTrackerService.getPurchaseDetails(purchaseBean.getVendorName());
		Map<String, String> typeMap = materialTypeConsumer.getCategoryTypeMap();
		for (PurchaseBean bean : purchaseList) {
			bean.setMaterialTypeName(typeMap.get(bean.getMaterialTypeId()));
		}

		ModelAndView view = new ModelAndView("PaymentTracker", "beanForPaymentTracking", newPurchaseBean);
		view.addObject("purchaseList", purchaseList);
		LOGGER.info("Execution over [getPurchaseDetails()] with purchaseBean:[" + purchaseBean + "]");
		return view;
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method get the Payment details for the selected vendor and transaction
	 * id and redirect to PaymentTracker.jsp.
	 * 
	 * @param purchaseBean
	 * @param map
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getPaymentDetails.html", method = RequestMethod.POST)
	public ModelAndView getPaymentDetails(@ModelAttribute("beanForPaymentTracking") PurchaseBean purchaseBean,
			ModelMap map, HttpSession session) throws Exception {
		LOGGER.info("Execution Started [getPurchaseDetails()] with purchaseBean:[" + purchaseBean + "]");
		PaymentCollectionBean paymentCollectionBean = new PaymentCollectionBean();
		ModelAndView view = new ModelAndView("PaymentTracker");
		Map<String, String> typeMap = materialTypeConsumer.getCategoryTypeMap();
		purchaseList = paymentTrackerService.getPurchaseDetails(purchaseBean.getVendorName());
		paymentList = paymentTrackerService.getPaymentDetails(purchaseBean.getPurchaseId());
		double totalAmtPaid = 0.0;
		for (PaymentBean bean : paymentList) {
			totalAmtPaid = totalAmtPaid + bean.getPaidAmount();
		}
		PurchaseBean localBean = null;
		for (PurchaseBean bean : purchaseList) {
			if (bean.getPurchaseId().equals(purchaseBean.getPurchaseId())) {
				bean.setBalance(bean.getPurchaseAmount() - totalAmtPaid);
				bean.setMaterialTypeName(typeMap.get(bean.getMaterialTypeId()));
				localBean = new PurchaseBean(bean);
				break;
			}
		}
		paymentCollectionBean.setPaymentBeans(paymentList);
		view.addObject("purchaseList", purchaseList);
		view.addObject("beanForPaymentTracking", localBean);
		session.setAttribute("beanForPaymentTracking", localBean);
		view.addObject("paymentCollectionBean", paymentCollectionBean);
		return view;
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method add one empty PaymentBean into the list of PaymentBeans of
	 * PaymentCollectionBean. This will increase one row on the ui so that user can
	 * add the payment details. After adding bean it is redirected to
	 * PaymentTracker.jsp.
	 * 
	 * @param paymentCollectionBean
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addRow.html", method = RequestMethod.POST)
	public ModelAndView addPaymentRow(
			@ModelAttribute("paymentCollectionBean") PaymentCollectionBean paymentCollectionBean, HttpSession session)
			throws Exception {
		LOGGER.info("Execution Started [addPaymentRow()]");
		ModelAndView view = new ModelAndView("PaymentTracker");
		PaymentBean paymentBean = new PaymentBean();
		paymentBean.setEnabled("Y");
		@SuppressWarnings("unchecked")
		List<PaymentBean> paymentList = paymentCollectionBean.getPaymentBeans();
		if (paymentList == null) {
			paymentList = new ArrayList<PaymentBean>();
			paymentCollectionBean.setPaymentBeans(paymentList);
		}
		paymentList.add(paymentBean);

		view.addObject("paymentCollectionBean", paymentCollectionBean);
		view.addObject("purchaseList", purchaseList);
		view.addObject("beanForPaymentTracking", session.getAttribute("beanForPaymentTracking"));

		LOGGER.info("Execution over [addPaymentRow()]");
		return view;
	}

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method delete the selected PaymentBean from the list of PaymentBeans of
	 * PaymentCollectionBean. This will remove the selected row from the ui. After
	 * removing bean it is redirected to PaymentTracker.jsp. PaymentCollectionBean
	 * 
	 * @param paymentCollectionBean
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deletePaymentRow.html", method = RequestMethod.POST)
	public ModelAndView deletePaymentRow(
			@ModelAttribute("paymentCollectionBean") PaymentCollectionBean paymentCollectionBean, HttpSession session)
			throws Exception {
		LOGGER.info("Execution Started [deletePaymentRow()]");
		ModelAndView view = new ModelAndView("PaymentTracker");
		List<PaymentBean> paymentList = paymentCollectionBean.getPaymentBeans();
		Iterator<PaymentBean> iterator = paymentList.iterator();
		while (iterator.hasNext()) {
			PaymentBean bean = iterator.next();
			if (bean.getChecked() != null && bean.getChecked().equals("Y"))
				iterator.remove();
		}

		view.addObject("paymentCollectionBean", paymentCollectionBean);
		view.addObject("purchaseList", purchaseList);
		view.addObject("beanForPaymentTracking", session.getAttribute("beanForPaymentTracking"));
		LOGGER.info("Execution over [deletePaymentRows()]");
		return view;
	}

}
