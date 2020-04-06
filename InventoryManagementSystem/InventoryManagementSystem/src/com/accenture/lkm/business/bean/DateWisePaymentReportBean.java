package com.accenture.lkm.business.bean;

import org.hibernate.validator.constraints.NotBlank;

public class DateWisePaymentReportBean {

	
	private String fromDate;
	
	private String toDate;
	
	@NotBlank
	private String vendorName;
	@NotBlank
	private String status;
	
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	public DateWisePaymentReportBean() {
		super();
	}
	
	public DateWisePaymentReportBean(String fromDate,  String toDate, String vendorName, String status) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.vendorName=vendorName;
		this.status=status;
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
				+ toDate + ", vendorName="+vendorName+", status="+status+"]";
	}

}
