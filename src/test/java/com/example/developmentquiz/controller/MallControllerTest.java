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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MallControllerTest {
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
        cokeDto1 = ProductDto.builder().imgURL("http://n1.itc.cn/img8/wb/smccloud/2015/04/17/142923612268757202.JPEG").productName("可乐1").unitPrice(1).unitType("瓶").build();
        cokeDto2 = ProductDto.builder().imgURL("https://i02piccdn.sogoucdn.com/15ffc426ffc960c7").productName("可乐2").unitPrice(1).unitType("瓶").build();
        cokeDto3 = ProductDto.builder().imgURL("https://i03piccdn.sogoucdn.com/a38f3df819c84cc1").productName("可乐3").unitPrice(1).unitType("瓶").build();
        cokeDto4 = ProductDto.builder().imgURL("https://i01piccdn.sogoucdn.com/1e669bf655d63be0").productName("可乐4").unitPrice(1).unitType("瓶").build();
        cokeDto5 = ProductDto.builder().imgURL("https://i04piccdn.sogoucdn.com/85855f63759e2816").productName("可乐5").unitPrice(1).unitType("瓶").build();
        productRepository.save(cokeDto1);
        productRepository.save(cokeDto2);
        productRepository.save(cokeDto3);
        productRepository.save(cokeDto4);
//        orderGoodsDto = OrderGoodsDto.builder().amount(1).product(cokeDto1).build();
//        orderGoodsDto1 = OrderGoodsDto.builder().amount(1).product(cokeDto2).build();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void should_get_all_product_list() throws Exception {
        mockMvc.perform(get("/mall/product/list")).andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(5)))
                .andExpect(jsonPath("$[0].id",is(cokeDto1.getId())))
                .andExpect(jsonPath("$[0].productName",is(cokeDto1.getProductName())))
                .andExpect(jsonPath("$[0].unitPrice",is(cokeDto1.getUnitPrice())))
                .andExpect(jsonPath("$[0].imgURL",is(cokeDto1.getImgURL())));
    }

    @Test
    void should_add_product_success() throws Exception {
        String jsonProduct = new ObjectMapper().writeValueAsString(cokeDto5);
        mockMvc.perform(post("/mall/product/add")
                .content(jsonProduct)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        List<ProductDto> productDtoList = productRepository.findAll();
        assertEquals(5, productDtoList.size());
    }

    @Test
    void should_add_product_fail_when_product_exist() throws Exception {
        String jsonProduct = new ObjectMapper().writeValueAsString(cokeDto4);
        mockMvc.perform(post("/mall/product/add")
                .content(jsonProduct)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ifExist",is("existed")));
    }


}
