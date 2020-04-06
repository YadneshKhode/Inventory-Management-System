package com.accenture.lkm.services;

import java.sql.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.dao.PurchaseDAO;
import com.accenture.lkm.entity.PurchaseEntity;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	private static Logger LOGGER =  Logger.getLogger(PurchaseServiceImpl.class);

	@Autowired
	private PurchaseDAO purchaseDAO;	
	
	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to insert purchase details data into the purchase table.
	 * Also, this method will add a single row into the payment table with paid amount as zero to keep the track of the balance amount
	 * for a specific purchase.
	 * @param purchaseBean
	 * @param materialCategoryName
	 * @return
	 * @throws Exception
	 */
	@Override
	public PurchaseBean addPurchaseDetails(PurchaseBean purchaseBean) throws Exception {
		LOGGER.info("Execution Started [addPurchaseDetails()] with purchaseBean:["+purchaseBean+"]");
		purchaseBean = this.insertPurchaseDetails(purchaseBean);		
		LOGGER.info("Execution over [addPurchaseDetails()] with purchaseBean:["+purchaseBean+ "]");
		return purchaseBean;
	}

	
	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method will be called by addPurchaseDetails method to insert the data into the Purchase table.
	 * @param purchaseBean
	 * @param materialCategoryName
	 * @return
	 * @throws Exception
	 */
	private PurchaseBean insertPurchaseDetails(PurchaseBean purchaseBean) throws Exception {
		LOGGER.info("Execution Started [insertPurchaseDetails()] with purchaseBean:["+purchaseBean + "]");
		PurchaseEntity purchaseEntity = new PurchaseEntity();
		BeanUtils.copyProperties(purchaseBean, purchaseEntity);
		purchaseEntity.setPurchaseDate(Date.valueOf(purchaseBean.getPurchaseDate()));
		purchaseEntity.setTransactionId(this.transactionIdGenerator(purchaseBean.getVendorName(),
				purchaseBean.getMaterialCategoryName(), purchaseBean.getPurchaseDate()));
		purchaseEntity.setStatus("Pending");
		purchaseEntity = purchaseDAO.savePurchaseDetail(purchaseEntity);
		purchaseBean.setPurchaseId(purchaseEntity.getPurchaseId());
		purchaseBean.setTransactionId(purchaseEntity.getTransactionId());
		LOGGER.info("Execution over [insertPurchaseDetails()] with purchaseBean:["+purchaseBean + "]");
		return purchaseBean;
	}	
	

	/**
	 * METHOD DESCRIPTION: <br/>
	 * This method is used to generate the transaction id as per logic- 
	 * P_first 3 characters of vendor name_purchase date in mmddyyyy format_first 3 characters of material category purchased_primary key of purchase table
	 * @param vendorName
	 * @param materialCategoryName
	 * @param purchaseDate
	 * @return
	 */
	private String transactionIdGenerator(String vendorName, String materialCategoryName, String purchaseDate) {
		LOGGER.info("Execution Started [transactionIdGenerator()] with vendorName:["+vendorName+"],materialCategoryName:[ "+ materialCategoryName + "],purchaseDate:[ "+ purchaseDate + "]");
		StringBuffer key = new StringBuffer("P_");
		if (vendorName != null) {
			key.append(vendorName.toUpperCase(), 0, 3);
			key.append("_");
		}		
		String pDate = purchaseDate.substring(5, 7)+purchaseDate.substring(8,10)+purchaseDate.substring(0, 4);
		key.append(pDate);
		key.append("_");
		if (materialCategoryName != null) {			
			key.append(materialCategoryName.toUpperCase(), 0, 3);
			key.append("_");
		}		
		LOGGER.info("Execution over [transactionIdGenerator()] with vendorName:["+vendorName+"],materialCategoryName:[ "+ materialCategoryName + "],purchaseDate:[ "+ purchaseDate + "]");
		return key.toString();

	}

	

}
