package com.example.developmentquiz.repository;

import com.example.developmentquiz.dto.ProductDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductDto,Integer> {
    @Override
    List<ProductDto> findAll();
}
