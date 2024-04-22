package com.example.OrderService.utils;

import com.example.OrderService.model.OrderItemsList;
import com.example.OrderService.model.PurchaseOrder;
import com.example.common.dto.OrderItemsListDto;
import com.example.common.dto.OrderRequestDto;
import com.example.common.dto.OrderResponseDto;
import com.example.common.enums.OrderStatus;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

public class AppUtils {

    public static OrderResponseDto entityToDto(PurchaseOrder order) {
        OrderResponseDto orderRequestDto = OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .userId(order.getUserId())
                .orderStatus(order.getOrderStatus())
                .totalAmount(order.getTotalAmount())
                .orderDate(order.getOrderDate())
                .orderRequestItemsListDto(order.getOrderItemsLists()
                        .stream()
                        .map(orderItemsList -> OrderItemsListDto.builder()
                                .quantity(orderItemsList.getQuantity())
                                .variantName(orderItemsList.getVariantName())
                                .productId(orderItemsList.getProductId())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        return orderRequestDto;
    }

    public static PurchaseOrder dtoToEntity(OrderRequestDto orderRequestDto){

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .orderId(orderRequestDto.getOrderId())
                .userId(orderRequestDto.getUserId())
                .orderStatus(OrderStatus.ORDER_CREATED)
                .orderDate(LocalDateTime.now())
                .totalAmount(orderRequestDto.getTotalAmount())
                .orderItemsLists(orderRequestDto.getOrderRequestItemsListDto()
                        .stream()
                        .map(orderItemsListDto -> OrderItemsList.builder()
                                .variantName(orderItemsListDto.getVariantName())
                                .quantity(orderItemsListDto.getQuantity())
                                .productId(orderItemsListDto.getProductId())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        return purchaseOrder;
    }


}
