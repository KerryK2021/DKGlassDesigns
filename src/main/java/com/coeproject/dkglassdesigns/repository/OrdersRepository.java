package com.coeproject.dkglassdesigns.repository;

import com.coeproject.dkglassdesigns.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Order, Integer> {
}
