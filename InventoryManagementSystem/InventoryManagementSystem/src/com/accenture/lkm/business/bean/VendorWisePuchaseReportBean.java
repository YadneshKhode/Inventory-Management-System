package com.accenture.lkm.business.bean;

import org.hibernate.validator.constraints.NotBlank;

public class VendorWisePuchaseReportBean {
	
	private String fromDate;
	private String toDate;
	@NotBlank
	private String vendorName;
	public VendorWisePuchaseReportBean() {
		super();
	}
	
	public VendorWisePuchaseReportBean(String fromDate,  String toDate, String vendorName) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.vendorName = vendorName;
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
	
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	@Override
	public String toString() {
		return "DateWisePurchaseReportBean [fromDate=" + fromDate + ", toDate="
				+ toDate + "]  vendorName [" + vendorName + "]";
	}



}
