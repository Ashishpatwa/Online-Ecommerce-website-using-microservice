package com.example.OrderService.service;

import com.example.common.dto.OrderRequestDto;
import com.example.common.enums.OrderStatus;
import com.example.common.event.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

    //Sinks is a publisher which Help to emit the event
    @Autowired
    private Sinks.Many<OrderEvent> orderSinks;

    public void publishOrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus){
        OrderEvent orderEvent = new OrderEvent(orderRequestDto, orderStatus);
        Sinks.EmitResult result = orderSinks.tryEmitNext(orderEvent);
        System.out.println("---------------result--------------" + result);
    }

}
