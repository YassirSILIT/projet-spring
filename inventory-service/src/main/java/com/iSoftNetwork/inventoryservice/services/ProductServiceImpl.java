package com.iSoftNetwork.inventoryservice.services;

import com.iSoftNetwork.inventoryservice.dto.CategoryDTO;
import com.iSoftNetwork.inventoryservice.dto.CountName;
import com.iSoftNetwork.inventoryservice.dto.ProductDTO;
import com.iSoftNetwork.inventoryservice.entities.Product;
import com.iSoftNetwork.inventoryservice.exceptions.CategoryNotFoundException;
import com.iSoftNetwork.inventoryservice.exceptions.ProductNotFoundException;
import com.iSoftNetwork.inventoryservice.mappers.CategoryMapper;
import com.iSoftNetwork.inventoryservice.mappers.ProductMapper;
import com.iSoftNetwork.inventoryservice.repository.CategoryRepository;
import com.iSoftNetwork.inventoryservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService{

    private ProductMapper productMapper;
    private ProductRepository productRepository;
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository, CategoryService categoryService, CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO, CategoryDTO categoryDTO){
        log.info(String.format("Saving new product => %s ", productDTO.toString()));
        categoryRepository.findById(categoryDTO.getId()).orElse(null);
        if (categoryDTO == null){
            throw new CategoryNotFoundException("Category Not Found");
        }

        //ProductDTO productDTO1 = new ProductDTO();
        productDTO.setCategory(categoryDTO);
        Product productToSave = productMapper.fromProductDTO(productDTO);
        Product savedProduct = productRepository.save(productToSave);
        ProductDTO result = productMapper.fromProduct(savedProduct);
        return result;
    }
    @Override
    public ProductDTO findProductById(Long id){
        Optional<Product> product = productRepository.findById(id);
                //.orElseThrow(()-> new ProductNotFoundException("Product " + id + " Not Found"));
        if (product.isEmpty()) throw new ProductNotFoundException("Product " + id + " Not Found");
        return productMapper.fromProduct(product.get());
    }

    @Override
    public List<ProductDTO> getAllProducts() throws ProductNotFoundException{
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) throw new ProductNotFoundException("No Products Exist");
        return productMapper.fromListProduct(products);
    }

    @Override
    public List<ProductDTO> searchProducts(String keyword) throws ProductNotFoundException {
        List<Product> products = productRepository.findByNameContainsIgnoreCase(keyword);
        if (products.isEmpty()) throw new ProductNotFoundException("no products with this name");
        return productMapper.fromListProduct(products);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO,CategoryDTO categoryDTO) throws ProductNotFoundException {
        log.info(String.format("Updating the product => %s ", productDTO.toString()));
        categoryRepository.findById(categoryDTO.getId()).orElse(null);
        if (categoryDTO == null){
            throw new CategoryNotFoundException("Category Not Found");
        }
        productDTO.setCategory(categoryDTO);
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) throw new ProductNotFoundException("Product " + id + " Not Found");
        productDTO.setId(id);
        Product productToUpdate = productMapper.fromProductDTO(productDTO);
        Product updateProduct = productRepository.save(productToUpdate);
        return productMapper.fromProduct(updateProduct);
    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) throw new ProductNotFoundException("Product " + id + " Not Found");
        productRepository.deleteById(id);
    }
    @Override
    public List<CountName> getPercentageGroupByName(){
        return productRepository.getPercentageGroupByName();
    }

}
