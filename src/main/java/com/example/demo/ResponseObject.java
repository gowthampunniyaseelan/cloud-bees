package com.example.demo;

import org.springframework.http.HttpStatus;

public class ResponseObject {
  private HttpStatus code;
  private String message;
  private Product product;
  private int statusCode;
  
  public HttpStatus getCode() {
    return code;
  }
  public void setCode(HttpStatus code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public Product getProduct() {
    return product;
  }
  public void setProduct(Product product) {
    this.product = product;
  }
  public int getStatusCode() {
    return statusCode;
  }
  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  
}
