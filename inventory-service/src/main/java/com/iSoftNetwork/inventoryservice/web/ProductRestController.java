package com.iSoftNetwork.inventoryservice.web;

import com.iSoftNetwork.inventoryservice.dto.CategoryDTO;
import com.iSoftNetwork.inventoryservice.dto.CountName;
import com.iSoftNetwork.inventoryservice.dto.ProductDTO;
import com.iSoftNetwork.inventoryservice.services.CategoryService;
import com.iSoftNetwork.inventoryservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {
    private ProductService productService;
    private CategoryService categoryService;

    public ProductRestController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping("/category/{idCategory}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO, @PathVariable Long idCategory){
        CategoryDTO categoryDTO = categoryService.findCategoryById(idCategory);
        return productService.addProduct(productDTO,categoryDTO);
    }
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }
    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/percentCountName")
    public List<CountName> getPercentageGroupByName(){
        return productService.getPercentageGroupByName();
    }
    @PutMapping("/{id}/category/{idCategory}")
    public ProductDTO updateProduct(@PathVariable Long id,@RequestBody ProductDTO productDTO,@PathVariable Long idCategory){
        CategoryDTO categoryDTO = categoryService.findCategoryById(idCategory);
        return productService.updateProduct(id,productDTO,categoryDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
    @GetMapping("/search")
    public List<ProductDTO> searchProduct(@RequestParam String keyword){
        return productService.searchProducts(keyword);
    }
    //http://localhost:8083/products/search?keyword=iphone

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
