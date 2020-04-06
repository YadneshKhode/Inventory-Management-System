package com.accenture.lkm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.accenture.lkm.entity.UnitEntity;

public interface UnitDAO extends JpaRepository<UnitEntity, String>{
	
	@Query("select u from UnitEntity u where u.materialCategoryEntity.categoryId = ?1")
	List<UnitEntity> findByCategoryId(String categoryId);
}
