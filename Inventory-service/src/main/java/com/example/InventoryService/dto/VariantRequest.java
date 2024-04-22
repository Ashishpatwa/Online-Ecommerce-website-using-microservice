package com.example.InventoryService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VariantRequest {
    private String variantName;
    private Integer quantity;
    private Map<String, Object> specifications;
}
