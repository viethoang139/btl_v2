package com.example.btl.service;

import com.example.btl.dto.LoginDto;
import com.example.btl.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

}
