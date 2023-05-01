package com.coeproject.dkglassdesigns.services;

import com.coeproject.dkglassdesigns.dto.*;
import com.coeproject.dkglassdesigns.entity.OrderDetails;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.OrderDetailsRepository;
import com.coeproject.dkglassdesigns.service.OrderDetailsService;
import com.flextrade.jfixture.annotations.Fixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderDetailsServiceTests {

    @Mock
    private OrderDetailsRepository orderDetailsRepositoryMock;
    @Mock
    private Mapper mapperMock;

    @Fixture
    private OrderDetails orderDetailsFixture;

    @Fixture
    private OrderDetailsDto orderDetailsDtoFixture;

    @Fixture
    private CreateOrderDetailsDto createOrderDetailsDtoFixture;

    @InjectMocks
    private OrderDetailsService classUnderTest;

    @Test
    public void create_Order_Details_Returns_OrderDetailsDto() {
        when(mapperMock.map(createOrderDetailsDtoFixture, OrderDetails.class)).thenReturn(orderDetailsFixture);
        when(mapperMock.map(orderDetailsFixture, OrderDetailsDto.class)).thenReturn(orderDetailsDtoFixture);
        when(orderDetailsRepositoryMock.save(orderDetailsFixture)).thenReturn(orderDetailsFixture);

        OrderDetailsDto orderDetailsDto = classUnderTest.createOrderDetails(createOrderDetailsDtoFixture);

        verify(orderDetailsRepositoryMock).save(orderDetailsFixture);
        assertThat(orderDetailsDto).usingRecursiveComparison().isEqualTo(orderDetailsDtoFixture);
    }

    @Test
    public void get_Order_Details_By_Id_Returns_Order_Details_By_Id() {
        when(orderDetailsRepositoryMock.findById(anyInt())).thenReturn(Optional.of(orderDetailsFixture));
        when(mapperMock.map(orderDetailsFixture, OrderDetailsDto.class)).thenReturn(orderDetailsDtoFixture);

        OrderDetailsDto orderDetailsDto = classUnderTest.findById(anyInt());

        assertThat(orderDetailsDto).isNotNull();
        assertThat(orderDetailsDto).usingRecursiveComparison().isEqualTo(orderDetailsDtoFixture);
        verify(orderDetailsRepositoryMock).findById(anyInt());
    }
}
