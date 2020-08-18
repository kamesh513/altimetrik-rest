package com.altimetrik.rest.api.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.altimetrik.rest.api.helper.ExcelHelper;
import com.altimetrik.rest.api.model.Product;
import com.altimetrik.rest.api.repository.ProductRepository;

@Service
public class ExcelService {
	
  @Autowired
  ProductRepository repository;

  public void save(MultipartFile file) {
    try {
      List<Product> products = ExcelHelper.excelToTutorials(file.getInputStream());
      repository.saveAll(products);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  public List<Product> getAllTutorials() {
    return repository.findAll();
  }
}
