package com.example.OrderService.config;

import com.example.common.event.OrderEvent;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class OrderPublisherConfig {

    //creat a Sink
    @Bean
    public Sinks.Many<OrderEvent> orderSinks(){

        return Sinks.many().multicast().onBackpressureBuffer();
    }

    //actual kafka publisher, u should spring cloud function for writing functional Style coding.
    @Bean
    public Supplier<Flux<OrderEvent>> orderSupplier(Sinks.Many<OrderEvent> sinks){

        return ()-> {
            Flux<OrderEvent> a = sinks.asFlux();
            System.out.println("*******************"+a);
            return a;
        };
        }


}
