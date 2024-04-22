package com.example.OrderService.config;

import com.example.OrderService.model.OrderItemsList;
import com.example.OrderService.model.PurchaseOrder;
import com.example.OrderService.repository.OrderRepository;
import com.example.OrderService.service.OrderStatusPublisher;
import com.example.common.dto.OrderItemsListDto;
import com.example.common.dto.OrderRequestDto;
import com.example.common.enums.OrderStatus;
import com.example.common.enums.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;
import java.util.stream.Collectors;

@Configuration
public class OrderStatusUpdateHandler {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusPublisher orderStatusPublisher;


    @Transactional
    public void updateOrder(String orderId, Consumer<PurchaseOrder> consumer) {
        //listen to Payment-Event topic
        //validate PaymentStatus
        // if payment status is Successfull then Complete the order
        // if payment status is failed then cancel the order.

        orderRepository.findById(orderId)
                .ifPresent(consumer.andThen(this::updateOrder));

    }

    private void updateOrder(PurchaseOrder order) {
        boolean isPaymentComplete = PaymentStatus.PAYMENT_COMPLETED.equals(order.getPaymentStatus());
        OrderStatus orderStatus = isPaymentComplete? OrderStatus.ORDER_SUCCESS : OrderStatus.ORDER_FAILED;

        order.setOrderStatus(orderStatus);
        if(!isPaymentComplete){
            orderStatusPublisher.publishOrderEvent(convertEntityToDto(order), orderStatus);
        }

    }

    private OrderRequestDto convertEntityToDto(PurchaseOrder order) {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setUserId(order.getUserId());
        orderRequestDto.setOrderId(order.getOrderId());
//        orderRequestDto.setOrderStatus(order.getOrderStatus());
        orderRequestDto.setTotalAmount(order.getTotalAmount());
//        orderRequestDto.setOrderDate(order.getOrderDate());
//        orderRequestDto.setOrderRequestItemsListDto(order.getOrderItemsLists().stream()
//                .map(orderItemsList -> OrderItemsListDto.builder()
//                        .variantName(orderItemsList.getVariantName())
//                        .quantity(orderItemsList.getQuantity())
//                        .productId(orderItemsList.getProductId())
//                        .build()).collect(Collectors.toList()));
        return  orderRequestDto;
    }
}
