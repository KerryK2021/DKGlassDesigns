package com.coeproject.dkglassdesigns.service;

import com.coeproject.dkglassdesigns.dto.UserDto;
import com.coeproject.dkglassdesigns.entity.User;
import com.coeproject.dkglassdesigns.exception.custom.ResourceNotFoundException;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
        return mapper.map(userRepository.findById(userId), UserDto.class);
    }

    public void deleteUserById(Integer userId) {
        if(!userRepository.existsById(userId)){
            throw new ResourceNotFoundException("Unable to find userId");
        }
        userRepository.deleteById(userId);
    }
}
