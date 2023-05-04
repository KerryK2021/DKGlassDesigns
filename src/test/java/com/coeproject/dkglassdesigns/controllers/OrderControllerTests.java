package com.coeproject.dkglassdesigns.controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.coeproject.dkglassdesigns.controller.OrdersController;
import com.coeproject.dkglassdesigns.dto.*;
import com.coeproject.dkglassdesigns.exception.ExceptionHandler;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.*;
import com.coeproject.dkglassdesigns.service.OrderService;
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
public class OrderControllerTests {

    private MockMvc mockMvc;

    private static final String CREATE_ORDER_VALID_JSON = ResourceUtility.generateStringFromResource("requestJson/CreateOrder_Valid.json");

    private static final String UPDATE_ORDER_VALID_JSON = ResourceUtility.generateStringFromResource("requestJson/UpdateOrder_Valid.json");

    @Mock
    private Mapper mapperMock;

    @Mock
    private OrderService orderServiceMock;

    @Fixture
    private OrdersDto ordersDtoFixture;

    @Fixture
    private List<OrdersDto> ordersDtoListFixture;

    @Fixture
    private CreateOrderDto createOrderDtoFixture;

    @Fixture
    private UpdateOrderDto updateOrderDtoFixture;

    @Fixture
    private CreateOrdersView createOrdersViewFixture;

    @Fixture
    private UpdateOrderView updateOrderViewFixture;

    @Fixture
    private OrdersView ordersViewFixture;

    @Fixture
    private List<OrdersView> ordersViewsListFixture;

    @Rule
    public FixtureRule fr = FixtureRule.initFixtures();

    @BeforeEach
    public void setUp() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        jFixture.customise().sameInstance(new SpecimenType<SortedSet<OrdersView>>() {
        }, new TreeSet<>());
        FixtureAnnotations.initFixtures(this, jFixture);
        mockMvc = MockMvcBuilders.standaloneSetup(new OrdersController(orderServiceMock, mapperMock)).setControllerAdvice(new ExceptionHandler()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void get_list_of_all_orders_returnsOk() throws Exception {
        when(mapperMock.map(ordersDtoListFixture, OrdersView.class)).thenReturn(ordersViewsListFixture);
        when(orderServiceMock.findAll()).thenReturn(ordersDtoListFixture);
        mockMvc.perform(get("/orders")).andExpect(status().isOk());
    }

    @Test
    public void get_order_by_id_returns_ok() throws Exception {
        when(mapperMock.map(ordersDtoFixture, OrdersView.class)).thenReturn(ordersViewFixture);
        when(orderServiceMock.findById(anyInt())).thenReturn(ordersDtoFixture);
        mockMvc.perform(get("/orders/1")).andExpect(status().isOk());
    }

    @Test
    public void create_order_returns_created() throws Exception {
        when(mapperMock.map(any(CreateOrdersView.class), eq(CreateOrderDto.class))).thenReturn(createOrderDtoFixture);
        when(orderServiceMock.createOrder(createOrderDtoFixture)).thenReturn(ordersDtoFixture);
        when(mapperMock.map(ordersDtoFixture, OrdersView.class)).thenReturn(ordersViewFixture);
        mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON)
                .content(CREATE_ORDER_VALID_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void update_order_by_id_returns_ok() throws Exception {
        when(mapperMock.map(any(UpdateOrderView.class), eq(UpdateOrderDto.class))).thenReturn(updateOrderDtoFixture);
        when(orderServiceMock.updateOrder(anyInt(), any(UpdateOrderDto.class))).thenReturn(Optional.of(ordersDtoFixture));
        when(mapperMock.map(ordersDtoFixture, OrdersView.class)).thenReturn(ordersViewFixture);
        mockMvc.perform(put("/orders/1").contentType(MediaType.APPLICATION_JSON)
                .content(UPDATE_ORDER_VALID_JSON)).andExpect(status().isOk());
    }
    @Test
    public void delete_order_returns_no_content() throws Exception {
        doNothing().when(orderServiceMock).deleteOrderById(anyInt());
        mockMvc.perform(delete("/orders/1")).andExpect(status().isNoContent());
    }
}