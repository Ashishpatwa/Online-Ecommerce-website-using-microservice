package com.example.PaymentService.config;

import com.example.PaymentService.service.PaymentService;
import com.example.common.enums.OrderStatus;
import com.example.common.event.OrderEvent;
import com.example.common.event.PaymentEvent;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentConsumerConfig {

    @Autowired
    private PaymentService paymentService;

    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor(){
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {
        if(OrderStatus.ORDER_CREATED == orderEvent.getOrderStatus())
        {
            return Mono.fromSupplier(()->paymentService.newOrderEvent(orderEvent));
        }
        else {
            return Mono.fromRunnable(()->paymentService.cancelOrderEvent(orderEvent));
        }
    }

}
