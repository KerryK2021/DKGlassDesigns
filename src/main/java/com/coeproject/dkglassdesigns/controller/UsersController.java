package com.coeproject.dkglassdesigns.controller;

import com.coeproject.dkglassdesigns.entity.User;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.model.view.CreateUsersView;
import com.coeproject.dkglassdesigns.model.view.UpdateUserView;
import com.coeproject.dkglassdesigns.model.view.UsersView;
import com.coeproject.dkglassdesigns.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final Mapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsersView>> getUsers() {
        List<User> userList = (List<User>) userRepository.findAll();
        List<UsersView> usersViewList = mapper.map(userList, UsersView.class);
        return ResponseEntity.ok(usersViewList);
    }

    @GetMapping(value = "/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersView> getUser(final Integer userId) {
        Optional<User> userById = userRepository.findById(userId);
        UsersView usersView = mapper.map(userById, UsersView.class);
        return ResponseEntity.ok(usersView);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createUser(
            @Valid @RequestBody final CreateUsersView createUsersView) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{user_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateUserView> updateUser(final Integer userId,
            @Valid @RequestBody final UpdateUserView updateUserView) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteUser(final Integer userId) {
        return ResponseEntity.noContent().build();
    }
}
