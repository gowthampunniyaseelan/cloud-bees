package com.example.demo;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product testProduct;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setDescription("Test Description");
        testProduct.setPrice(100.0);
        testProduct.setQuantityAvailable(10);
    }

    @Test
    public void testCreateProduct() {
        when(productRepository.save(any())).thenReturn(testProduct);
        Product createdProduct = productService.createProduct(testProduct);
        assertEquals(testProduct, createdProduct);
    }

    @Test
    public void testReadProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        Product retrievedProduct = productService.readProduct(1L);
        assertEquals(testProduct, retrievedProduct);
    }

    @Test
    public void testUpdateProduct() {
        when(productRepository.findById(anyLong())).thenAnswer(invocation -> {
            Long productId = invocation.getArgument(0);
            if (productId.equals(1L)) {
                return Optional.of(testProduct);
            } else {
                return Optional.empty();
            }
        });

        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product savedProduct = invocation.getArgument(0);
            return savedProduct;
        });
        testProduct.setName("update test");
        testProduct.setDescription("update description");
        testProduct.setPrice(99.00);
        testProduct.setQuantityAvailable(20);
        Product updatedProduct = productService.updateProduct(1L, testProduct);
        assertEquals(testProduct, updatedProduct);
    }

    @Test
    public void testDeleteProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        boolean deleted = productService.deleteProduct(1L);
        assertEquals(true, deleted);
        verify(productRepository, times(1)).delete(testProduct);
    }

     @Test
    public void testApplyDiscount() {
        double discountPercentage = 10.0;
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        Product updatedProduct = productService.applyDiscount(1L, discountPercentage);

        double expectedDiscountedPrice = 90.0;
        assertEquals(expectedDiscountedPrice, updatedProduct.getPrice(), 0.01);
    }

    @Test
    public void testApplyTax() {
        double taxRate = 5.0;
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        Product updatedProduct = productService.applyTax(1L, taxRate);

        double expectedTaxedPrice = 105.0;
        assertEquals(expectedTaxedPrice, updatedProduct.getPrice(), 0.01);
    }
}
