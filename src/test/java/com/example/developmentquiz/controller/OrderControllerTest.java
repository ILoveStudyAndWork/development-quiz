package com.example.developmentquiz.controller;

import com.example.developmentquiz.dto.OrderGoodsDto;
import com.example.developmentquiz.dto.ProductDto;
import com.example.developmentquiz.repository.OrderRepository;
import com.example.developmentquiz.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    ProductDto cokeDto1;
    ProductDto cokeDto2;
    ProductDto cokeDto3;
    ProductDto cokeDto4;
    ProductDto cokeDto5;
    OrderGoodsDto orderGoodsDto;
    OrderGoodsDto orderGoodsDto1;
    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        orderRepository.deleteAll();
        cokeDto1 = ProductDto.builder().imgURL("http://n1.itc.cn/img8/wb/smccloud/2015/04/17/142923612268757202.JPEG").productName("可乐1").unitPrice(1).unitType("瓶").build();
        cokeDto2 = ProductDto.builder().imgURL("https://i02piccdn.sogoucdn.com/15ffc426ffc960c7").productName("可乐2").unitPrice(1).unitType("瓶").build();
        cokeDto3 = ProductDto.builder().imgURL("https://i03piccdn.sogoucdn.com/a38f3df819c84cc1").productName("可乐3").unitPrice(1).unitType("瓶").build();
        cokeDto4 = ProductDto.builder().imgURL("https://i01piccdn.sogoucdn.com/1e669bf655d63be0").productName("可乐4").unitPrice(1).unitType("瓶").build();
        cokeDto5 = ProductDto.builder().imgURL("https://i04piccdn.sogoucdn.com/85855f63759e2816").productName("可乐5").unitPrice(1).unitType("瓶").build();
        productRepository.save(cokeDto1);
        productRepository.save(cokeDto2);
        productRepository.save(cokeDto3);
        productRepository.save(cokeDto4);
        productRepository.save(cokeDto5);
        orderGoodsDto = OrderGoodsDto.builder().amount(1).product(cokeDto1).build();
//        orderGoodsDto1 = OrderGoodsDto.builder().amount(1).product(cokeDto2).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void should_add_new_order_to_order_list_when_product_no_exist() throws Exception {
        orderRepository.save(orderGoodsDto);
        int productId = cokeDto2.getId();
        mockMvc.perform(get("/order/add?productId="+productId))
                .andExpect(status().isCreated());

        List<OrderGoodsDto> orderGoodsDtoList = orderRepository.findAll();
        assertEquals(2,orderGoodsDtoList.size());
        assertEquals("可乐2",orderGoodsDtoList.get(1).getProduct().getProductName());
        assertEquals(1,orderGoodsDtoList.get(0).getAmount());
    }

    @Test
    void should_update_order_when_product_exist() throws Exception {
        orderRepository.save(orderGoodsDto);
        int productId = cokeDto1.getId();
        mockMvc.perform(get("/order/add?productId="+productId))
                .andExpect(status().isCreated());
        List<OrderGoodsDto> orderGoodsDtoList = orderRepository.findAll();
        assertEquals(1,orderGoodsDtoList.size());
        assertEquals("可乐1",orderGoodsDtoList.get(0).getProduct().getProductName());
        assertEquals(2,orderGoodsDtoList.get(0).getAmount());
    }
}
