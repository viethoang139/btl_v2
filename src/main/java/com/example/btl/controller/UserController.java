package com.example.btl.controller;

import com.example.btl.dto.UserDto;
import com.example.btl.service.CartService;
import com.example.btl.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserById(@RequestBody @Valid UserDto userDto,
                                                  @PathVariable("id") Long userId){
        return ResponseEntity.ok(userService.updateUserById(userDto, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long userId){
        userService.deleteUserById(userId);
        return ResponseEntity.ok("Delete user successfully");
    }

}
