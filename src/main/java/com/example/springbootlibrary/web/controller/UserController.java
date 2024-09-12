package com.example.springbootlibrary.web.controller;

import com.example.springbootlibrary.model.user.User;
import com.example.springbootlibrary.service.UserService;
import com.example.springbootlibrary.web.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }



}
