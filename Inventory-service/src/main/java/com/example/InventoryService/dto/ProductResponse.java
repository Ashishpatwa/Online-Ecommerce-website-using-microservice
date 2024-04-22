package com.example.InventoryService.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private String productId;
    private String productName;
    private Double orginalPrice;
    private String categoryName;
    private String brandName;
    private List<VariantResponse> variants;

}
