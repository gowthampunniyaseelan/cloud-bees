package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

   public Product createProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to create product", ex);
        }
    }

    public Product readProduct(Long productId) {
        try {
            return productRepository.findById(productId).orElse(null);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to read product", ex);
        }
    }

    public Product updateProduct(Long productId, Product productDetails) {
        try {
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                product.setName(productDetails.getName());
                product.setDescription(productDetails.getDescription());
                product.setPrice(productDetails.getPrice());
                product.setQuantityAvailable(productDetails.getQuantityAvailable());
                return productRepository.save(product);
            }else{
                return null;
            }
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to update product", ex);
        }
    }

    public boolean deleteProduct(Long productId) {
        try {
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                productRepository.delete(product);
                return true;
            }
            return false;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to delete product", ex);
        }
    }

    public Product applyDiscount(Long productId, double discountPercentage) {
        try {
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                double price = product.getPrice();
                double discountedPrice = price - (price * discountPercentage / 100);
                product.setPrice(discountedPrice);
                return productRepository.save(product);
            }
            return null;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to apply discount to product", ex);
        }
    }

    public Product applyTax(Long productId, double taxRate) {
        try {
            Product product = productRepository.findById(productId).orElse(null);
            if (product != null) {
                double price = product.getPrice();
                double taxedPrice = price + (price * taxRate / 100);
                product.setPrice(taxedPrice);
                return productRepository.save(product);
            }
            return null;
        } catch (DataAccessException ex) {
            throw new RuntimeException("Failed to apply tax to product", ex);
        }
    }
}
