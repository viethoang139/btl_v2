package com.example.btl.controller;

import com.example.btl.dto.LoginDto;
import com.example.btl.dto.RegisterDto;
import com.example.btl.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDto registerDto){
        return ResponseEntity.ok(authService.register(registerDto));
    }

}
