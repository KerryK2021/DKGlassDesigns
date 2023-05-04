package com.coeproject.dkglassdesigns.controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.coeproject.dkglassdesigns.controller.SuppliersController;
import com.coeproject.dkglassdesigns.dto.SupplierDto;
import com.coeproject.dkglassdesigns.exception.ExceptionHandler;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.SuppliersView;
import com.coeproject.dkglassdesigns.model.view.UsersView;
import com.coeproject.dkglassdesigns.service.SupplierService;
import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import com.flextrade.jfixture.rules.FixtureRule;
import com.flextrade.jfixture.utility.SpecimenType;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@ExtendWith(MockitoExtension.class)
public class SupplierControllerTests {

    private MockMvc mockMvc;

    @Mock
    private Mapper mapperMock;

    @Mock
    private SupplierService supplierServiceMock;

    @Fixture
    private SupplierDto supplierDtoFixture;

    @Fixture
    private List<SupplierDto> supplierDtoListFixture;

    @Fixture
    private SuppliersView suppliersViewFixture;

    @Fixture
    private List<SuppliersView> suppliersViewListFixture;

    @Rule
    public FixtureRule fr = FixtureRule.initFixtures();

    @BeforeEach
    public void setUp() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        jFixture.customise().sameInstance(new SpecimenType<SortedSet<SuppliersView>>() {
        }, new TreeSet<>());
        FixtureAnnotations.initFixtures(this, jFixture);
        mockMvc = MockMvcBuilders.standaloneSetup(new SuppliersController(supplierServiceMock, mapperMock)).setControllerAdvice(new ExceptionHandler()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void get_list_of_all_suppliers_returnsOk() throws Exception {
        when(mapperMock.map(supplierDtoListFixture, SuppliersView.class)).thenReturn(suppliersViewListFixture);
        when(supplierServiceMock.findAll()).thenReturn(supplierDtoListFixture);
        mockMvc.perform(get("/supplier")).andExpect(status().isOk());
    }

    @Test
    public void get_supplier_by_id_returns_ok() throws Exception {
        when(mapperMock.map(supplierDtoFixture, SuppliersView.class)).thenReturn(suppliersViewFixture);
        when(supplierServiceMock.findById(anyInt())).thenReturn(supplierDtoFixture);
        mockMvc.perform(get("/supplier/1")).andExpect(status().isOk());
    }
}