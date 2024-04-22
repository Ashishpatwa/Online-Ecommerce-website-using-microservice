package com.example.InventoryService.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
	
	@Id
	private String productId;
	private String productName;
	private Double orginalPrice;
	private String brandName;
	private String categoryName;
	private List<Variant> variants;
	private LocalDateTime updateDate;
	private LocalDateTime createDate;
	private String sellerId;

}
