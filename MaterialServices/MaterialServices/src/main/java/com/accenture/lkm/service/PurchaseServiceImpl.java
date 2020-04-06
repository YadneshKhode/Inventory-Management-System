package com.accenture.lkm.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.PurchaseBean;
import com.accenture.lkm.dao.MaterialCategoryDAO;
import com.accenture.lkm.dao.PurchaseDAO;
import com.accenture.lkm.entity.MaterialCategoryEntity;
import com.accenture.lkm.entity.PurchaseEntity;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	static int count = 1001;

	@Autowired
	private PurchaseDAO purchaseDAO;
	@Autowired
	private MaterialCategoryDAO materialCategoryDAO;

	public String insertPurchaseDetails(PurchaseBean purchaseBean) {
		PurchaseEntity purchaseEntity = new PurchaseEntity();
		BeanUtils.copyProperties(purchaseBean, purchaseEntity);

		purchaseEntity.setPurchaseId(this.primaryKeyGenerator(purchaseBean.getVendorName(),
				purchaseBean.getMaterialCategoryId(), purchaseBean.getPurchaseDate()));
		PurchaseEntity entity = purchaseDAO.save(purchaseEntity);
		return entity.getPurchaseId();
	}

	private String primaryKeyGenerator(String vendorName, String materialCategoryId, Date purchaseDate) {
		StringBuffer key = new StringBuffer("P_");
		if (vendorName != null) {
			key.append(vendorName.toUpperCase(), 0, 3);
			key.append("_");
		}
		String pattern = "MMddyyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		if (purchaseDate != null) {
			String date = format.format(purchaseDate);
			key.append(date);
			key.append("_");
		}
		if (materialCategoryId != null) {
			MaterialCategoryEntity matCatEntity = materialCategoryDAO.findOne(materialCategoryId);
			key.append(matCatEntity.getCategoryName().toUpperCase(), 0, 3);
			key.append("_");
		}
		key.append(count);
		count++;
		return key.toString();

	}

}
