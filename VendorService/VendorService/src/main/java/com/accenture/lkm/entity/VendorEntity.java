package com.accenture.lkm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendor")
public class VendorEntity {
	@Id
	@Column(name="vendor_id")
	private String vendorId;
	@Column(name="vendor_name")
	private String vendorName;
	@Column(name="vendor_address")
	private String vendorAddress;
	@Column(name="contact_person")
	private String contactPerson;
	@Column(name="contact_number")
	private long contactNumber;
	public VendorEntity()
	{
		super();
	}
	
	public VendorEntity(String vendorId, String vendorName, String vendorAddress, String contactPerson,
			long contactNumber) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorAddress = vendorAddress;
		this.contactPerson = contactPerson;
		this.contactNumber = contactNumber;
	}

	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String toString() {
		return "VendorEntity [vendorId=" + vendorId + ", vendorName=" + vendorName + ", vendorAddress=" + vendorAddress
				+ ", contactPerson=" + contactPerson + ", contactNumber=" + contactNumber + "]";
	}
	
}
