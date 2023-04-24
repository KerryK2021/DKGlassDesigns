package com.coeproject.dkglassdesigns.controller;

import com.coeproject.dkglassdesigns.entity.Category;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.CategoriesView;
import com.coeproject.dkglassdesigns.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Validated
@Slf4j
public class CategoriesController {

    private final CategoryRepository categoriesRepository;
    private final Mapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriesView>> getCategories() {
        List<Category> categoriesList = (List<Category>) categoriesRepository.findAll();
        List<CategoriesView> categoriesViewList = mapper.map(categoriesList, CategoriesView.class);
        return ResponseEntity.ok(categoriesViewList);
    }

    @GetMapping(value = "/{category_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesView> getCategory(final Integer categoryId) {
        return ResponseEntity.ok(CategoriesView.builder().build());
    }

}