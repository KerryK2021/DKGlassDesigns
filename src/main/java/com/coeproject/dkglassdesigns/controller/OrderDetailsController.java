package com.coeproject.dkglassdesigns.controller;

import com.coeproject.dkglassdesigns.dto.CreateOrderDetailsDto;
import com.coeproject.dkglassdesigns.dto.OrderDetailsDto;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.CreateOrderDetailsView;
import com.coeproject.dkglassdesigns.model.view.OrderDetailsView;
import com.coeproject.dkglassdesigns.service.OrderDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order_details")
@RequiredArgsConstructor
@Validated
@Slf4j
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;
    private final Mapper mapper;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetailsView> getOrderDetails(@PathVariable final Integer id) {
        OrderDetailsDto orderDetailsById = orderDetailsService.findById(id);
        return ResponseEntity.ok(mapper.map(orderDetailsById, OrderDetailsView.class));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createOrderDetails(
            @Valid @RequestBody final CreateOrderDetailsView createOrdersDetailsView) {
        final CreateOrderDetailsDto createOrderDetailsDto = mapper.map(createOrdersDetailsView, CreateOrderDetailsDto.class);
        final OrderDetailsDto orderDetailsDto = orderDetailsService.createOrderDetails(createOrderDetailsDto);
        final OrderDetailsView orderDetailsView = mapper.map(orderDetailsDto, OrderDetailsView.class);
        return new ResponseEntity(orderDetailsView, HttpStatus.CREATED);
    }

}