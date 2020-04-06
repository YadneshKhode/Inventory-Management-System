package com.accenture.lkm.business.bean;

import org.hibernate.validator.constraints.NotBlank;

public class DateWisePurchaseReportBean {
	@NotBlank
	private String fromDate;
	@NotBlank
	private String toDate;
	
	public DateWisePurchaseReportBean() {
		super();
	}
	
	public DateWisePurchaseReportBean(String fromDate,  String toDate) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}		

	@Override
	public String toString() {
		return "DateWisePurchaseReportBean [fromDate=" + fromDate + ", toDate="
				+ toDate + "]";
	}

}
