package com.example.developmentquiz.controller;

import com.example.developmentquiz.domain.OrderGoods;
import com.example.developmentquiz.dto.OrderGoodsDto;
import com.example.developmentquiz.dto.ProductDto;
import com.example.developmentquiz.repository.OrderRepository;
import com.example.developmentquiz.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    private List<OrderGoods> orderList;

    @GetMapping("/order/add")
    public ResponseEntity addProductToOrder(@RequestParam int productId){
        Optional<ProductDto> productDto = productRepository.findById(productId);
        if (!productDto.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        OrderGoodsDto orderHasThisProduct =orderRepository.findByProductId(productId);
        if (orderHasThisProduct == null){
            OrderGoodsDto orderGoodsDto = OrderGoodsDto.builder()
                    .amount(1)
                    .product(productDto.get())
                    .build();
            orderRepository.save(orderGoodsDto);
        } else {
            orderHasThisProduct.setAmount(orderHasThisProduct.getAmount()+1);
            orderRepository.save(orderHasThisProduct);
        }
        return ResponseEntity.created(null).build();


    }

    @GetMapping("/order/list")
    public ResponseEntity getOrderList(){
        orderList = orderRepository.findAll().stream().map(
                order -> OrderGoods.builder()
                        .amount(order.getAmount())
                        .productName(order.getProduct().getProductName())
                        .unitPrice(order.getProduct().getUnitPrice())
                        .unitType(order.getProduct().getUnitType())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderList);
    }
}
