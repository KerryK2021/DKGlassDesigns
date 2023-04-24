package com.coeproject.dkglassdesigns.controller;

import com.coeproject.dkglassdesigns.entity.Supplier;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.SuppliersView;
import com.coeproject.dkglassdesigns.repository.SupplierRepository;
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

    private final SupplierRepository supplierRepository;
    private final Mapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SuppliersView>> getSuppliers() {
        List<Supplier> supplierList = (List<Supplier>) supplierRepository.findAll();
        List<SuppliersView> suppliersViewList = mapper.map(supplierList, SuppliersView.class);
        return ResponseEntity.ok(suppliersViewList);
    }

    @GetMapping(value = "/{supplier_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuppliersView> getSupplier(final Integer supplierId) {
        return ResponseEntity.ok(SuppliersView.builder().build());
    }

}