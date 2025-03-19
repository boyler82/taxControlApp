package com.flynn.tax_application.service;

import com.flynn.tax_application.dto.UserCreateDto;
import com.flynn.tax_application.dto.UserResponseDto;
import com.flynn.tax_application.mapper.UserMapper;
import com.flynn.tax_application.model.User;
import com.flynn.tax_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDto createUser(UserCreateDto dto) {
        User user = userMapper.mapToUser(dto);
        User savedUser = userRepository.save(user);
        return userMapper.mapToUserResponseDto(savedUser);
    }


    public UserResponseDto getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.mapToUserResponseDto(user);
    }

    public UserResponseDto updateUser(long id, UserCreateDto userCreateDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(userCreateDto.getUsername());
        user.setEmail(userCreateDto.getEmail());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setPhoneNumber(userCreateDto.getPhoneNumber());

        userRepository.save(user);

        return userMapper.mapToUserResponseDto(user);
    }

    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
