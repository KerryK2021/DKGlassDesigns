package com.coeproject.dkglassdesigns.controller;

import com.coeproject.dkglassdesigns.dto.*;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.*;
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
import java.util.Optional;

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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdersView> getOrder(@PathVariable final Integer id) {
        OrdersDto orderById = orderService.findById(id);
        return ResponseEntity.ok(mapper.map(orderById, OrdersView.class));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createOrder(
            @Valid @RequestBody final CreateOrdersView createOrdersView) {
        final CreateOrderDto createOrderDto = mapper.map(createOrdersView, CreateOrderDto.class);
        final OrdersDto ordersDto = orderService.createOrder(createOrderDto);
        final OrdersView ordersView = mapper.map(ordersDto, OrdersView.class);
        return new ResponseEntity(ordersView, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateOrderView> updateOrder(@PathVariable final int orderId,
                                                     @Valid @RequestBody final UpdateOrderView updateOrderView) {
        final UpdateOrderDto updateOrderDto = mapper.map(updateOrderView, UpdateOrderDto.class);
        final Optional<OrdersDto> ordersDto = orderService.updateOrder(orderId, updateOrderDto);
        final OrdersView ordersView = mapper.map(ordersDto, OrdersView.class);
        return new ResponseEntity(ordersView, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable final Integer orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.ok().build();
    }
}
