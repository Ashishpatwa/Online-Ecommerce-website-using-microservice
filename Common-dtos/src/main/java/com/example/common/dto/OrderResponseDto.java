package com.example.common.dto;

import com.example.common.enums.OrderStatus;
import com.example.common.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {

    private String orderId;
    private String userId;
    private Integer totalAmount;
    private List<OrderItemsListDto> orderRequestItemsListDto;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private LocalDateTime orderDate;

}
