package com.iSoftNetwork.inventoryservice.services;

import com.iSoftNetwork.inventoryservice.dto.CategoryDTO;
import com.iSoftNetwork.inventoryservice.dto.CountName;
import com.iSoftNetwork.inventoryservice.dto.ProductDTO;
import com.iSoftNetwork.inventoryservice.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    ProductDTO addProduct(ProductDTO productDTO, CategoryDTO categoryDTO);
    ProductDTO findProductById(Long id);
    List<ProductDTO> getAllProducts() throws ProductNotFoundException;
    List<ProductDTO> searchProducts(String keyword) throws ProductNotFoundException;
    ProductDTO updateProduct(Long id,ProductDTO productDTO,CategoryDTO categoryDTO) throws ProductNotFoundException;
    void deleteProduct(Long id) throws ProductNotFoundException;
    List<CountName> getPercentageGroupByName();
}
