package com.accenture.lkm.services;

import org.springframework.beans.BeanUtils;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.lkm.business.bean.PaymentBean;
import com.accenture.lkm.business.bean.PaymentCollectionBean;
import com.accenture.lkm.entity.PaymentEntity;

public interface PaymentService
{
	void savePaymentDetails(PaymentBean paymentBean) throws Exception;
}