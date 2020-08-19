package com.example.developmentquiz.repository;

import com.example.developmentquiz.dto.OrderGoodsDto;
import com.example.developmentquiz.dto.ProductDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderGoodsDto,Integer> {
    @Override
    List<OrderGoodsDto> findAll();
    OrderGoodsDto findByProductId(int productId);
}
