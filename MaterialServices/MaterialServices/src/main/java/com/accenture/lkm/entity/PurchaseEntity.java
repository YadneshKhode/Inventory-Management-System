package com.accenture.lkm.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchase")
public class PurchaseEntity {
	@Id
	@Column(name="purchase_id")
	private String purchaseId;
	@Column(name="vendor_name")
	private String vendorName;
	@Column(name="material_category_id")
	private String materialCategoryId;
	@Column(name="material_type_id")
	private String materialTypeId;	
	@Column(name="brandname")
	private String brandName;
	@Column(name="unit_id")
	private String unitId;
	@Column(name="quantity")
	private int quantity;
	@Column(name="status")
	private String status;
	@Column(name="purchase_amount")
	private double purchaseAmount;
	@Column(name="purchase_date")
	private Date purchaseDate;
		
	public PurchaseEntity() {
		super();
	}

	public PurchaseEntity(String purchaseId, String vendorName, String materialCategoryId, String materialTypeId,
			String brandName, String unitId, int quantity, double purchaseAmount, Date purchaseDate, String status) {
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
		this.status = status;
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
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PurchaseBean [purchaseId=" + purchaseId + ", vendorName=" + vendorName + ", status="  + status + ", materialCategoryId="
				+ materialCategoryId + ", materialTypeId=" + materialTypeId + ", brandName=" + brandName + ", unitId="
				+ unitId + ", quantity=" + quantity + ", purchaseAmount=" + purchaseAmount + ", purchaseDate="
				+ purchaseDate + "]";
	}
	
	
	
	
}
