package com.example.developmentquiz.controller;

import com.example.developmentquiz.domain.Product;
import com.example.developmentquiz.dto.ProductDto;
import com.example.developmentquiz.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MallControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProductRepository productRepository;
    ProductDto cokeDto1;
    ProductDto cokeDto2;
    ProductDto cokeDto3;
    ProductDto cokeDto4;
    ProductDto cokeDto5;
    @BeforeEach
    void setUp() {
        cokeDto1 = ProductDto.builder().imgURL("http://n1.itc.cn/img8/wb/smccloud/2015/04/17/142923612268757202.JPEG").productName("可乐1").unitPrice(1).build();
        cokeDto2 = ProductDto.builder().imgURL("https://i02piccdn.sogoucdn.com/15ffc426ffc960c7").productName("可乐2").unitPrice(1).build();
        cokeDto3 = ProductDto.builder().imgURL("https://i03piccdn.sogoucdn.com/a38f3df819c84cc1").productName("可乐3").unitPrice(1).build();
        cokeDto4 = ProductDto.builder().imgURL("https://i01piccdn.sogoucdn.com/1e669bf655d63be0").productName("可乐4").unitPrice(1).build();
        cokeDto5 = ProductDto.builder().imgURL("https://i04piccdn.sogoucdn.com/85855f63759e2816").productName("可乐5").unitPrice(1).build();
        productRepository.save(cokeDto1);
        productRepository.save(cokeDto2);
        productRepository.save(cokeDto3);
        productRepository.save(cokeDto4);
        productRepository.save(cokeDto5);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void should_get_all_product_list() throws Exception {
        mockMvc.perform(get("/product/list")).andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(5)))
                .andExpect(jsonPath("$[0].id",is(cokeDto1.getId())))
                .andExpect(jsonPath("$[0].productName",is(cokeDto1.getProductName())))
                .andExpect(jsonPath("$[0].unitPrice",is(cokeDto1.getUnitPrice())))
                .andExpect(jsonPath("$[0].imgURL",is(cokeDto1.getImgURL())));


    }
}
