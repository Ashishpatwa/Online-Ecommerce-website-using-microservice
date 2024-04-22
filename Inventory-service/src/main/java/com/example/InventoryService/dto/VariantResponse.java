package com.example.InventoryService.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VariantResponse {
    private String variantId;
    private String variantName;
    private Integer quantity;
    private Map<String, Object> specifications;
    private boolean availability;
    private Double rating;
}
