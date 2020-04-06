package com.accenture.lkm.business.bean;

import org.springframework.format.annotation.NumberFormat;

public class PaymentBean {

	private Integer paidId;
	private String paidType;
	private String chequeNo;
	private String paidDate;
	private String remarks;
	private Double balance;
	@NumberFormat
	private Double paidAmount;
	private Integer purchaseId;
	private String enabled;  // N stands for disable and Y stands for enable
	private String checked; // Y stands for checked and N stand for unchecked
	
	

	public PaymentBean() {
		super();
	}

	public PaymentBean(Integer paidId, String paidType, String chequeNo, String remarks, Double paidAmount, Double balance,
			String paidDate, Integer purchaseId, String enabled, String checked) {
		super();
		this.paidAmount = paidAmount;
		this.paidDate = paidDate;
		this.paidId = paidId;
		this.paidType = paidType;
		this.purchaseId = purchaseId;
		this.remarks = remarks;
		this.chequeNo = chequeNo;
		this.balance = balance;
		this.enabled = enabled;
		this.checked = checked;
		
	}

	public Integer getPaidId() {
		return paidId;
	}

	public void setPaidId(Integer paidId) {
		this.paidId = paidId;
	}

	public String getPaidType() {
		return paidType;
	}

	public void setPaidType(String paidType) {
		this.paidType = paidType;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(String paidDate) {
		this.paidDate = paidDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}	

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}	

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "PaymentEntityBean [purchaseId=" + purchaseId + ", paidAmount=" + paidAmount  + ", checked=" + checked + ", enabled=" + enabled + ", balance=" + balance
				+ ", remarks=" + remarks + ", paidDate=" + paidDate + ", chequeNo=" + chequeNo + ", paidType="
				+ paidType + ", paidId=" + paidId + "]";
	}

}
