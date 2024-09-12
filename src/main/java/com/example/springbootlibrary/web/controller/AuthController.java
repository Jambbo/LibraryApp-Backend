package com.example.springbootlibrary.web.controller;

import com.example.springbootlibrary.model.user.User;
import com.example.springbootlibrary.service.AuthService;
import com.example.springbootlibrary.service.UserService;
import com.example.springbootlibrary.web.dto.auth.JwtRequest;
import com.example.springbootlibrary.web.dto.auth.JwtResponse;
import com.example.springbootlibrary.web.dto.user.UserDTO;
import com.example.springbootlibrary.web.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/logic")
    public JwtResponse login(@Validated @RequestBody JwtRequest jwtRequest){
        return authService.login(jwtRequest);
    }

    @PostMapping("/register")
    public UserDTO register(@Validated @RequestBody UserDTO userDTO){
        User user = userMapper.toEntity(userDTO);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken){
        return authService.refresh(refreshToken);
    }

}
