package com.accenture.lkm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.lkm.entity.PurchaseEntity;

public interface PurchaseDAO extends JpaRepository<PurchaseEntity, String>{

}
