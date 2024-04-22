package com.example.OrderService.config;

import com.example.common.event.PaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class PaymentConsumerConfig {

    @Autowired
    private OrderStatusUpdateHandler handler;

    @Bean
    public Consumer<PaymentEvent> paymentEventConsumer(){
        return paymentEvent -> handler.updateOrder(paymentEvent.getPaymentRequestDto().getOrderId(), po -> po.setPaymentStatus(paymentEvent.getPaymentStatus()));
    }

}
