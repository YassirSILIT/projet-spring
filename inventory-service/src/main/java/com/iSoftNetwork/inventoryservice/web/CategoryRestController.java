package com.iSoftNetwork.inventoryservice.web;

import com.iSoftNetwork.inventoryservice.dto.CategoryDTO;
import com.iSoftNetwork.inventoryservice.dto.ProductDTO;
import com.iSoftNetwork.inventoryservice.services.CategoryService;
import com.iSoftNetwork.inventoryservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryRestController {
    private CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO saveCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.addCategory(categoryDTO);
    }
    @PutMapping("/{id}")
    public CategoryDTO updateCategory(@PathVariable  Long id, @RequestBody CategoryDTO categoryDTO){
        return categoryService.updateCategory(id,categoryDTO);
    }
    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.findCategoryById(id);
    }
    @GetMapping
    public List<CategoryDTO> getAllCategory(){
        return categoryService.getAllCategories();
    }
    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
