package com.java.products.service;

import com.java.products.model.CreateProductRestModel;

public interface ProductService {

	String createProduct(CreateProductRestModel productRestModel) throws Exception ;
}