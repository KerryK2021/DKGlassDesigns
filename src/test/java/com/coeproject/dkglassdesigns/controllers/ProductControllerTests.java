package com.coeproject.dkglassdesigns.controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.coeproject.dkglassdesigns.controller.ProductsController;
import com.coeproject.dkglassdesigns.dto.*;
import com.coeproject.dkglassdesigns.exception.ExceptionHandler;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.*;
import com.coeproject.dkglassdesigns.service.ProductService;
import com.coeproject.dkglassdesigns.util.ResourceUtility;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {

    private MockMvc mockMvc;

    private static final String CREATE_PRODUCT_VALID_JSON = ResourceUtility.generateStringFromResource("requestJson/CreateProduct_Valid.json");

    private static final String UPDATE_PRODUCT_VALID_JSON = ResourceUtility.generateStringFromResource("requestJson/UpdateProduct_Valid.json");

    @Mock
    private Mapper mapperMock;

    @Mock
    private ProductService productServiceMock;

    @Fixture
    private ProductsDto productsDtoFixture;

    @Fixture
    private List<ProductsDto> productsDtoListFixture;

    @Fixture
    private CreateProductDto createProductDtoFixture;

    @Fixture
    private UpdateProductDto updateProductDtoFixture;

    @Fixture
    private CreateProductView createProductViewFixture;

    @Fixture
    private UpdateProductView updateProductViewFixture;

    @Fixture
    private ProductsView productsViewFixture;

    @Fixture
    private List<ProductsView> productsViewsListFixture;

    @Rule
    public FixtureRule fr = FixtureRule.initFixtures();

    @BeforeEach
    public void setUp() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        jFixture.customise().sameInstance(new SpecimenType<SortedSet<ProductsView>>() {
        }, new TreeSet<>());
        FixtureAnnotations.initFixtures(this, jFixture);
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductsController(productServiceMock, mapperMock)).setControllerAdvice(new ExceptionHandler()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void get_list_of_all_products_returnsOk() throws Exception {
        when(mapperMock.map(productsDtoListFixture, ProductsView.class)).thenReturn(productsViewsListFixture);
        when(productServiceMock.findAll()).thenReturn(productsDtoListFixture);
        mockMvc.perform(get("/products")).andExpect(status().isOk());
    }

    @Test
    public void get_product_by_id_returns_ok() throws Exception {
        when(mapperMock.map(productsDtoFixture, ProductsView.class)).thenReturn(productsViewFixture);
        when(productServiceMock.findById(anyInt())).thenReturn(productsDtoFixture);
        mockMvc.perform(get("/products/1")).andExpect(status().isOk());
    }

    @Test
    public void create_product_returns_created() throws Exception {
        when(mapperMock.map(any(CreateProductView.class), eq(CreateProductDto.class))).thenReturn(createProductDtoFixture);
        when(productServiceMock.createProduct(createProductDtoFixture)).thenReturn(productsDtoFixture);
        when(mapperMock.map(productsDtoFixture, ProductsView.class)).thenReturn(productsViewFixture);
        mockMvc.perform(post("/products").contentType(MediaType.APPLICATION_JSON)
                .content(CREATE_PRODUCT_VALID_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void update_product_by_id_returns_ok() throws Exception {
        when(mapperMock.map(any(UpdateProductView.class), eq(UpdateProductDto.class))).thenReturn(updateProductDtoFixture);
        when(productServiceMock.updateProduct(anyInt(), any(UpdateProductDto.class))).thenReturn(Optional.of(productsDtoFixture));
        when(mapperMock.map(productsDtoFixture, ProductsView.class)).thenReturn(productsViewFixture);
        mockMvc.perform(put("/products/1").contentType(MediaType.APPLICATION_JSON)
                .content(UPDATE_PRODUCT_VALID_JSON)).andExpect(status().isOk());
    }
}