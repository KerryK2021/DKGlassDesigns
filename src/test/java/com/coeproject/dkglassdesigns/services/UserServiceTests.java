package com.coeproject.dkglassdesigns.services;

import com.coeproject.dkglassdesigns.dto.CreateUserDto;
import com.coeproject.dkglassdesigns.dto.UpdateUserDto;
import com.coeproject.dkglassdesigns.dto.UserDto;
import com.coeproject.dkglassdesigns.entity.User;
import com.coeproject.dkglassdesigns.exception.custom.ResourceNotFoundException;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.UserRepository;
import com.coeproject.dkglassdesigns.service.UserService;
import com.flextrade.jfixture.FixtureAnnotations;
import com.flextrade.jfixture.JFixture;
import com.flextrade.jfixture.annotations.Fixture;
import jakarta.persistence.EntityManager;
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
public class UserServiceTests {
    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private Mapper mapperMock;

    @Mock
    private EntityManager entityManagerMock;

    @Fixture
    private List<User> userListFixture;

    @Fixture
    private List<UserDto> userDtoListFixture;

    @Fixture
    private User userFixture;

    @Fixture
    private UserDto userDtoFixture;

    @Fixture
    private CreateUserDto createUserDtoFixture;

    @Fixture
    private UpdateUserDto updateUserDtoFixture;

    @InjectMocks
    private UserService classUnderTest;

    @BeforeEach
    public void setup() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        FixtureAnnotations.initFixtures(this, jFixture);
    }

    @Test
    public void get_Users_Returns_List_Of_Users() {
        when(userRepositoryMock.findAll()).thenReturn(userListFixture);
        when(mapperMock.map(userListFixture, UserDto.class)).thenReturn(userDtoListFixture);

        List<UserDto> userList = classUnderTest.findAll();

        assertThat(userList).isNotNull();
        assertThat(userList).isSameAs(userDtoListFixture);
    }

    @Test
    public void get_User_By_Id_Returns_User_By_Id() {
        when(userRepositoryMock.findById(anyInt())).thenReturn(Optional.of(userFixture));
        when(mapperMock.map(userFixture, UserDto.class)).thenReturn(userDtoFixture);

        UserDto userDto = classUnderTest.findById(anyInt());

        assertThat(userDto).isNotNull();
        assertThat(userDto).usingRecursiveComparison().isEqualTo(userDtoFixture);
        verify(userRepositoryMock).findById(anyInt());
    }

    @Test
    public void create_User_Returns_UserDto() {
        when(mapperMock.map(createUserDtoFixture, User.class)).thenReturn(userFixture);
        when(mapperMock.map(userFixture, UserDto.class)).thenReturn(userDtoFixture);
        when(userRepositoryMock.save(userFixture)).thenReturn(userFixture);

        UserDto userDto = classUnderTest.createUser(createUserDtoFixture);

        verify(userRepositoryMock).save(userFixture);
        assertThat(userDto).usingRecursiveComparison().isEqualTo(userDtoFixture);
    }

    @Test
    public void update_User_Returns_UserDto() {
        when(mapperMock.map(userFixture, UserDto.class)).thenReturn(userDtoFixture);
        when(userRepositoryMock.findById(anyInt())).thenReturn(Optional.of(userFixture));
        when(mapperMock.map(updateUserDtoFixture, User.class)).thenReturn(userFixture);
        when(userRepositoryMock.save(userFixture)).thenReturn(userFixture);

        Optional<UserDto> userDto = classUnderTest.updateUser(anyInt(), updateUserDtoFixture);

        verify(userRepositoryMock).save(userFixture);
        assertThat(userDto.get()).usingRecursiveComparison().isEqualTo(userDtoFixture);
    }

    @Test
    public void delete_User_Returns_ResourceNotFoundException() {
        doThrow(ResourceNotFoundException.class).when(userRepositoryMock).deleteById(anyInt());
        assertThrows(ResourceNotFoundException.class,
                () -> classUnderTest.deleteUserById(anyInt()),
                "ResourceNotFoundException is expected");
        verify(userRepositoryMock).deleteById(anyInt());
    }
}
