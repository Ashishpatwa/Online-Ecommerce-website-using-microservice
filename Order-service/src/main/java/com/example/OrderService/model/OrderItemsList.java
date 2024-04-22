package com.example.OrderService.model;

//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderItemsList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productId;
    private String variantName;
    private String quantity;
    @ManyToOne
//    @JoinColumn(name = "order_Id")
    private PurchaseOrder purchaseOrder;
}
