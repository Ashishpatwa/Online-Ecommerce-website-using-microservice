package com.example.OrderService.repository;


import com.example.OrderService.model.OrderItemsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemListRepository extends JpaRepository<OrderItemsList, Long> {
}
