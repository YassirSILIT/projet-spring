package com.iSoftNetwork.ordersmservice.service;

import com.iSoftNetwork.ordersmservice.dto.OrderRequestDTO;
import com.iSoftNetwork.ordersmservice.dto.ProductItemsRequestDTO;
import com.iSoftNetwork.ordersmservice.dto.ProductItemsResponseDTO;
import com.iSoftNetwork.ordersmservice.entities.Order;
import com.iSoftNetwork.ordersmservice.entities.ProductItems;
import com.iSoftNetwork.ordersmservice.exceptions.OrderNotFoundException;
import com.iSoftNetwork.ordersmservice.exceptions.ProductItemNotFoundExceptions;
import com.iSoftNetwork.ordersmservice.exceptions.ProductNotFoundExceptions;
import com.iSoftNetwork.ordersmservice.mapper.ProductItemsMapper;
import com.iSoftNetwork.ordersmservice.model.Product;
import com.iSoftNetwork.ordersmservice.repository.OrderRepository;
import com.iSoftNetwork.ordersmservice.repository.ProductItemRepository;
import com.iSoftNetwork.ordersmservice.restClient.ProductRestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductItemServiceImpl implements ProductItemService{

    private ProductItemRepository productItemRepository;
    private ProductRestClient productRestClient;
    private ProductItemsMapper productItemsMapper;
    private OrderRepository orderRepository;

    public ProductItemServiceImpl(ProductItemRepository productItemRepository,
                                  ProductRestClient productRestClient,
                                  ProductItemsMapper productItemsMapper,
                                  OrderRepository orderRepository) {
        this.productItemRepository = productItemRepository;
        this.productRestClient = productRestClient;
        this.productItemsMapper = productItemsMapper;
        this.orderRepository = orderRepository;
    }


    @Override
    public List<ProductItemsResponseDTO> getAllProductItems() {

        List<ProductItems> productItems = productItemRepository.findAll();
        //List<Order> order = orderRepository.findAll();
        productItems.forEach(pr -> {
           Product product = productRestClient.findProductById(pr.getProductId());
           pr.setProduct(product);
           //pr.setOrder(order);
        });
        if (productItems.isEmpty())
            throw new ProductItemNotFoundExceptions("No ProductItem exist");
        return productItems.stream()
                .map(pri-> productItemsMapper.fromProductItems(pri))
                .collect(Collectors.toList());
    }

    @Override
    public ProductItemsResponseDTO findProductItemsById(Long id) {
        ProductItems productItems = productItemRepository.findById(id).get();
        if (productItems == null)
            throw new ProductItemNotFoundExceptions("ProductItem " + id + " Not Found");
        //productItems.setProduct(productRestClient.findProductById(productItems.getProductId()));
        //productItems.setOrder((Order) orderRepository.findAll());
        //System.out.println(productItems.getOrder());
        Product product = productRestClient.findProductById(productItems.getProductId());
        productItems.setProduct(product);
        return productItemsMapper.fromProductItems(productItems);
    }

    @Override
    public void deleteProductItemById(Long id) {
        Optional<ProductItems> productItemById = productItemRepository.findById(id);
            if (productItemById.isEmpty())
                throw new ProductItemNotFoundExceptions("ProductItem " + id + " Not Found");
        /*List<Order> order = orderRepository.findAll();
        order
        order.getProductItems().forEach(pr->{
            pr.setProduct(productRestClient.findProductById(pr.getProductId()));
        });*/
            productItemRepository.deleteById(id);
    }

    @Override
    public ProductItemsResponseDTO saveProductItem(ProductItemsRequestDTO productItemsRequestDTO,
                                                   Order order ) {
        log.info(String.format("ProductItem {} is saved => %s ", productItemsRequestDTO.toString()));
        Product product = null;
        try {
            product = productRestClient.findProductById(productItemsRequestDTO.getProductId());
        }catch (Exception e){
            throw new ProductNotFoundExceptions("Product " +productItemsRequestDTO.getProductId() + " not found");
        }
        orderRepository.findById(order.getId()).orElse(null);
        if (order == null){
            throw new OrderNotFoundException("Order Not Found");
        }
        productItemsRequestDTO.setOrder(order);
        ProductItems productItems = productItemsMapper.fromProductItemsRequestDTO(productItemsRequestDTO);
        ProductItems savedProductItem = productItemRepository.save(productItems);
        savedProductItem.setProduct(product);
        return productItemsMapper.fromProductItems(savedProductItem);
    }

    @Override
    public List<ProductItemsResponseDTO> productItemsByProductId(Long productId) {
        List<ProductItems> byProductId = productItemRepository.findByProductId(productId);
        try{
            byProductId.forEach(pr->{
                Product product = productRestClient.findProductById(pr.getProductId());
                pr.setProduct(product);
            });
        }catch (Exception e){
            throw new ProductNotFoundExceptions("ProductItem " + productId + " Not Found");
        }
        return byProductId.stream()
                .map(productItems -> productItemsMapper.fromProductItems(productItems))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductItems> findAllProductItems() {
        List<ProductItems> productItems = productItemRepository.findAll();
        List<Order> order = orderRepository.findAll();
        productItems.forEach(pr -> {
            Product product = productRestClient.findProductById(pr.getProductId());
            pr.setProduct(product);
            //pr.setOrder();
        });
        if (productItems.isEmpty())
            throw new ProductItemNotFoundExceptions("No ProductItem exist");
        return productItems;
    }

    @Override
    public ProductItems addProductItem(ProductItems productItems, Order order) {
        log.info(String.format("ProductItem {} is saved => %s ", productItems.toString()));
        Product product = null;
        try {
            product = productRestClient.findProductById(productItems.getProductId());
        }catch (Exception e){
            throw new ProductNotFoundExceptions("Product " +productItems.getProductId() + " not found");
        }
        orderRepository.findById(order.getId()).orElse(null);
        if (order == null){
            throw new OrderNotFoundException("Order Not Found");
        }
        productItems.setOrder(order);
        ProductItems savedProductItem = productItemRepository.save(productItems);
        savedProductItem.setProduct(product);
        return savedProductItem;
    }
}

