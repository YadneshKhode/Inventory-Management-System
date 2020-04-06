package com.accenture.lkm.business.bean;

import java.sql.Date;

public class VendorWisePuchaseBean {


	
	private PurchaseBean purchaseBean = new PurchaseBean();
	private Double balance;   	
	
	public VendorWisePuchaseBean(Object[] columns) {   		
		this.purchaseBean.setPurchaseId(columns[0] != null ? (Integer)columns[0] : 0);
		this.purchaseBean.setTransactionId((String)columns[1]);
		this.purchaseBean.setVendorName((String)columns[2]);
		this.purchaseBean.setMaterialCategoryId((String)columns[3]);
		this.purchaseBean.setMaterialTypeId((String)columns[4]);
		this.purchaseBean.setBrandName((String)columns[5]);
		this.purchaseBean.setUnitId((String)columns[6]);
		this.purchaseBean.setQuantity((Integer)columns[7]);
		this.purchaseBean.setPurchaseAmount((Double)columns[8]);
		Date date = (Date) columns[9];		
		this.purchaseBean.setPurchaseDate(date.toString());
		this.purchaseBean.setStatus((String)columns[10]);
		this.balance = (Double) columns[11];
    }
		
	public VendorWisePuchaseBean() {
		super();
	}

	public VendorWisePuchaseBean(PurchaseBean purchaseBean, Double balance) {
		super();
		this.balance = balance;
		this.purchaseBean = purchaseBean;		
	}	

	public PurchaseBean getPurchaseBean() {
		return purchaseBean;
	}

	public void setPurchaseBean(PurchaseBean purchaseBean) {
		this.purchaseBean = purchaseBean;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "VendorWisePuchaseBean [balance=" + balance + ", purchaseBean="  + purchaseBean +"]";
	}	
	
}
