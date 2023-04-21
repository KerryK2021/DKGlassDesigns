package com.coeproject.dkglassdesigns.controller;

import com.coeproject.dkglassdesigns.model.view.CategoriesView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Validated
@Slf4j
public class CategoriesController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesView> getCategories() {
        return ResponseEntity.ok(CategoriesView.builder().build());
    }

    @GetMapping(value = "/{category_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriesView> getCategory(final Integer categoryId) {
        return ResponseEntity.ok(CategoriesView.builder().build());
    }

}