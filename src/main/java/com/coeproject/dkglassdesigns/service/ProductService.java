package com.coeproject.dkglassdesigns.service;

import com.coeproject.dkglassdesigns.dto.*;
import com.coeproject.dkglassdesigns.entity.Products;
import com.coeproject.dkglassdesigns.exception.custom.ResourceNotFoundException;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductService {

    private final ProductsRepository productsRepository;
    private final Mapper mapper;

    public List<ProductsDto> findAll(){
        return mapper.map(productsRepository.findAll(), ProductsDto.class);
    }

    public ProductsDto findById(final int productId) {
        return mapper.map(productsRepository.findById(productId).get(), ProductsDto.class);
    }

    public ProductsDto createProduct(final CreateProductDto createProductDto) {
        final Products products = mapper.map(createProductDto, Products.class);
        final Products newProduct = productsRepository.save(products);
        return mapper.map(newProduct, ProductsDto.class);
    }

    public Optional<ProductsDto> updateProduct(final int productId, final UpdateProductDto updateProductDto) {
        final Optional<Products> products = productsRepository.findById(productId);
        if (products.isEmpty()) return Optional.empty();
        updateProductEntity(updateProductDto);
        return Optional.of(mapper.map(products.get(), ProductsDto.class));
    }

    private void updateProductEntity(final UpdateProductDto updateProductDto) {
        Products updateProduct = mapper.map(updateProductDto, Products.class);
        productsRepository.save(updateProduct);
    }

    public void deleteProductById(Integer productId) {
        try {
            productsRepository.deleteById(productId);
        } catch (final EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Unable to find product");
        }
    }
}
