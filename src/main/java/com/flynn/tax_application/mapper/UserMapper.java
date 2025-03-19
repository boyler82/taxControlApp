package com.flynn.tax_application.mapper;

import com.flynn.tax_application.dto.UserCreateDto;
import com.flynn.tax_application.dto.UserResponseDto;
import com.flynn.tax_application.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public  User mapToUser(UserCreateDto userCreateDto) {
        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        user.setEmail(userCreateDto.getEmail());
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setPhoneNumber(userCreateDto.getPhoneNumber());
        user.setEnabled(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setTaxClientList(new ArrayList<>());
        return user;
    }

    public UserResponseDto mapToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setPhoneNumber(user.getPhoneNumber());
        userResponseDto.setEnabled(user.isEnabled());
        userResponseDto.setCreatedAt(user.getCreatedAt());

        List<Long> taxClients = user.getTaxClientList() != null
                ? user.getTaxClientList().stream()
                .map(c -> c.getId())
                .collect(Collectors.toList())
                : List.of();

        userResponseDto.setTaxClientIds(taxClients);
        return userResponseDto;
    }
}
