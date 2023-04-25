package com.coeproject.dkglassdesigns.service;

import com.coeproject.dkglassdesigns.dto.OrdersDto;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
