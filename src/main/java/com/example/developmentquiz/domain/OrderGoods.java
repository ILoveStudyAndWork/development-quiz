package com.example.developmentquiz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderGoods {
    private int amount;
    private String productName;
    private float unitPrice;
    private String unitType;
}
