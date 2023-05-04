package com.coeproject.dkglassdesigns.controller;

import com.coeproject.dkglassdesigns.dto.CreateUserDto;
import com.coeproject.dkglassdesigns.dto.UpdateUserDto;
import com.coeproject.dkglassdesigns.dto.UserDto;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.CreateUsersView;
import com.coeproject.dkglassdesigns.model.view.UpdateUserView;
import com.coeproject.dkglassdesigns.model.view.UsersView;
import com.coeproject.dkglassdesigns.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/system_users")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UsersController {

    private final UserService userService;
    private final Mapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsersView>> getUsers() {
        List<UserDto> userDtoList = userService.findAll();
        return ResponseEntity.ok(mapper.map(userDtoList, UsersView.class));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersView> getUser(@PathVariable final Integer id) {
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok(mapper.map(userDto, UsersView.class));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createUser(
            @Valid @RequestBody final CreateUsersView createUsersView) {
        final CreateUserDto createUserDto = mapper.map(createUsersView, CreateUserDto.class);
        final UserDto userDto = userService.createUser(createUserDto);
        final UsersView usersView = mapper.map(userDto, UsersView.class);
        return new ResponseEntity(usersView, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateUserView> updateUser(@PathVariable final int id,
            @Valid @RequestBody final UpdateUserView updateUserView) {
        final UpdateUserDto updateUserDto = mapper.map(updateUserView, UpdateUserDto.class);
        final Optional<UserDto> userDto = userService.updateUser(id, updateUserDto);
        final UsersView usersView = mapper.map(userDto.get(), UsersView.class);
        return new ResponseEntity(usersView, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteUser(final Integer userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}
