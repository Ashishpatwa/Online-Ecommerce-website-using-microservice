package com.example.PaymentService.repository;

import com.example.PaymentService.model.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserBalanceRepository extends JpaRepository<UserBalance, String> {
}
