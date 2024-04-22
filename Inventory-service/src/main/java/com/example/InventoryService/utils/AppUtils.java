package com.example.InventoryService.utils;

import com.example.InventoryService.dto.ProductRequest;
import com.example.InventoryService.dto.ProductResponse;
import com.example.InventoryService.dto.VariantRequest;
import com.example.InventoryService.dto.VariantResponse;
import com.example.InventoryService.model.Product;
import com.example.InventoryService.model.Variant;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AppUtils {

    public static Product dtoToEntity(ProductRequest productRequest){

        Product product = Product.builder()
                .productId("ProductId-" + UUID.randomUUID().toString())
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .variants(productRequest.getVariants()
                        .stream()
                        .map(variantRequest ->
                            Variant.builder()
                                    .variantId("VariantId-" + UUID.randomUUID().toString())
                                    .variantName(variantRequest.getVariantName())
                                    .specifications(variantRequest.getSpecifications())
                                    .quantity(variantRequest.getQuantity())
                                    .rating(0.0d)
                                    .avalibility(true)
                                    .build())
                        .collect(Collectors.toList()))
                .build();
        BeanUtils.copyProperties(productRequest, product);
        return product;
    }


    public static ProductResponse entityToDto(Product product){

        ProductResponse productResponse = ProductResponse.builder()
                .variants(product.getVariants()
                        .stream()
                        .map(variant ->
                          VariantResponse.builder()
                                    .variantId(variant.getVariantId())
                                    .variantName(variant.getVariantName())
                                    .availability(variant.isAvalibility())
                                    .specifications(variant.getSpecifications())
                                    .quantity(variant.getQuantity())
                                    .rating(variant.getRating())
                                    .build())
                        .collect(Collectors.toList()))
                .build();
        BeanUtils.copyProperties(product, productResponse);
        return productResponse;
    }

//    public static
}
