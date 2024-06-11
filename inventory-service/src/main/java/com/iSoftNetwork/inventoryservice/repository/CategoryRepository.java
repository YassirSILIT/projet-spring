package com.iSoftNetwork.inventoryservice.repository;

import com.iSoftNetwork.inventoryservice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findCategoryByCode(String code);
}
