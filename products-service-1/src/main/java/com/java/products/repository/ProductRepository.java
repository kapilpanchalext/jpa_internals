package com.java.products.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.java.products.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID>{

}
