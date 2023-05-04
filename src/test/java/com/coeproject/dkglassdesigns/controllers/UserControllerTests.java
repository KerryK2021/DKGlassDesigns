package com.coeproject.dkglassdesigns.controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.coeproject.dkglassdesigns.controller.UsersController;
import com.coeproject.dkglassdesigns.dto.CreateUserDto;
import com.coeproject.dkglassdesigns.dto.UpdateUserDto;
import com.coeproject.dkglassdesigns.dto.UserDto;
import com.coeproject.dkglassdesigns.exception.ExceptionHandler;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.CreateUsersView;
import com.coeproject.dkglassdesigns.model.view.UpdateUserView;
import com.coeproject.dkglassdesigns.model.view.UsersView;
import com.coeproject.dkglassdesigns.service.UserService;
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
public class UserControllerTests {

    private MockMvc mockMvc;

    private static final String CREATE_USER_VALID_JSON = ResourceUtility.generateStringFromResource("requestJson/CreateUser_Valid.json");

    private static final String UPDATE_USER_VALID_JSON = ResourceUtility.generateStringFromResource("requestJson/UpdateUser_Valid.json");

    @Mock
    private Mapper mapperMock;

    @Mock
    private UserService userServiceMock;

    @Fixture
    private UserDto userDtoFixture;

    @Fixture
    private List<UserDto> userDtoListFixture;

    @Fixture
    private CreateUserDto createUserDtoFixture;

    @Fixture
    private UpdateUserDto updateUserDtoFixture;

    @Fixture
    private CreateUsersView createUsersViewFixture;

    @Fixture
    private UpdateUserView updateUserViewFixture;

    @Fixture
    private UsersView usersViewFixture;

    @Fixture
    private List<UsersView> usersViewListFixture;

    @Rule
    public FixtureRule fr = FixtureRule.initFixtures();

    @BeforeEach
    public void setUp() {
        final JFixture jFixture = new JFixture();
        jFixture.customise().circularDependencyBehaviour().omitSpecimen();
        jFixture.customise().sameInstance(new SpecimenType<SortedSet<UsersView>>() {
        }, new TreeSet<>());
        FixtureAnnotations.initFixtures(this, jFixture);
        mockMvc = MockMvcBuilders.standaloneSetup(new UsersController(userServiceMock, mapperMock)).setControllerAdvice(new ExceptionHandler()).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void get_list_of_all_users_returnsOk() throws Exception {
        when(mapperMock.map(userDtoListFixture, UsersView.class)).thenReturn(usersViewListFixture);
        when(userServiceMock.findAll()).thenReturn(userDtoListFixture);
        mockMvc.perform(get("/system_users")).andExpect(status().isOk());
    }

    @Test
    public void get_user_by_id_returns_ok() throws Exception {
        when(mapperMock.map(userDtoFixture, UsersView.class)).thenReturn(usersViewFixture);
        when(userServiceMock.findById(anyInt())).thenReturn(userDtoFixture);
        mockMvc.perform(get("/system_users/1")).andExpect(status().isOk());
    }

    @Test
    public void create_user_returns_created() throws Exception {
        when(mapperMock.map(any(CreateUsersView.class), eq(CreateUserDto.class))).thenReturn(createUserDtoFixture);
        when(userServiceMock.createUser(createUserDtoFixture)).thenReturn(userDtoFixture);
        when(mapperMock.map(userDtoFixture, UsersView.class)).thenReturn(usersViewFixture);
        mockMvc.perform(post("/system_users").contentType(MediaType.APPLICATION_JSON)
                .content(CREATE_USER_VALID_JSON)).andExpect(status().isCreated());
    }

    @Test
    public void update_user_by_id_returns_ok() throws Exception {
        when(mapperMock.map(any(UpdateUserView.class), eq(UpdateUserDto.class))).thenReturn(updateUserDtoFixture);
        when(userServiceMock.updateUser(anyInt(), any(UpdateUserDto.class))).thenReturn(Optional.of(userDtoFixture));
        when(mapperMock.map(userDtoFixture, UsersView.class)).thenReturn(usersViewFixture);
        mockMvc.perform(put("/system_users/1").contentType(MediaType.APPLICATION_JSON)
                .content(UPDATE_USER_VALID_JSON)).andExpect(status().isOk());
    }

    @Test
    public void delete_user_returns_no_content() throws Exception {
        doNothing().when(userServiceMock).deleteUserById(anyInt());
        mockMvc.perform(delete("/system_users/1")).andExpect(status().isNoContent());
    }
}