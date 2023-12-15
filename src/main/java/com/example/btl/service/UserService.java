package com.example.btl.service;

import com.example.btl.dto.UserDto;

public interface UserService {
    UserDto getUserById(Long id);

    UserDto updateUserById(UserDto userDto, Long id);

    void deleteUserById(Long id);
}
