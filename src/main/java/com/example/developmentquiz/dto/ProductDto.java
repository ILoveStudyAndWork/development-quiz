package com.example.developmentquiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class ProductDto {
    @Id
    @GeneratedValue
    private int id;
    private String imgURL;
    private String productName;
    private float unitPrice;
    private String unitType;
}
