package com.coeproject.dkglassdesigns.controller;

import com.coeproject.dkglassdesigns.entity.User;
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

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UsersController {

    private final UserRepository userRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersView> getUsers() {
        Iterable<User> userList = userRepository.findAll();
        return ResponseEntity.ok(UsersView.builder().build());
    }

    @GetMapping(value = "/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersView> getUser(final Integer userId) {
        return ResponseEntity.ok(UsersView.builder().build());
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
