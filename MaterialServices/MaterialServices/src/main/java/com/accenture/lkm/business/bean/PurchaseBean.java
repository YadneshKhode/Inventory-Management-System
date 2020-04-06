package com.accenture.lkm.business.bean;

import java.sql.Date;

public class PurchaseBean {
    private String purchaseId;
	private String vendorName;
	private String materialCategoryId;
	private String materialTypeId;	
	private String brandName;
	private String unitId;
	private int quantity;
	private double purchaseAmount;
	private Date purchaseDate;
		
	public PurchaseBean() {
		super();
	}

	public PurchaseBean(String purchaseId, String vendorName, String materialCategoryId, String materialTypeId,
			String brandName, String unitId, int quantity, double purchaseAmount, Date purchaseDate) {
		super();
		this.purchaseId = purchaseId;
		this.vendorName = vendorName;
		this.materialCategoryId = materialCategoryId;
		this.materialTypeId = materialTypeId;
		this.brandName = brandName;
		this.unitId = unitId;
		this.quantity = quantity;
		this.purchaseAmount = purchaseAmount;
		this.purchaseDate = purchaseDate;
	}

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getMaterialCategoryId() {
		return materialCategoryId;
	}

	public void setMaterialCategoryId(String materialCategoryId) {
		this.materialCategoryId = materialCategoryId;
	}

	public String getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(String materialTypeId) {
		this.materialTypeId = materialTypeId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Override
	public String toString() {
		return "PurchaseBean [purchaseId=" + purchaseId + ", vendorName=" + vendorName + ", materialCategoryId="
				+ materialCategoryId + ", materialTypeId=" + materialTypeId + ", brandName=" + brandName + ", unitId="
				+ unitId + ", quantity=" + quantity + ", purchaseAmount=" + purchaseAmount + ", purchaseDate="
				+ purchaseDate + "]";
	}
	
	
	
}
