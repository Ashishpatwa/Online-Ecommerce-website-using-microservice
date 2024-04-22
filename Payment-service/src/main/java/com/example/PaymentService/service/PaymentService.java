package com.example.PaymentService.service;

import com.example.PaymentService.model.UserBalance;
import com.example.PaymentService.model.UserTransaction;
import com.example.PaymentService.repository.UserBalanceRepository;
import com.example.PaymentService.repository.UserTransactionRepository;
import com.example.common.dto.OrderRequestDto;
import com.example.common.dto.PaymentRequestDto;
import com.example.common.enums.PaymentStatus;
import com.example.common.event.OrderEvent;
import com.example.common.event.PaymentEvent;
//import jakarta.annotation.PostConstruct;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PaymentService {

    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @Autowired
    private UserTransactionRepository userTransactionRepository;

    @Transactional
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {

        //validate userId
        //check user balance availability
        //If balance is sufficent then  payment complement and detect amount from DB.
        //If balance is not sufficent then cancel order Event and update the amount in DB.


        OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(orderRequestDto.getUserId(),
                orderRequestDto.getTotalAmount(), orderRequestDto.getOrderId());

        return this.userBalanceRepository.findById(orderRequestDto.getUserId())
                .filter(userBalance -> userBalance.getAmount() >= orderRequestDto.getTotalAmount())
                .map(userBalance -> {
                    userBalance.setAmount(userBalance.getAmount()- orderRequestDto.getTotalAmount());
                    userTransactionRepository.save(new UserTransaction(orderRequestDto.getOrderId(),
                            orderRequestDto.getUserId(), orderRequestDto.getTotalAmount()));
                    return new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_COMPLETED);
                }).orElse(new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_FAILED));


    }

    @PostConstruct
    public void initUserBalance(){
        this.userBalanceRepository.saveAll(Stream.of(new UserBalance("101",1000),
                new UserBalance("102", 2000),
                new UserBalance("103", 3000)).collect(Collectors.toList()));
    }


    @Transactional
    public void cancelOrderEvent(OrderEvent orderEvent) {

        userTransactionRepository.findById(orderEvent.getOrderRequestDto().getUserId())
                .ifPresent(ut -> {
                    userTransactionRepository.delete(ut);
                    userBalanceRepository.findById(ut.getUserId())
                            .ifPresent(ub -> ub.setAmount(ub.getAmount() + ut.getAmount()));
                });
    }
}
