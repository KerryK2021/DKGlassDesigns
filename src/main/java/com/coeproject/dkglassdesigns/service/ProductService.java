package com.coeproject.dkglassdesigns.service;

import com.coeproject.dkglassdesigns.dto.*;
import com.coeproject.dkglassdesigns.entity.Products;
import com.coeproject.dkglassdesigns.entity.User;
import com.coeproject.dkglassdesigns.exception.custom.ResourceNotFoundException;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
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
        return mapper.map(productsRepository.findById(productId), ProductsDto.class);
    }

    public ProductsDto createProduct(final CreateProductDto createProductDto) {
        final Products products = mapper.map(createProductDto, Products.class);
        final Products newProduct;
        newProduct = productsRepository.save(products);
        return mapper.map(newProduct, ProductsDto.class);
    }

    public Optional<ProductsDto> updateProduct(final int productId, final UpdateProductDto updateProductDto) {
        final Optional<Products> products = productsRepository.findById(productId);
        if (products.isEmpty()) return Optional.empty();
        updateProductEntity(updateProductDto, products.get());
        return Optional.of(mapper.map(products.get(), ProductsDto.class));
    }

    private void updateProductEntity(final UpdateProductDto updateProductDto, final Products products) {
        products.setName(updateProductDto.getName());
        products.setDescription(updateProductDto.getDescription());
        products.setPrice(updateProductDto.getPrice());
        products.setStockCount(updateProductDto.getStockCount());
        products.setStatus(updateProductDto.getStatus());
        products.setNotes(updateProductDto.getNotes());
        products.setSupplierId(updateProductDto.getSupplierId());
        products.setCategoryId(updateProductDto.getCategoryId());
        productsRepository.save(products);
    }

    public void deleteProductById(Integer productId) {
        if(!productsRepository.existsById(productId)){
            throw new ResourceNotFoundException("Unable to find productId");
        }
        productsRepository.deleteById(productId);
    }
}
