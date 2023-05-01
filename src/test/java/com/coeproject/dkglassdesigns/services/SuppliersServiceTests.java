package com.coeproject.dkglassdesigns.services;

import com.coeproject.dkglassdesigns.dto.CategoryDto;
import com.coeproject.dkglassdesigns.dto.ProductsDto;
import com.coeproject.dkglassdesigns.dto.SupplierDto;
import com.coeproject.dkglassdesigns.entity.Category;
import com.coeproject.dkglassdesigns.entity.Products;
import com.coeproject.dkglassdesigns.entity.Supplier;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.CategoryRepository;
import com.coeproject.dkglassdesigns.repository.SupplierRepository;
import com.coeproject.dkglassdesigns.service.CategoryService;
import com.coeproject.dkglassdesigns.service.SupplierService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SuppliersServiceTests {
    @Mock
    private SupplierRepository supplierRepositoryMock;
    @Mock
    private Mapper mapperMock;

    @Fixture
    private List<Supplier> suppliersListFixture;

    @Fixture
    private List<SupplierDto> supplierDtoListFixture;

    @Fixture
    private Supplier supplierFixture;

    @Fixture
    private SupplierDto supplierDtoFixture;

    @InjectMocks
    private SupplierService classUnderTest;

    @BeforeEach
    public void setup() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void get_Suppliers_Returns_List_Of_Suppliers() {
        when(supplierRepositoryMock.findAll()).thenReturn(suppliersListFixture);
        when(mapperMock.map(suppliersListFixture, SupplierDto.class)).thenReturn(supplierDtoListFixture);

        List<SupplierDto> suppliersList = classUnderTest.findAll();

        assertThat(suppliersList).isNotNull();
        assertThat(suppliersList).isSameAs(supplierDtoListFixture);
    }

    @Test
    public void get_Supplier_By_Id_Returns_Supplier_By_Id() {
        when(supplierRepositoryMock.findById(anyInt())).thenReturn(Optional.of(supplierFixture));
        when(mapperMock.map(supplierFixture, SupplierDto.class)).thenReturn(supplierDtoFixture);

        SupplierDto supplierDto = classUnderTest.findById(anyInt());

        assertThat(supplierDto).isNotNull();
        assertThat(supplierDto).usingRecursiveComparison().isEqualTo(supplierDtoFixture);
        verify(supplierRepositoryMock).findById(anyInt());
    }
}
