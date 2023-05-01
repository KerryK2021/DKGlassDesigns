package com.coeproject.dkglassdesigns.services;

import com.coeproject.dkglassdesigns.dto.CategoryDto;
import com.coeproject.dkglassdesigns.entity.Category;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.CategoryRepository;
import com.coeproject.dkglassdesigns.service.CategoryService;
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
public class CategoryServiceTests {
    @Mock
    private CategoryRepository categoryRepositoryMock;
    @Mock
    private Mapper mapperMock;

    @Fixture
    private List<Category> categoryListFixture;

    @Fixture
    private List<CategoryDto> categoryDtoListFixture;

    @Fixture
    private Category categoryFixture;

    @Fixture
    private CategoryDto categoryDtoFixture;

    @InjectMocks
    private CategoryService classUnderTest;

    @BeforeEach
    public void setup() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void get_Categories_Returns_List_Of_Orders() {
        when(categoryRepositoryMock.findAll()).thenReturn(categoryListFixture);
        when(mapperMock.map(categoryListFixture, CategoryDto.class)).thenReturn(categoryDtoListFixture);

        List<CategoryDto> categoryList = classUnderTest.findAll();

        assertThat(categoryList).isNotNull();
        assertThat(categoryList).isSameAs(categoryDtoListFixture);
    }

    @Test
    public void get_Category_By_Id_Returns_Category_By_Id() {
        when(categoryRepositoryMock.findById(anyInt())).thenReturn(Optional.of(categoryFixture));
        when(mapperMock.map(categoryFixture, CategoryDto.class)).thenReturn(categoryDtoFixture);

        CategoryDto categoryDto = classUnderTest.findById(anyInt());

        assertThat(categoryDto).isNotNull();
        assertThat(categoryDto).usingRecursiveComparison().isEqualTo(categoryDtoFixture);
        verify(categoryRepositoryMock).findById(anyInt());
    }
}
