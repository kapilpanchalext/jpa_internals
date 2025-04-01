package com.java.producer.service;

import com.java.producer.model.CreateProductRestModel;

public interface ProductService {

	String createProduct(CreateProductRestModel productRestModel) throws Exception;
}