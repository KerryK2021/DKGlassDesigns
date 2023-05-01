package com.coeproject.dkglassdesigns.service;

import com.coeproject.dkglassdesigns.dto.CreateUserDto;
import com.coeproject.dkglassdesigns.dto.UpdateUserDto;
import com.coeproject.dkglassdesigns.dto.UserDto;
import com.coeproject.dkglassdesigns.entity.User;
import com.coeproject.dkglassdesigns.exception.custom.ResourceNotFoundException;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final Mapper mapper;

    public List<UserDto> findAll(){
        return mapper.map(userRepository.findAll(), UserDto.class);
    }

    public UserDto findById(final int userId) {
        return mapper.map(userRepository.findById(userId).get(), UserDto.class);
    }

    public UserDto createUser(final CreateUserDto createUserDto) {
        final User user = mapper.map(createUserDto, User.class);
        final User newUser = userRepository.save(user);
        return mapper.map(newUser, UserDto.class);
    }

    public Optional<UserDto> updateUser(final int userId, final UpdateUserDto updateUserDto) {
        final Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) return Optional.empty();
        updateUserEntity(updateUserDto);
        return Optional.of(mapper.map(user.get(), UserDto.class));
    }

    private void updateUserEntity(final UpdateUserDto updateUserDto) {
        User updateUser = mapper.map(updateUserDto, User.class);
        userRepository.save(updateUser);
    }

    public void deleteUserById(final Integer userId) {
        try {
            userRepository.deleteById(userId);
        } catch (final EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Unable to find user");
        }
    }
}
