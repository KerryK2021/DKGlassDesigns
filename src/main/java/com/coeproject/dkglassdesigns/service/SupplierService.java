package com.coeproject.dkglassdesigns.service;

import com.coeproject.dkglassdesigns.dto.SupplierDto;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final Mapper mapper;

    public List<SupplierDto> findAll(){
        return mapper.map(supplierRepository.findAll(), SupplierDto.class);
    }

    public SupplierDto findById(final int supplierId) {
        return mapper.map(supplierRepository.findById(supplierId).get(), SupplierDto.class);
    }
}
