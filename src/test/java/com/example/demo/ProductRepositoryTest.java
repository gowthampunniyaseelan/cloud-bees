package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(100.0);
        product.setQuantityAvailable(10);

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct);
        assertNotNull(savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getDescription(), savedProduct.getDescription());
        assertEquals(product.getPrice(), savedProduct.getPrice(), 0.001);
        assertEquals(product.getQuantityAvailable(), savedProduct.getQuantityAvailable());
    }

    @Test
    public void testFindProductById() {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(100.0);
        product.setQuantityAvailable(10);

        Product savedProduct = productRepository.save(product);
        Long productId = savedProduct.getId();

        Optional<Product> optionalProduct = productRepository.findById(productId);

        assertTrue(optionalProduct.isPresent());
        Product retrievedProduct = optionalProduct.get();
        assertEquals(savedProduct, retrievedProduct);
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(100.0);
        product.setQuantityAvailable(10);

        Product savedProduct = productRepository.save(product);
        Long productId = savedProduct.getId();

        productRepository.deleteById(productId);

        Optional<Product> optionalProduct = productRepository.findById(productId);
        assertFalse(optionalProduct.isPresent());
    }
}

