package com.example.PaymentService.model;

//import jakarta.persistence.*;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserTransaction {

    @Id
    private String orderId;
    private String userId;
    private Integer amount;

}
