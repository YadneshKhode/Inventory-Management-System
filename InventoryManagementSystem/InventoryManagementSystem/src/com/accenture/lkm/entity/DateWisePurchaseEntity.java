package com.accenture.lkm.entity;

public class DateWisePurchaseEntity {

	private String materialCategory;
	private String materialType;
	private String brand;
	private String quantity;
	private String unit;
	private Double price;
	private String status;
	private String vendor;

	public DateWisePurchaseEntity() {
		super();
	}

	public DateWisePurchaseEntity(String materialCategory, String materialType, String brand,
			String quantity, String unit, String status, String vendor,
			Double price) {
		super();
		this.brand=brand;
		this.materialCategory = materialCategory;
		this.materialType = materialType;
		this.quantity = quantity;
		this.status =status;
		this.unit =unit;
		this.vendor = vendor;
		this.price  = price;		
	}

	public String getMaterialCategory() {
		return materialCategory;
	}

	public void setMaterialCategory(String materialCategory) {
		this.materialCategory = materialCategory;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	@Override
	public String toString() {
		return "DateWisePurchaseBean [vendor=" + vendor + ", status=" + status + ", price=" + price
				+ ", unit=" + unit + ", quantity=" + quantity + ", brand=" + brand + ", materialType="
				+ materialType + ", materialCategory=" + materialCategory + "]";
	}


}
