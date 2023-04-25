package com.coeproject.dkglassdesigns.repository;

import com.coeproject.dkglassdesigns.entity.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends CrudRepository<Supplier, Integer> {
    List<Supplier> findAll();

    Optional<Supplier> findById(int supplierId);

}
