package com.coeproject.dkglassdesigns.service;

import com.coeproject.dkglassdesigns.dto.UserDto;
import com.coeproject.dkglassdesigns.mapper.Mapper;
import com.coeproject.dkglassdesigns.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
