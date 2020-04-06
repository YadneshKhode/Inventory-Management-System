package com.accenture.lkm.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class PaymentEntity {
	@Id
	@Column(name="paid_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer paidId;	
	@Column(name="paid_type")
	private String paidType;
	@Column(name="cheque_no")
	private String chequeNo;	
	@Column(name="paid_date")
	private Date paidDate;
	@Column(name="remarks")
	private String remarks;	
	@Column(name="balance")
	private Double balance;
	@Column(name="paid_amount")
	private Double paidAmount;	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="purchase_id")
	private PurchaseEntity purchaseEntity ;
	
	public PaymentEntity() {
		super();
	}

	public PaymentEntity(Integer paidId, String paidType, String chequeNo, String remarks, Double paidAmount, Double balance, Date paidDate, PurchaseEntity purchaseEntity) {
		super();
		this.paidAmount = paidAmount;
		this.paidDate = paidDate;
		this.paidId = paidId;
		this.paidType = paidType;
		this.purchaseEntity = purchaseEntity;
		this.remarks = remarks;
		this.chequeNo = chequeNo;
		this.balance = balance;
		
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

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
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

	public PurchaseEntity getPurchaseEntity() {
		return purchaseEntity;
	}

	public void setPurchaseEntity(PurchaseEntity purchaseEntity) {
		this.purchaseEntity = purchaseEntity;
	}		
	
	

	@Override
	public String toString() {
		return "PaymentEntity [purchaseEntity=" + purchaseEntity + ", paidAmount="  + paidAmount + ", balance=" + balance + ", remarks="
				+ remarks + ", paidDate=" + paidDate + ", chequeNo=" + chequeNo + ", paidType="
				+ paidType + ", paidId=" + paidId + "]";
	}
	
	
}
