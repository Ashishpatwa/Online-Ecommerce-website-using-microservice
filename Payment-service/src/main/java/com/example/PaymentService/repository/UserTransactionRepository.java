package com.example.PaymentService.repository;

import com.example.PaymentService.model.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, String> {
}
