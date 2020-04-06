package com.accenture.lkm.validator;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.accenture.lkm.business.bean.PaymentBean;
import com.accenture.lkm.business.bean.PaymentCollectionBean;

public class PaymentCollectionBeanValidator implements Validator {

	@Override
	public boolean supports(Class arg0) {
		return PaymentCollectionBean.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		PaymentCollectionBean form = (PaymentCollectionBean) arg0;
		List<PaymentBean> paymentBeans = form.getPaymentBeans();
		if (paymentBeans == null) {
			return;
		}
		for (int i = 0; i < paymentBeans.size(); i++) {
			PaymentBean bean = paymentBeans.get(i);
			if (bean.getPaidAmount() == null || "".equals(bean.getPaidAmount())) {
				errors.rejectValue("paymentBeans[" + i + "].paidAmount", "paymentBeans.paidAmount.notblank");
			}
			if (bean.getPaidType() == null || "".equals(bean.getPaidType())) {
				errors.rejectValue("paymentBeans[" + i + "].paidType", "paymentBeans.paidType.notblank");
				if(bean.getChequeNo() == null || "".equals(bean.getChequeNo())) {
					errors.rejectValue("paymentBeans[" + i + "].chequeNo", "paymentBeans.chequeNo.notblank");
				}
			}else {
				if(bean.getPaidType().equals("Cheque") && (bean.getChequeNo() == null || "".equals(bean.getChequeNo()))){
					errors.rejectValue("paymentBeans[" + i + "].chequeNo", "paymentBeans.chequeNo.notblank");
				}
			}
			if (bean.getRemarks() == null || "".equals(bean.getRemarks())) {
				errors.rejectValue("paymentBeans[" + i + "].remarks", "paymentBeans.remarks.notblank");
			}
			if(bean.getPaidDate() == null || "".equals(bean.getPaidDate())) {
				errors.rejectValue("paymentBeans[" + i + "].paidDate", "paymentBeans.paidDate.notblank");
			}
		}

	}

}
