package com.coeproject.dkglassdesigns.repository;

import com.coeproject.dkglassdesigns.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    List<Category> findAll();

    Optional<Category> findById(int categoryId);
}
