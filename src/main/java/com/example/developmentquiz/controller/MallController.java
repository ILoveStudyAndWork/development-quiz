package com.example.developmentquiz.controller;

import com.example.developmentquiz.domain.Product;
import com.example.developmentquiz.dto.ProductDto;
import com.example.developmentquiz.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class MallController {

    MallController(ProductRepository productRepository){
        ProductDto cokeDto1;
        ProductDto cokeDto2;
        ProductDto cokeDto3;
        ProductDto cokeDto4;
        ProductDto cokeDto5;
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
    }
    @Autowired
    ProductRepository productRepository;
    List<ProductDto> productDtoList;

    @GetMapping("/mall/product/list")
    public ResponseEntity getProductList(){
        productDtoList = productRepository.findAll();
        return ResponseEntity.ok(productDtoList);
    }

    @PostMapping("/mall/product/add")
    public ResponseEntity getProductList(@RequestBody Product product){
        Boolean ifExist = productRepository.existsByProductName(product.getProductName());
        if (ifExist){
            return ResponseEntity.ok().body("{\"ifExist\":\"existed\"}");
        }else {
            ProductDto productToBeAdd = ProductDto.builder()
                    .productName(product.getProductName())
                    .unitType(product.getUnitType())
                    .unitPrice(product.getUnitPrice())
                    .imgURL(product.getImgURL())
                    .build();
            productRepository.save(productToBeAdd);
            return ResponseEntity.created(null).build();
        }

    }




}
