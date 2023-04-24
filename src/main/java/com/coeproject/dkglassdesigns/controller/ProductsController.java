package com.coeproject.dkglassdesigns.controller;

import com.coeproject.dkglassdesigns.entity.Products;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.ProductsView;
import com.coeproject.dkglassdesigns.model.view.UpdateProductView;
import com.coeproject.dkglassdesigns.repository.ProductsRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ProductsController {

    private final ProductsRepository productsRepository;
    private final Mapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductsView>> getProducts() {
        List<Products> productsList = (List<Products>) productsRepository.findAll();
        List<ProductsView> productsViewList = mapper.map(productsList, ProductsView.class);
        return ResponseEntity.ok(productsViewList);
    }

    @GetMapping(value = "/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductsView> getProduct(final Integer productId) {
        return ResponseEntity.ok(ProductsView.builder().build());
    }

    @PutMapping(value = "/{product_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateProductView> updateProduct(final Integer productId, @Valid @RequestBody final UpdateProductView updateProductView) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteProduct(final Integer productId) {
        return ResponseEntity.noContent().build();
    }
}