package com.example.springbootlibrary.service;

import com.example.springbootlibrary.dao.UserRepository;
import com.example.springbootlibrary.model.user.Role;
import com.example.springbootlibrary.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(RuntimeException::new);
    }

    public User getById(Long id){
        return userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Transactional
    public User create(User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new IllegalStateException("User already exists");
        }
        if(!user.getPassword().equals(user.getPasswordConfirmation())){
            throw new IllegalStateException("Passwords do not match");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = Set.of(Role.ROLE_USER);
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User update(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }


}
