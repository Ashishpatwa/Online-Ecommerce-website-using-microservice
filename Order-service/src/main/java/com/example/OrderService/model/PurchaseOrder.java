package com.example.OrderService.model;

import com.example.common.dto.OrderItemsListDto;
import com.example.common.enums.OrderStatus;
import com.example.common.enums.PaymentStatus;
//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PurchaseOrder {
    @Id
    private String orderId;
    private String userId;
    private Integer totalAmount;
    @OneToMany(targetEntity = OrderItemsList.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(name = "Purchase_Order_With_Item_List",
            joinColumns = {
            @JoinColumn(name = "Order_Id", referencedColumnName = "orderId")},
            inverseJoinColumns = {
            @JoinColumn(name = "ItemList_Id", referencedColumnName = "id")}
    )
    private List<OrderItemsList> orderItemsLists;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private LocalDateTime orderDate;

}
