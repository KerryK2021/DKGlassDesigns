package com.coeproject.dkglassdesigns.repository;

import com.coeproject.dkglassdesigns.entity.Products;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends CrudRepository<Products, Integer> {

    List<Products> findAll();

    Optional<Products> findById(int productId);
}

