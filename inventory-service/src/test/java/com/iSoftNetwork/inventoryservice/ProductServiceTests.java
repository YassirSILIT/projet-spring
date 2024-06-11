package com.iSoftNetwork.inventoryservice;

import com.iSoftNetwork.inventoryservice.dto.CountName;
import com.iSoftNetwork.inventoryservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
@SpringBootTest
public class ProductServiceTests {
    @Autowired
    private ProductService productService;

    @Test
    public void testGetPercentageGroupByName() {
        List<CountName> result = productService.getPercentageGroupByName();
        assertThat(result).isNotNull();
        assertThat(result.size()).isGreaterThan(0);
    }
}
