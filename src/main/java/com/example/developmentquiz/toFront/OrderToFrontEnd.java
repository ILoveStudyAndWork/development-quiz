package com.example.developmentquiz.toFront;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderToFrontEnd {
    private int id;
    private int amount;
    private String productName;
    private float unitPrice;
    private String unitType;
}
