package com.altimetrik.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altimetrik.rest.api.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	 
}