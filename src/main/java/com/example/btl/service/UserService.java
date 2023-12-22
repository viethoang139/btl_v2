package com.example.btl.service;

import com.example.btl.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);

    UserDto updateUserById(UserDto userDto, Long id);

    List<UserDto> getAllUsers();

    void deleteUserById(Long id);
}
