package com.coeproject.dkglassdesigns.controller;

import com.coeproject.dkglassdesigns.dto.CategoryDto;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.CategoriesView;
import com.coeproject.dkglassdesigns.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Validated
@Slf4j
public class CategoriesController {

    private final CategoryService categoryService;
    private final Mapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriesView>> getCategories() {
        List<CategoryDto> categoriesList = categoryService.findAll();
        return ResponseEntity.ok(mapper.map(categoriesList, CategoriesView.class));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesView> getCategory(@PathVariable final Integer id) {
        CategoryDto categoryById = categoryService.findById(id);
        return ResponseEntity.ok(mapper.map(categoryById, CategoriesView.class));
    }

}