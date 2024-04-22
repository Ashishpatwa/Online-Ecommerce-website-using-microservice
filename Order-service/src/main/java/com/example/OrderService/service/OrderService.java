package com.example.OrderService.service;

import com.example.OrderService.model.PurchaseOrder;
import com.example.OrderService.repository.OrderRepository;
import com.example.OrderService.utils.AppUtils;
import com.example.common.dto.OrderRequestDto;
import com.example.common.dto.OrderResponseDto;
import com.example.common.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import reactor.core.publisher.Mono;

@Service

public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusPublisher orderStatusPublisher;


    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {

        PurchaseOrder purchaseOrder = AppUtils.dtoToEntity(orderRequestDto);
        orderRepository.save(purchaseOrder);

        //Setting OrderId inside the OrderRequestDto so that entire transcation in a single track
        orderRequestDto.setUserId(purchaseOrder.getUserId());

        // produce kafka event with Status ORDER_CREATED and
        // publish the createOrder event to the Order-event topic.

        orderStatusPublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);

        return AppUtils.entityToDto(purchaseOrder);
    }
}