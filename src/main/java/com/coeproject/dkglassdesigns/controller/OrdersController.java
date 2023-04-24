package com.coeproject.dkglassdesigns.controller;

import com.coeproject.dkglassdesigns.entity.Order;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.CreateOrdersView;
import com.coeproject.dkglassdesigns.model.view.OrdersView;
import com.coeproject.dkglassdesigns.model.view.UpdateOrderView;
import com.coeproject.dkglassdesigns.repository.OrdersRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Validated
@Slf4j
public class OrdersController {

    private final OrdersRepository ordersRepository;
    private final Mapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrdersView>> getOrders() {
        List<Order> ordersList = (List<Order>) ordersRepository.findAll();
        List<OrdersView> ordersViewList = mapper.map(ordersList, OrdersView.class);
        return ResponseEntity.ok(ordersViewList);
    }

    @GetMapping(value = "/{order_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersView> getOrder(final Integer orderId) {
        return ResponseEntity.ok(OrdersView.builder().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createOrder(
            @Valid @RequestBody final CreateOrdersView createOrdersView) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{order_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateOrderView> updateUser(final Integer orderId, @Valid @RequestBody final UpdateOrderView updateOrderView) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{order_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteOrder(final Integer orderId) {
        return ResponseEntity.noContent().build();
    }
}
