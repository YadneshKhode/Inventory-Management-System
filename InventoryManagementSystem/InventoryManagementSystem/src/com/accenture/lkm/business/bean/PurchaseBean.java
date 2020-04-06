package com.accenture.lkm.business.bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class PurchaseBean {
    private Integer purchaseId;
    private String transactionId;
    @NotBlank
	private String vendorName;
    @NotBlank
	private String materialCategoryId;
    @NotBlank
	private String materialTypeId;
    @NotBlank
	private String brandName;
    @NotBlank
	private String unitId;
    @NotNull
	private Integer quantity;
    @NotNull
    @NumberFormat
	private Double purchaseAmount;
    @NumberFormat
	private Double balance;
	@NotBlank
	private String purchaseDate;
	private String materialCategoryName;
	private String materialTypeName;
	private String materialUnitName;
	private String status;
		
	public PurchaseBean() {
		super();
	}
	
	public PurchaseBean(PurchaseBean bean) {
		this.setBalance(bean.getBalance());
		this.setBrandName(bean.getBrandName());
		this.setMaterialCategoryId(bean.getMaterialCategoryId());
		this.setMaterialCategoryName(bean.getMaterialCategoryName());
		this.setMaterialTypeId(bean.getMaterialTypeId());
		this.setMaterialTypeName(bean.getMaterialTypeName());
		this.setMaterialUnitName(bean.getMaterialUnitName());
		this.setPurchaseAmount(bean.getPurchaseAmount());
		this.setPurchaseDate(bean.getPurchaseDate());
		this.setPurchaseId(bean.getPurchaseId());
		this.setQuantity(bean.getQuantity());
		this.setStatus(bean.getStatus());
		this.setTransactionId(bean.getTransactionId());
		this.setVendorName(bean.getVendorName());
		this.setUnitId(bean.getUnitId());
		
	}
	

	public PurchaseBean(Integer purchaseId, String transactionId, String vendorName, String materialCategoryId, String materialTypeId,Double balance,
			String brandName, String unitId, Integer quantity, Double purchaseAmount, String purchaseDate,String materialCategoryName, String materialTypeName, String materialUnitName, String status) {
		super();
		this.purchaseId = purchaseId;
		this.transactionId = transactionId;
		this.vendorName = vendorName;
		this.materialCategoryId = materialCategoryId;
		this.materialTypeId = materialTypeId;
		this.brandName = brandName;
		this.unitId = unitId;
		this.quantity = quantity;
		this.purchaseAmount = purchaseAmount;
		this.purchaseDate = purchaseDate;
		this.materialCategoryName = materialCategoryName;
		this.materialTypeName = materialTypeName;
		this.materialUnitName = materialUnitName;
		this.status = status;
		this.balance = balance;
	}

	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(Double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
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

	public String getMaterialUnitName() {
		return materialUnitName;
	}

	public void setMaterialUnitName(String materialUnitName) {
		this.materialUnitName = materialUnitName;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "PurchaseBean [purchaseId=" + purchaseId + ", transactionId=" + transactionId + ", balance=" + balance + ", vendorName="
				+ vendorName + ", materialCategoryId=" + materialCategoryId + ", materialTypeId=" + materialTypeId
				+ ", brandName=" + brandName + ", unitId=" + unitId + ", quantity=" + quantity + ", purchaseAmount="
				+ purchaseAmount + ", purchaseDate=" + purchaseDate + ", materialCategoryName=" + materialCategoryName
				+ ", materialTypeName=" + materialTypeName + ", materialUnitName=" + materialUnitName + ", status="
				+ status + "]";
	}

	
	
	
	
}
