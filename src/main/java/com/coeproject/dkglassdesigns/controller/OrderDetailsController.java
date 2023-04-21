package com.coeproject.dkglassdesigns.controller;

import com.coeproject.dkglassdesigns.model.view.CreateOrderDetailsView;
import com.coeproject.dkglassdesigns.model.view.OrderDetailsView;
import com.coeproject.dkglassdesigns.repository.OrderDetailsRepository;
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

    private final OrderDetailsRepository orderDetailsRepository;

    @GetMapping(value = "/{order_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetailsView> getOrderDetails(final Integer orderId) {
        return ResponseEntity.ok(OrderDetailsView.builder().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createOrderDetails(
            @Valid @RequestBody final CreateOrderDetailsView createOrderDetailsView) {

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}