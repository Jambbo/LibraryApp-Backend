package com.example.springbootlibrary.service;

import com.example.springbootlibrary.model.user.User;
import com.example.springbootlibrary.web.dto.auth.JwtRequest;
import com.example.springbootlibrary.web.dto.auth.JwtResponse;
import com.example.springbootlibrary.web.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public JwtResponse login(final JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()
                    )
            );
            User user = userService.getByUsername(loginRequest.getUsername());
            jwtResponse.setId(user.getId());
            jwtResponse.setUsername(user.getUsername());
            jwtResponse.setAccessToken(
                    jwtTokenProvider.createAccessToken(
                            user.getId(), user.getUsername(), user.getRoles()
                    )
            );
            jwtResponse.setRefreshToken(
                    jwtTokenProvider.createRefreshToken(
                            user.getId(), user.getUsername()
                    )
            );
            return jwtResponse;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public JwtResponse refresh(final String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }

}
