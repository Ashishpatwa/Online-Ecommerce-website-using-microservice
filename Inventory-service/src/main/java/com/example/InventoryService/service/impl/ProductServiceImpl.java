package com.example.InventoryService.service.impl;

import com.example.InventoryService.dto.ProductRequest;
import com.example.InventoryService.dto.ProductResponse;
import com.example.InventoryService.exception.InsufficentProductQuantity;
import com.example.InventoryService.model.Product;
import com.example.InventoryService.model.Variant;
import com.example.InventoryService.repository.ProductRepository;
import com.example.InventoryService.service.ProductService;
import com.example.InventoryService.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	@Override
	public Mono<ProductResponse> insertProduct(Mono<ProductRequest> productRequest) {

		return productRequest.map(AppUtils::dtoToEntity)
				.flatMap(entity -> productRepository.save(entity))
				.map(AppUtils::entityToDto);

	}

	@Override
	public Mono<ProductResponse> increaseVariantQuantity(String productId, String variantId, Integer quantity) {


		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(productId).and("variants.variantId").is(variantId));

		Update update = new Update();
		update.inc("variants.$.quantity", quantity);

		return mongoTemplate.findAndModify(query, update, Product.class)
				.map(AppUtils::entityToDto);
	}

		@Override
	public Mono<ProductResponse> decreaseVariantQuantity(String productId, String variantId, Integer quantity) {

		Query query = new Query();
		query.addCriteria(Criteria
				.where("_id").is(productId)
				.and("variants.variantId").is(variantId)
				.and("variants.quantity").gte(quantity));


		Update update = new Update();
		update.inc("variants.$.quantity", -quantity);

		Mono<Product> decreaseVariantItem = mongoTemplate.findAndModify(query, update, Product.class);
		return decreaseVariantItem
				.switchIfEmpty(Mono.error(new InsufficentProductQuantity("Insufficent Product or variant Quantity")))
				.map(AppUtils::entityToDto);

	}

}
