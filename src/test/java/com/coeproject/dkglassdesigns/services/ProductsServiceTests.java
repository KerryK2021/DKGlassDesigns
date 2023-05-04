package com.coeproject.dkglassdesigns.services;

import com.coeproject.dkglassdesigns.dto.CreateProductDto;
import com.coeproject.dkglassdesigns.dto.ProductsDto;
import com.coeproject.dkglassdesigns.dto.UserDto;
import com.coeproject.dkglassdesigns.entity.Products;
import com.coeproject.dkglassdesigns.entity.User;
import com.coeproject.dkglassdesigns.exception.custom.ResourceNotFoundException;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.ProductsRepository;
import com.coeproject.dkglassdesigns.service.ProductService;
import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceTests {
    @Mock
    private ProductsRepository productsRepositoryMock;
    @Mock
    private Mapper mapperMock;

    @Fixture
    private List<Products> productsListFixture;

    @Fixture
    private List<ProductsDto> productsDtoListFixture;

    @Fixture
    private Products productsFixture;

    @Fixture
    private ProductsDto productsDtoFixture;

    @Fixture
    private CreateProductDto createProductDtoFixture;

    @InjectMocks
    private ProductService classUnderTest;

    @BeforeEach
    public void setup() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void get_Products_Returns_List_Of_Products() {
        when(productsRepositoryMock.findAll()).thenReturn(productsListFixture);
        when(mapperMock.map(productsListFixture, ProductsDto.class)).thenReturn(productsDtoListFixture);

        List<ProductsDto> productsList = classUnderTest.findAll();

        assertThat(productsList).isNotNull();
        assertThat(productsList).isSameAs(productsDtoListFixture);
    }

    @Test
    public void get_Product_By_Id_Returns_Product_By_Id() {
        when(productsRepositoryMock.findById(anyInt())).thenReturn(Optional.of(productsFixture));
        when(mapperMock.map(productsFixture, ProductsDto.class)).thenReturn(productsDtoFixture);

        ProductsDto productsDto = classUnderTest.findById(anyInt());

        assertThat(productsDto).isNotNull();
        assertThat(productsDto).usingRecursiveComparison().isEqualTo(productsDtoFixture);
        verify(productsRepositoryMock).findById(anyInt());
    }

    @Test
    public void create_Product_Returns_ProductDto() {
        when(mapperMock.map(createProductDtoFixture, Products.class)).thenReturn(productsFixture);
        when(mapperMock.map(productsFixture, ProductsDto.class)).thenReturn(productsDtoFixture);
        when(productsRepositoryMock.save(productsFixture)).thenReturn(productsFixture);

        ProductsDto productsDto = classUnderTest.createProduct(createProductDtoFixture);

        verify(productsRepositoryMock).save(productsFixture);
        assertThat(productsDto).usingRecursiveComparison().isEqualTo(productsDtoFixture);
    }

    @Test
    public void delete_Product_Returns_ResourceNotFoundException() {
        doThrow(ResourceNotFoundException.class).when(productsRepositoryMock).deleteById(anyInt());
        assertThrows(ResourceNotFoundException.class,
                () -> classUnderTest.deleteProductById(anyInt()),
                "ResourceNotFoundException is expected");
        verify(productsRepositoryMock).deleteById(anyInt());
    }
}
