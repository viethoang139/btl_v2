package com.example.btl.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @NotEmpty(message = "username can not be empty")
    private String username;
    @NotEmpty(message = "password can not be empty")
    private String password;
    @NotEmpty(message = "email can not be empty")
    @Email(message = "invalid email.Please try again!")
    private String email;
    @NotEmpty(message = "phone can not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "phoneNumber must be 10 digits")
    private String phone;
    @NotEmpty(message = "address can not be empty")
    private String address;
}
