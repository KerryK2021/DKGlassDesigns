package com.coeproject.dkglassdesigns.services;

import com.coeproject.dkglassdesigns.dto.CreateOrderDto;
import com.coeproject.dkglassdesigns.dto.OrdersDto;
import com.coeproject.dkglassdesigns.dto.ProductsDto;
import com.coeproject.dkglassdesigns.entity.Order;
import com.coeproject.dkglassdesigns.entity.Products;
import com.coeproject.dkglassdesigns.exception.custom.ResourceNotFoundException;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.OrdersRepository;
import com.coeproject.dkglassdesigns.service.OrderService;
import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().silent();

    @Mock
    private OrdersRepository ordersRepositoryMock;
    @Mock
    private Mapper mapperMock;

    @Fixture
    private List<Order> ordersListFixture;

    @Fixture
    private List<OrdersDto> ordersDtoListFixture;

    @Fixture
    private Order orderFixture;

    @Fixture
    private OrdersDto orderDtoFixture;

    @Fixture
    private CreateOrderDto createOrderDtoFixture;

    @InjectMocks
    private OrderService classUnderTest;

    @BeforeEach
    public void setup() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void get_Orders_Returns_List_Of_Orders() {
        when(ordersRepositoryMock.findAll()).thenReturn(ordersListFixture);
        when(mapperMock.map(ordersListFixture, OrdersDto.class)).thenReturn(ordersDtoListFixture);

        List<OrdersDto> ordersList = classUnderTest.findAll();

        assertThat(ordersList).isNotNull();
        assertThat(ordersList).isSameAs(ordersDtoListFixture);
    }

    @Test
    public void get_Order_By_Id_Returns_Order_By_Id() {
        when(ordersRepositoryMock.findById(anyInt())).thenReturn(Optional.of(orderFixture));
        when(mapperMock.map(orderFixture, OrdersDto.class)).thenReturn(orderDtoFixture);

        OrdersDto ordersDto = classUnderTest.findById(anyInt());

        assertThat(ordersDto).isNotNull();
        assertThat(ordersDto).usingRecursiveComparison().isEqualTo(orderDtoFixture);
        verify(ordersRepositoryMock).findById(anyInt());
    }

    @Test
    public void create_Order_Returns_OrderDto() {
        when(mapperMock.map(createOrderDtoFixture, Order.class)).thenReturn(orderFixture);
        when(mapperMock.map(orderFixture, OrdersDto.class)).thenReturn(orderDtoFixture);
        when(ordersRepositoryMock.save(orderFixture)).thenReturn(orderFixture);

        OrdersDto ordersDto = classUnderTest.createOrder(createOrderDtoFixture);

        verify(ordersRepositoryMock).save(orderFixture);
        assertThat(ordersDto).usingRecursiveComparison().isEqualTo(orderDtoFixture);
    }

    @Test
    public void delete_Order_Returns_ResourceNotFoundException() {
        doThrow(ResourceNotFoundException.class).when(ordersRepositoryMock).deleteById(anyInt());
        assertThrows(ResourceNotFoundException.class,
                () -> classUnderTest.deleteOrderById(anyInt()),
                "ResourceNotFoundException is expected");
        verify(ordersRepositoryMock).deleteById(anyInt());
    }
}
