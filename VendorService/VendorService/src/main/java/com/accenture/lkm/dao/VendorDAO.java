package com.accenture.lkm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.lkm.entity.VendorEntity;
@Repository
public interface VendorDAO extends JpaRepository<VendorEntity, String>{

}
