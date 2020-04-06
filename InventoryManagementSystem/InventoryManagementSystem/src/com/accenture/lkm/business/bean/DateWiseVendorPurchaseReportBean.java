package com.accenture.lkm.business.bean;

import org.hibernate.validator.constraints.NotBlank;

public class DateWiseVendorPurchaseReportBean {
private String fromDate;
	
	private String toDate;
	
	@NotBlank
	private String vendorName;
	@NotBlank
	private String transactionId;
	private String brandName;
	private Integer quantity;
	private String materialCategoryName;
	private String materialTypeName;
	
	
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getMaterialCategoryName() {
		return materialCategoryName;
	}

	public void setMaterialCategoryName(String materialCategoryName) {
		this.materialCategoryName = materialCategoryName;
	}

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	
	
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	
	
	public DateWiseVendorPurchaseReportBean() {
		super();
	}
	
	public DateWiseVendorPurchaseReportBean(String fromDate,  String toDate, String vendorName, String transactionId) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.vendorName=vendorName;
		this.transactionId=transactionId;
		
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
				+ toDate + ", vendorName="+vendorName+", purchaseId="+transactionId+"]";
	}

}
