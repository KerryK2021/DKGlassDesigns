package com.coeproject.dkglassdesigns.service;

import com.coeproject.dkglassdesigns.dto.*;
import com.coeproject.dkglassdesigns.entity.Order;
import com.coeproject.dkglassdesigns.entity.User;
import com.coeproject.dkglassdesigns.exception.custom.ResourceNotFoundException;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class OrderService {

    private final OrdersRepository ordersRepository;
    private final Mapper mapper;

    public List<OrdersDto> findAll(){
        return mapper.map(ordersRepository.findAll(), OrdersDto.class);
    }

    public OrdersDto findById(final int orderId) {
        return mapper.map(ordersRepository.findById(orderId), OrdersDto.class);
    }

    public OrdersDto createOrder(final CreateOrderDto createOrderDto) {
        final Order order = mapper.map(createOrderDto, Order.class);
        final Order newOrder;
        newOrder = ordersRepository.save(order);
        return mapper.map(newOrder, OrdersDto.class);
    }

    public Optional<OrdersDto> updateOrder(final int orderId, final UpdateOrderDto updateOrderDto) {
        final Optional<Order> orders = ordersRepository.findById(orderId);
        if (orders.isEmpty()) return Optional.empty();
        updateOrderEntity(updateOrderDto, orders.get());
        return Optional.of(mapper.map(orders.get(), OrdersDto.class));
    }

    private void updateOrderEntity(final UpdateOrderDto updateOrderDto, final Order order) {
        order.setOrderDate(updateOrderDto.getOrderDate());
        order.setStatus(updateOrderDto.getStatus());
        order.setNotes(updateOrderDto.getNotes());
        ordersRepository.save(order);
    }

    public void deleteOrderById(Integer orderId) {
        if(!ordersRepository.existsById(orderId)){
            throw new ResourceNotFoundException("Unable to find orderId");
        }
        ordersRepository.deleteById(orderId);
    }
}
