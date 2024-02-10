package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product testProduct;

    @Before
    public void setUp() {
        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setDescription("Test Description");
        testProduct.setPrice(100.0);
        testProduct.setQuantityAvailable(10);
    }

    @Test
    public void testCreateProduct() {
        when(productService.createProduct(any())).thenReturn(testProduct);

        ResponseObject responseEntity = productController.createProduct(testProduct);

        assertEquals(HttpStatus.CREATED, responseEntity.getCode());
        assertEquals("Successfully created", responseEntity.getMessage());
        assertEquals(testProduct, responseEntity.getProduct());
    }

    @Test
    public void testReadProduct() {
        when(productService.readProduct(1L)).thenReturn(testProduct);

        ResponseObject responseEntity = productController.readProduct(1L);

        assertEquals(HttpStatus.FOUND, responseEntity.getCode());
        assertEquals("Success", responseEntity.getMessage());
        assertEquals(testProduct, responseEntity.getProduct());
    }

    @Test
    public void testUpdateProduct() {
        when(productService.updateProduct(1L, testProduct)).thenReturn(testProduct);

        ResponseObject responseEntity = productController.updateProduct(1L, testProduct);

        assertEquals(HttpStatus.CREATED, responseEntity.getCode());
        assertEquals("Successfully Updated", responseEntity.getMessage());
        assertEquals(testProduct, responseEntity.getProduct());
    }

    @Test
    public void testDeleteProduct() {
        when(productService.deleteProduct(1L)).thenReturn(true);

        ResponseObject responseEntity = productController.deleteProduct(1L);

        assertEquals(HttpStatus.OK, responseEntity.getCode());
        assertEquals("Successfully Deleted", responseEntity.getMessage());
    }

    @Test
    public void testApplyDiscount() {
        double discountPercentage = 10.0;
        when(productService.applyDiscount(1L, discountPercentage)).thenReturn(testProduct);

        ResponseObject responseEntity = productController.applyDiscount(1L, discountPercentage);

        assertEquals(HttpStatus.OK, responseEntity.getCode());
        assertEquals("Discount Applied Successfully", responseEntity.getMessage());
        assertEquals(testProduct, responseEntity.getProduct());
    }

    @Test
    public void testApplyTax() {
        double taxRate = 5.0;
        when(productService.applyTax(1L, taxRate)).thenReturn(testProduct);

        ResponseObject responseEntity = productController.applyTax(1L, taxRate);

        assertEquals(HttpStatus.OK, responseEntity.getCode());
        assertEquals("Tax Applied Successfully", responseEntity.getMessage());
        assertEquals(testProduct, responseEntity.getProduct());
    }
}

