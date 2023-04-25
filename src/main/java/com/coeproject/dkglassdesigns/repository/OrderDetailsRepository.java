package com.coeproject.dkglassdesigns.repository;

import com.coeproject.dkglassdesigns.entity.OrderDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Integer> {

    Optional<OrderDetails> findById(int orderId);
}
