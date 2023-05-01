package com.coeproject.dkglassdesigns.controller;

import com.coeproject.dkglassdesigns.dto.*;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.*;
import com.coeproject.dkglassdesigns.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ProductsController {

    private final ProductService productService;
    private final Mapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductsView>> getProducts() {
        List<ProductsDto> productsList = productService.findAll();
        return ResponseEntity.ok(mapper.map(productsList, ProductsView.class));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductsView> getProduct(@PathVariable final Integer id) {
        ProductsDto productById = productService.findById(id);
        return ResponseEntity.ok(mapper.map(productById, ProductsView.class));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createProduct(
            @Valid @RequestBody final CreateProductView createProductView) {
        final CreateProductDto createProductDto = mapper.map(createProductView, CreateProductDto.class);
        final ProductsDto productsDto = productService.createProduct(createProductDto);
        final ProductsView productsView = mapper.map(productsDto, ProductsView.class);
        return new ResponseEntity(productsView, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateProductView> updateProduct(@PathVariable final int productId,
                                                     @Valid @RequestBody final UpdateProductView updateProductView) {
        final UpdateProductDto updateProductDto = mapper.map(updateProductView, UpdateProductDto.class);
        final Optional<ProductsDto> productsDto = productService.updateProduct(productId, updateProductDto);
        final ProductsView productsView = mapper.map(productsDto, ProductsView.class);
        return new ResponseEntity(productsView, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteProduct(final Integer productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }
}