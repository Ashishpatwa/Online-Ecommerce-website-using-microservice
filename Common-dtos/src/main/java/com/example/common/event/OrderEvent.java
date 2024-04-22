package com.example.common.event;

import com.example.common.dto.OrderRequestDto;
import com.example.common.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class OrderEvent implements  Event{

    private String eventId = UUID.randomUUID().toString();
    private Date date = new Date();
    private OrderRequestDto orderRequestDto;
    private OrderStatus orderStatus;

    @Override
    public String eventId() {
        return eventId;
    }
    @Override
    public Date date() {
        return date;
    }

    public OrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
        this.orderRequestDto = orderRequestDto;
        this.orderStatus = orderStatus;
    }
}
