package com.flynn.tax_application.controller;

import com.flynn.tax_application.dto.UserCreateDto;
import com.flynn.tax_application.dto.UserResponseDto;
import com.flynn.tax_application.mapper.UserMapper;
import com.flynn.tax_application.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateDto userCreateDto) {
        User user = UserMapper.mapToUser(userCreateDto);
        UserResponseDto responseDto = UserMapper.mapToUserResponseDto(user);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(new UserResponseDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id, @RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.ok(new UserResponseDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }


}
