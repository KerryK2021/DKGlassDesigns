package com.coeproject.dkglassdesigns.service;

import com.coeproject.dkglassdesigns.dto.CategoryDto;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final Mapper mapper;

    public List<CategoryDto> findAll(){
        return mapper.map(categoryRepository.findAll(), CategoryDto.class);
    }

    public CategoryDto findById(final int categoryId) {
        return mapper.map(categoryRepository.findById(categoryId).get(), CategoryDto.class);
    }
}
