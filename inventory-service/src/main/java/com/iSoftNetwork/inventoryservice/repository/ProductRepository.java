package com.iSoftNetwork.inventoryservice.repository;

import com.iSoftNetwork.inventoryservice.dto.CountName;
import com.iSoftNetwork.inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByNameContainsIgnoreCase(String keyword);

    @Query("SELECT new com.iSoftNetwork.inventoryservice.dto.CountName((COUNT(p) * 100.0) / (SELECT COUNT(p2) FROM Product p2), p.name) FROM Product p GROUP BY p.name")
    List<CountName> getPercentageGroupByName();


}
