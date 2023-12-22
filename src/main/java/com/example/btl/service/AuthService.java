package com.example.btl.service;

import com.example.btl.dto.LoginDto;
import com.example.btl.dto.LoginDtoResponse;
import com.example.btl.dto.RegisterDto;

public interface AuthService {
    LoginDtoResponse login(LoginDto loginDto);

    String register(RegisterDto registerDto);

}
