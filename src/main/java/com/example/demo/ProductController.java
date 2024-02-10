package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseObject createProduct(@RequestBody Product product) {
        ResponseObject responseObject  =  new ResponseObject();
        try {
            Product createdProduct = productService.createProduct(product);
            responseObject.setCode(HttpStatus.CREATED);
            responseObject.setMessage("Successfully created");
            responseObject.setProduct(createdProduct);
            responseObject.setStatusCode(201);
            ObjectMapper objectMapper = new ObjectMapper();
            String responseObjectJson = objectMapper.writeValueAsString(responseObject);
            System.out.println("Response Object: " + responseObjectJson);
        } catch (Exception ex) {
            responseObject.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseObject.setMessage(ex.getMessage());
            responseObject.setStatusCode(500);
        }
        return responseObject;
    }

    @GetMapping("/{productId}")
    public ResponseObject readProduct(@PathVariable Long productId) {
        ResponseObject responseObject  =  new ResponseObject();
        try {
            Product product = productService.readProduct(productId);
            if (product != null) {
                responseObject.setCode(HttpStatus.FOUND);
                responseObject.setMessage("Success");
                responseObject.setProduct(product);
                responseObject.setStatusCode(302);
                ObjectMapper objectMapper = new ObjectMapper();
                String responseObjectJson = objectMapper.writeValueAsString(responseObject);
                System.out.println("Response Object: " + responseObjectJson);
            } else {
                responseObject.setCode(HttpStatus.NOT_FOUND);
                responseObject.setMessage("Data not found");
                responseObject.setStatusCode(404);
                ObjectMapper objectMapper = new ObjectMapper();
                String responseObjectJson = objectMapper.writeValueAsString(responseObject);
                System.out.println("Response Object: " + responseObjectJson);
            }
        } catch (Exception ex) {
            responseObject.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseObject.setMessage(ex.getMessage());
            responseObject.setStatusCode(500);
        }
        return responseObject;
    }

    @PutMapping("/{productId}")
    public ResponseObject updateProduct(@PathVariable Long productId, @RequestBody Product productDetails) {
        ResponseObject responseObject  =  new ResponseObject();
        try {
            Product updatedProduct = productService.updateProduct(productId, productDetails);
            if (updatedProduct != null) {
                responseObject.setCode(HttpStatus.CREATED);
                responseObject.setMessage("Successfully Updated");
                responseObject.setProduct(updatedProduct);
                responseObject.setStatusCode(201);
                ObjectMapper objectMapper = new ObjectMapper();
                String responseObjectJson = objectMapper.writeValueAsString(responseObject);
                System.out.println("Response Object: " + responseObjectJson);
            } else {
                responseObject.setCode(HttpStatus.NOT_FOUND);
                responseObject.setMessage("Data not found");
                responseObject.setStatusCode(404);
                ObjectMapper objectMapper = new ObjectMapper();
                String responseObjectJson = objectMapper.writeValueAsString(responseObject);
                System.out.println("Response Object: " + responseObjectJson);
            }
        } catch (Exception ex) {
            responseObject.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseObject.setMessage(ex.getMessage());
            responseObject.setStatusCode(500);
        }
        return responseObject;
    }

    @DeleteMapping("/{productId}")
    public ResponseObject deleteProduct(@PathVariable Long productId) {
        ResponseObject responseObject  =  new ResponseObject();
        try {
            boolean deleted = productService.deleteProduct(productId);
            if (deleted) {
                responseObject.setCode(HttpStatus.OK);
                responseObject.setMessage("Successfully Deleted");
                responseObject.setStatusCode(200);
                ObjectMapper objectMapper = new ObjectMapper();
                String responseObjectJson = objectMapper.writeValueAsString(responseObject);
                System.out.println("Response Object: " + responseObjectJson);
            } else {
                responseObject.setCode(HttpStatus.NOT_FOUND);
                responseObject.setMessage("Data not found");
                responseObject.setStatusCode(404);
                ObjectMapper objectMapper = new ObjectMapper();
                String responseObjectJson = objectMapper.writeValueAsString(responseObject);
                System.out.println("Response Object: " + responseObjectJson);
            }
        } catch (Exception ex) {
            responseObject.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseObject.setMessage(ex.getMessage());
            responseObject.setStatusCode(500);
        }
        return responseObject;
    }

    @PutMapping("/{productId}/apply-discount")
    public ResponseObject applyDiscount(@PathVariable Long productId, @RequestParam("discount") double discountPercentage) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Product updatedProduct = productService.applyDiscount(productId, discountPercentage);
            if (updatedProduct != null) {
                responseObject.setCode(HttpStatus.OK);
                responseObject.setMessage("Discount Applied Successfully");
                responseObject.setProduct(updatedProduct);
                responseObject.setStatusCode(200);
                ObjectMapper objectMapper = new ObjectMapper();
                String responseObjectJson = objectMapper.writeValueAsString(responseObject);
                System.out.println("Response Object: " + responseObjectJson);
            } else {
                responseObject.setCode(HttpStatus.NOT_FOUND);
                responseObject.setMessage("Product not found");
                responseObject.setStatusCode(404);
                ObjectMapper objectMapper = new ObjectMapper();
                String responseObjectJson = objectMapper.writeValueAsString(responseObject);
                System.out.println("Response Object: " + responseObjectJson);
            }
        } catch (Exception ex) {
            responseObject.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseObject.setMessage(ex.getMessage());
        }
        return responseObject;
    }

    @PutMapping("/{productId}/apply-tax")
    public ResponseObject applyTax(@PathVariable Long productId, @RequestParam("tax") double taxRate) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Product updatedProduct = productService.applyTax(productId, taxRate);
            if (updatedProduct != null) {
                responseObject.setCode(HttpStatus.OK);
                responseObject.setMessage("Tax Applied Successfully");
                responseObject.setProduct(updatedProduct);
                responseObject.setStatusCode(200);
                ObjectMapper objectMapper = new ObjectMapper();
                String responseObjectJson = objectMapper.writeValueAsString(responseObject);
                System.out.println("Response Object: " + responseObjectJson);
            } else {
                responseObject.setCode(HttpStatus.NOT_FOUND);
                responseObject.setMessage("Product not found");
                responseObject.setStatusCode(404);
                ObjectMapper objectMapper = new ObjectMapper();
                String responseObjectJson = objectMapper.writeValueAsString(responseObject);
                System.out.println("Response Object: " + responseObjectJson);
            }
        } catch (Exception ex) {
            responseObject.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseObject.setMessage(ex.getMessage());
        }
        return responseObject;
    }
}
