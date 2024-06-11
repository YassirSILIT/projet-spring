package com.iSoftNetwork.inventoryservice.services;

import com.iSoftNetwork.inventoryservice.dto.CategoryDTO;
import com.iSoftNetwork.inventoryservice.entities.Category;
import com.iSoftNetwork.inventoryservice.exceptions.CategoryNotFoundException;
import com.iSoftNetwork.inventoryservice.mappers.CategoryMapper;
import com.iSoftNetwork.inventoryservice.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        log.info(String.format("Saving new Category => %s" , categoryDTO.toString()));
        Category categoryToSave = categoryMapper.fromCategoryDTO(categoryDTO);
        Category savedCategory = categoryRepository.save(categoryToSave);
        CategoryDTO result = categoryMapper.fromCategory(savedCategory);
        return result;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) throw new CategoryNotFoundException("No category Exist");
        return categoryMapper.fromListCategory(categories);
    }

    @Override
    public CategoryDTO findCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) throw new CategoryNotFoundException("Category" + id + "Not Found");

        return categoryMapper.fromCategory(category.get());
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        log.info(String.format("Updating the Category => %s" , categoryDTO.toString()));
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) throw new CategoryNotFoundException("Category" + id + "Not Found");
        categoryDTO.setId(id);
        Category categoryToUpdate = categoryMapper.fromCategoryDTO(categoryDTO);
        Category updateCategory = categoryRepository.save(categoryToUpdate);
        return categoryMapper.fromCategory(updateCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) throw new CategoryNotFoundException("Category" + id + "Not Found");
        categoryRepository.deleteById(id);
    }
}
