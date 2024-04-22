package com.example.InventoryService.service;

import com.example.InventoryService.dto.ProductRequest;
import com.example.InventoryService.dto.ProductResponse;
import com.example.InventoryService.model.Product;
import com.example.InventoryService.model.Variant;
import reactor.core.publisher.Mono;

public interface ProductService {
	
	Mono<ProductResponse> insertProduct(Mono<ProductRequest> product);
	Mono<ProductResponse> increaseVariantQuantity(String productId, String variantId, Integer count);
	Mono<ProductResponse> decreaseVariantQuantity(String productId, String variantId, Integer count);

}
