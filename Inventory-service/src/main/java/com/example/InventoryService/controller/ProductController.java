package com.example.InventoryService.controller;

import com.example.InventoryService.dto.ProductRequest;
import com.example.InventoryService.dto.ProductResponse;
import com.example.InventoryService.model.Product;
import com.example.InventoryService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/inventory")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/insert-product")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<ProductResponse> insertProduct(@RequestBody Mono<ProductRequest> productRequest){
		return productService.insertProduct(productRequest);
	}

	@PutMapping("/increase-variant-quantity/{productId}/{variantId}/{count}")
	@ResponseStatus(HttpStatus.OK)
	public  Mono<ProductResponse> increaseVariantQuantity(@PathVariable("productId") String productId, @PathVariable("variantId") String variantId, @PathVariable("count") Integer quantity) {
		return productService.increaseVariantQuantity(productId,variantId, quantity);
	}

	
	@PutMapping("/decrease-variant-quantity/{productId}/{variantId}/{count}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<ProductResponse> decreaseVariantQuantity(@PathVariable("productId") String productId, @PathVariable("variantId") String variantId, @PathVariable("count") Integer quantity){
		
		return productService.decreaseVariantQuantity(productId, variantId, quantity);

	}
	

}
