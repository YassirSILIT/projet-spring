package com.iSoftNetwork.inventoryservice.mappers;

import com.iSoftNetwork.inventoryservice.dto.ProductDTO;
import com.iSoftNetwork.inventoryservice.entities.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMapper {
    ModelMapper modelMapper = new ModelMapper();

    public ProductDTO fromProduct(Product product){
        return modelMapper.map(product,ProductDTO.class);
    }
    public Product fromProductDTO(ProductDTO productDTO){
        return modelMapper.map(productDTO,Product.class);
    }
    public List<ProductDTO> fromListProduct(List<Product> products){
        return products.stream()
                .map(pr -> modelMapper.map(pr,ProductDTO.class)).collect(Collectors.toList());
    }
}
