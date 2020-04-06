package com.accenture.lkm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.business.bean.VendorBean;
import com.accenture.lkm.dao.VendorDAO;
import com.accenture.lkm.entity.VendorEntity;

@Service
public class VendorServiceImpl implements VendorService{
	@Autowired
	private VendorDAO vendorDAO;
	
	public List<VendorBean> getVendorDetails(){
		Iterable<VendorEntity> collec =vendorDAO.findAll();
		List<VendorBean> listVendor = new ArrayList<VendorBean> ();
		for (VendorEntity vendorEntity : collec) {
			VendorBean vendor=new VendorBean();
			BeanUtils.copyProperties(vendorEntity, vendor);
			listVendor.add(vendor);
		}
		return listVendor;
	}
}
