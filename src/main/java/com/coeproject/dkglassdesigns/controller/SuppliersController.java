package com.coeproject.dkglassdesigns.controller;

import com.coeproject.dkglassdesigns.dto.SupplierDto;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.SuppliersView;
import com.coeproject.dkglassdesigns.service.SupplierService;
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
@RequestMapping("/supplier")
@RequiredArgsConstructor
@Validated
@Slf4j
public class SuppliersController {

    private final SupplierService supplierService;
    private final Mapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SuppliersView>> getSuppliers() {
        List<SupplierDto> supplierList = supplierService.findAll();
        return ResponseEntity.ok(mapper.map(supplierList, SuppliersView.class));
    }

    @GetMapping(value = "/{supplier_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuppliersView> getSupplier(final Integer supplierId) {
        SupplierDto supplierById = supplierService.findById(supplierId);
        return ResponseEntity.ok(mapper.map(supplierById, SuppliersView.class));
    }

}