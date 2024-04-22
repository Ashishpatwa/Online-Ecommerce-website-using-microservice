package com.example.PaymentService.controller;

import com.example.PaymentService.model.UserBalance;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @RequestMapping("/add-amount")
    @ResponseStatus(HttpStatus.CREATED)
    public String addAmount(@RequestBody UserBalance userBalance){
        return null;
    }
}
