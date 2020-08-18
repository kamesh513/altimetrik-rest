package com.altimetrik.rest.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.rest.api.exception.ResourceNotFoundException;
import com.altimetrik.rest.api.model.Product;
import com.altimetrik.rest.api.repository.ProductRepository;

@RequestMapping("/product")
@RestController
public class ProductController {
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("/all")
	public List<Product> getAllNotes() {
	    return productRepo.findAll();
	}
	
	@PostMapping("/")
	public Product createProduct(@Valid @RequestBody Product product) {
	    return productRepo.save(product);
	}
	
	@GetMapping("/notes/{id}")
	public Product getProductById(@PathVariable(value = "id") Long productId) {
	    return productRepo.findById(productId)
	            .orElseThrow(() -> new ResourceNotFoundException("Product is not found for given productId", "productId", productId));
	}

	@PutMapping("{productId}")
	public Product updateProduct(@PathVariable(value = "productId") Long productId,
	                                        @Valid @RequestBody Product productDetails) {

	    Product product = productRepo.findById(productId)
	            .orElseThrow(() -> new ResourceNotFoundException("Product is not found for given productId, while updating the product", "productId", productId));

	    product.setName(productDetails.getName());
	    product.setBrand(productDetails.getBrand());
	    product.setMadein(productDetails.getMadein());
	    product.setPrice(productDetails.getPrice());
	    
	    Product updatedProduct = productRepo.save(product);
	    return updatedProduct;
	}
	
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long productId) {
		Product product = productRepo.findById(productId)
	            .orElseThrow(() -> new ResourceNotFoundException("Product is not found for given productId, while deleting the product", "productId", productId));

		productRepo.delete(product);

	    return ResponseEntity.ok().build();
	}
	
}
