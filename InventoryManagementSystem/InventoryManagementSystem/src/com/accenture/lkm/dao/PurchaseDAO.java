package com.accenture.lkm.dao;

import java.util.List;

import com.accenture.lkm.entity.PurchaseEntity;

public interface PurchaseDAO{
	public PurchaseEntity savePurchaseDetail(PurchaseEntity purchaseEntity) throws Exception;
	public List<PurchaseEntity> getPurchaseDetails(String vendoreName);	
	public PurchaseEntity updatePurchaseDetail(PurchaseEntity purchaseEntity) throws Exception;

}
