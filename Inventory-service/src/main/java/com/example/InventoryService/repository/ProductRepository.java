package com.example.InventoryService.repository;

import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.InventoryService.model.Product;
import com.example.InventoryService.model.Variant;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

//    @Query(value = "{'_id': ?0, 'variants.variantId': ?1}", fields="{'variants':0}")
//	Product increaseVariantQuantity(String productId, String variantId, Integer quantity);

}
