package com.coeproject.dkglassdesigns.service;

import com.coeproject.dkglassdesigns.dto.OrderDetailsDto;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.OrderDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;
    private final Mapper mapper;

    public OrderDetailsDto findById(final int orderId) {
        return mapper.map(orderDetailsRepository.findById(orderId), OrderDetailsDto.class);
    }
}
