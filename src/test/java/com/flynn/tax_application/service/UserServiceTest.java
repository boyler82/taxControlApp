package com.flynn.tax_application.service;

import com.flynn.tax_application.dto.UserCreateDto;
import com.flynn.tax_application.dto.UserResponseDto;
import com.flynn.tax_application.mapper.UserMapper;
import com.flynn.tax_application.model.User;
import com.flynn.tax_application.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUser() {
        UserCreateDto dto = new UserCreateDto("username", "password", "mail@mail.com", "John", "Doe", "123456789");
        User user = new User();
        user.setId(1L);
        user.setUsername(dto.getUsername());
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(1L);
        responseDto.setUsername("username");

        when(userMapper.mapToUser(dto)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.mapToUserResponseDto(user)).thenReturn(responseDto);

        UserResponseDto result = userService.createUser(dto);

        assertEquals(1L, result.getId());
        assertEquals("username", result.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldGetUserById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(1L);
        responseDto.setUsername("username");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.mapToUserResponseDto(user)).thenReturn(responseDto);

        UserResponseDto result = userService.getUserById(1L);

        assertEquals(1L, result.getId());
        assertEquals("username", result.getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void shouldUpdateUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("oldUsername");

        UserCreateDto dto = new UserCreateDto("updated", "password", "mail@mail.com", "John", "Doe", "123456789");
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(1L);
        responseDto.setUsername("updated");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.mapToUserResponseDto(user)).thenReturn(responseDto);

        UserResponseDto result = userService.updateUser(1L, dto);

        assertEquals(1L, result.getId());
        assertEquals("updated", result.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldDeleteUser() {

            // GIVEN
            when(userRepository.existsById(1L)).thenReturn(true);

            // WHEN
            userService.deleteUser(1L);

            // THEN
            verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundOnDelete() {
        // GIVEN
        when(userRepository.existsById(1L)).thenReturn(false);

        // WHEN & THEN
        assertThrows(RuntimeException.class, () -> userService.deleteUser(1L));
    }
}