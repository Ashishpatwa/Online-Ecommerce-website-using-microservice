package com.example.InventoryService.model;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Variant {
	
	private String variantId;
	private String variantName;
	private Integer quantity;
	private Map<String, Object> specifications;
	private boolean avalibility;
	private Double rating;

}
