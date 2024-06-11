package com.iSoftNetwork.ordersmservice.repository;

import com.iSoftNetwork.ordersmservice.entities.ProductItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductItemRepository extends JpaRepository<ProductItems, Long> {
    List<ProductItems> findByProductId(Long productId);
}
