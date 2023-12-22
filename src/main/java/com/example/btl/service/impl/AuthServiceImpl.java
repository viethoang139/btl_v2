package com.example.btl.service.impl;

import com.example.btl.dto.LoginDto;
import com.example.btl.dto.LoginDtoResponse;
import com.example.btl.dto.RegisterDto;
import com.example.btl.entity.Role;
import com.example.btl.entity.User;
import com.example.btl.repository.RoleRepository;
import com.example.btl.repository.UserRepository;
import com.example.btl.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public LoginDtoResponse login(LoginDto loginDto) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());
        authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByUsername(loginDto.getUsername()).get();
        LoginDtoResponse loginDtoResponse = new LoginDtoResponse();
        loginDtoResponse.setId(user.getId());
        loginDtoResponse.setUsername(loginDto.getUsername());
        return loginDtoResponse;
    }

    @Override
    public String register(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());
        user.setPhone(registerDto.getPhone());
        user.setAddress(registerDto.getAddress());
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return "Register successfully";
    }
}
