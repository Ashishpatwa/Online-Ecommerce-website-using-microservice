package com.example.InventoryService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String productName;
    private Double orginalPrice;
    private String categoryName;
    private String brandName;
    private List<VariantRequest> variants;

}
