package com.coeproject.dkglassdesigns.repository;

import com.coeproject.dkglassdesigns.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends CrudRepository<Order, Integer> {
    List<Order> findAll();

    Optional<Order> findById(int orderId);
}
