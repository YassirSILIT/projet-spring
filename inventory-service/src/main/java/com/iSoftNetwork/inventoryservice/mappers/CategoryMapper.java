package com.iSoftNetwork.inventoryservice.mappers;

import com.iSoftNetwork.inventoryservice.dto.CategoryDTO;
import com.iSoftNetwork.inventoryservice.entities.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryMapper {
    ModelMapper modelMapper = new ModelMapper();
    public CategoryDTO fromCategory(Category category){
        return modelMapper.map(category,CategoryDTO.class);
    }
    public Category fromCategoryDTO(CategoryDTO categoryDTO){
        return modelMapper.map(categoryDTO,Category.class);
    }
    public List<CategoryDTO> fromListCategory(List<Category> categories){
        return categories.stream()
                .map(category -> modelMapper.map(category,CategoryDTO.class)).collect(Collectors.toList());
    }
}
