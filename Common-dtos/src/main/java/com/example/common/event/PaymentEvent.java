package com.example.common.event;

import com.example.common.dto.OrderRequestDto;
import com.example.common.dto.PaymentRequestDto;
import com.example.common.enums.OrderStatus;
import com.example.common.enums.PaymentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PaymentEvent implements Event{
    private String eventId = UUID.randomUUID().toString();
    private Date date = new Date();
    private PaymentRequestDto paymentRequestDto;
    private PaymentStatus paymentStatus;

    @Override
    public String eventId() {
        return eventId;
    }
    @Override
    public Date date() {
        return date;
    }

    public PaymentEvent(PaymentRequestDto paymentRequestDto, PaymentStatus paymentStatus) {
        this.paymentRequestDto = paymentRequestDto;
        this.paymentStatus = paymentStatus;
    }
}
