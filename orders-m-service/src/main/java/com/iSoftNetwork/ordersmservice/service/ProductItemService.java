package com.iSoftNetwork.ordersmservice.service;

import com.iSoftNetwork.ordersmservice.dto.OrderRequestDTO;
import com.iSoftNetwork.ordersmservice.dto.OrderResponseDTO;
import com.iSoftNetwork.ordersmservice.dto.ProductItemsRequestDTO;
import com.iSoftNetwork.ordersmservice.dto.ProductItemsResponseDTO;
import com.iSoftNetwork.ordersmservice.entities.Order;
import com.iSoftNetwork.ordersmservice.entities.ProductItems;


import java.util.List;

public interface ProductItemService {
    List<ProductItemsResponseDTO> getAllProductItems();
    ProductItemsResponseDTO findProductItemsById(Long id);
    void deleteProductItemById(Long id);
    ProductItemsResponseDTO saveProductItem(ProductItemsRequestDTO productItemsRequestDTO,
                                            Order order );
    List<ProductItemsResponseDTO> productItemsByProductId(Long productId);
    List<ProductItems> findAllProductItems();
    ProductItems addProductItem(ProductItems productItems, Order order);
}
