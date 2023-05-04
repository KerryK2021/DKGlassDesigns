package com.coeproject.dkglassdesigns.controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.coeproject.dkglassdesigns.controller.OrderDetailsController;
import com.coeproject.dkglassdesigns.dto.*;
import com.coeproject.dkglassdesigns.exception.ExceptionHandler;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.*;
import com.coeproject.dkglassdesigns.service.OrderDetailsService;
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

import java.util.SortedSet;
import java.util.TreeSet;

@ExtendWith(MockitoExtension.class)
public class OrderDetailsControllerTests {

    private MockMvc mockMvc;

    private static final String CREATE_ORDER_DETAILS_VALID_JSON = ResourceUtility.generateStringFromResource("requestJson/CreateOrderDetails_Valid.json");

    @Mock
    private Mapper mapperMock;

    @Mock
    private OrderDetailsService orderDetailsServiceMock;

    @Fixture
    private OrderDetailsDto orderDetailsDtoFixture;

    @Fixture
    private CreateOrderDetailsDto createOrderDetailsDtoFixture;

    @Fixture
    private CreateOrderDetailsView createOrderDetailsViewFixture;

    @Fixture
    private OrderDetailsView orderDetailsViewFixture;


    @Rule
    public FixtureRule fr = FixtureRule.initFixtures();

    @BeforeEach
    public void setUp() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        jFixture.customise().sameInstance(new SpecimenType<SortedSet<OrderDetailsView>>() {
        }, new TreeSet<>());
        FixtureAnnotations.initFixtures(this, jFixture);
        mockMvc = MockMvcBuilders.standaloneSetup(new OrderDetailsController(orderDetailsServiceMock, mapperMock)).setControllerAdvice(new ExceptionHandler()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void get_order_details_by_id_returns_ok() throws Exception {
        when(mapperMock.map(orderDetailsDtoFixture, OrderDetailsView.class)).thenReturn(orderDetailsViewFixture);
        when(orderDetailsServiceMock.findById(anyInt())).thenReturn(orderDetailsDtoFixture);
        mockMvc.perform(get("/order_details/1")).andExpect(status().isOk());
    }

    @Test
    public void create_order_details_returns_created() throws Exception {
        when(mapperMock.map(any(CreateOrderDetailsView.class), eq(CreateOrderDetailsDto.class))).thenReturn(createOrderDetailsDtoFixture);
        when(orderDetailsServiceMock.createOrderDetails(createOrderDetailsDtoFixture)).thenReturn(orderDetailsDtoFixture);
        when(mapperMock.map(orderDetailsDtoFixture, OrderDetailsView.class)).thenReturn(orderDetailsViewFixture);
        mockMvc.perform(post("/order_details").contentType(MediaType.APPLICATION_JSON)
                .content(CREATE_ORDER_DETAILS_VALID_JSON)).andExpect(status().isCreated());
    }

}