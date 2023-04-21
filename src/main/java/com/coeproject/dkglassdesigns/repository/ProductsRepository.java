package com.coeproject.dkglassdesigns.repository;

import com.coeproject.dkglassdesigns.entity.Products;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<Products, Integer> {
}
