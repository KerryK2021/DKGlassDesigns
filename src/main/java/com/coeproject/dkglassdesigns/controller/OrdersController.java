package com.coeproject.dkglassdesigns.controller;

import com.coeproject.dkglassdesigns.dto.OrdersDto;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.CreateOrdersView;
import com.coeproject.dkglassdesigns.model.view.OrdersView;
import com.coeproject.dkglassdesigns.model.view.UpdateOrderView;
import com.coeproject.dkglassdesigns.service.OrderService;
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

    private final OrderService orderService;
    private final Mapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrdersView>> getOrders() {
        List<OrdersDto> ordersList = orderService.findAll();
        return ResponseEntity.ok(mapper.map(ordersList, OrdersView.class));
    }

    @GetMapping(value = "/{order_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersView> getOrder(final Integer orderId) {
        OrdersDto orderById = orderService.findById(orderId);
        return ResponseEntity.ok(mapper.map(orderById, OrdersView.class));
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
