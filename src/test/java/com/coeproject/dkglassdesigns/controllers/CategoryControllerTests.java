package com.coeproject.dkglassdesigns.controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.coeproject.dkglassdesigns.controller.CategoriesController;
import com.coeproject.dkglassdesigns.dto.*;
import com.coeproject.dkglassdesigns.exception.ExceptionHandler;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.*;
import com.coeproject.dkglassdesigns.service.CategoryService;
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
public class CategoryControllerTests {

    private MockMvc mockMvc;

    @Mock
    private Mapper mapperMock;

    @Mock
    private CategoryService categoryServiceMock;

    @Fixture
    private CategoryDto categoryDtoFixture;

    @Fixture
    private List<CategoryDto> categoryDtoListFixture;

    @Fixture
    private CategoriesView categoriesViewFixture;

    @Fixture
    private List<CategoriesView> categoriesViewListFixture;

    @Rule
    public FixtureRule fr = FixtureRule.initFixtures();

    @BeforeEach
    public void setUp() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        jFixture.customise().sameInstance(new SpecimenType<SortedSet<CategoriesView>>() {
        }, new TreeSet<>());
        FixtureAnnotations.initFixtures(this, jFixture);
        mockMvc = MockMvcBuilders.standaloneSetup(new CategoriesController(categoryServiceMock, mapperMock)).setControllerAdvice(new ExceptionHandler()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void get_list_of_all_categories_returnsOk() throws Exception {
        when(mapperMock.map(categoryDtoListFixture, CategoriesView.class)).thenReturn(categoriesViewListFixture);
        when(categoryServiceMock.findAll()).thenReturn(categoryDtoListFixture);
        mockMvc.perform(get("/category")).andExpect(status().isOk());
    }

    @Test
    public void get_category_by_id_returns_ok() throws Exception {
        when(mapperMock.map(categoryDtoFixture, CategoriesView.class)).thenReturn(categoriesViewFixture);
        when(categoryServiceMock.findById(anyInt())).thenReturn(categoryDtoFixture);
        mockMvc.perform(get("/category/1")).andExpect(status().isOk());
    }
}