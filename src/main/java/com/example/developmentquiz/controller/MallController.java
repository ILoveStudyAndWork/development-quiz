package com.example.developmentquiz.controller;

import com.example.developmentquiz.dto.ProductDto;
import com.example.developmentquiz.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MallController {
    @Autowired
    ProductRepository productRepository;
    List<ProductDto> productDtoList;
    @GetMapping("/product/list")
    public ResponseEntity getProductList(){
        productDtoList = productRepository.findAll();
        return ResponseEntity.ok(productDtoList);
    }
}
