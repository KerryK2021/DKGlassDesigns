package com.coeproject.dkglassdesigns.service;

import com.coeproject.dkglassdesigns.dto.ProductsDto;
import com.coeproject.dkglassdesigns.exception.custom.ResourceNotFoundException;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteProductById(Integer productId) {
        if(!productsRepository.existsById(productId)){
            throw new ResourceNotFoundException("Unable to find productId");
        }
        productsRepository.deleteById(productId);
    }
}
