package com.example.common.dto;

import com.example.common.enums.OrderStatus;
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
public class OrderRequestDto {

    private String orderId;
    private String userId;
    private Integer totalAmount;
    private List<OrderItemsListDto> orderRequestItemsListDto;

}
