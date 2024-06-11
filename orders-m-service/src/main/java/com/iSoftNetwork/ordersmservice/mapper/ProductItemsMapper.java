package com.iSoftNetwork.ordersmservice.mapper;

import com.iSoftNetwork.ordersmservice.dto.ProductItemsRequestDTO;
import com.iSoftNetwork.ordersmservice.dto.ProductItemsResponseDTO;
import com.iSoftNetwork.ordersmservice.entities.ProductItems;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductItemsMapper {
    ProductItemsResponseDTO fromProductItems(ProductItems productItems);
    ProductItems fromProductItemsRequestDTO(ProductItemsRequestDTO productItemsRequestDTO);

}
