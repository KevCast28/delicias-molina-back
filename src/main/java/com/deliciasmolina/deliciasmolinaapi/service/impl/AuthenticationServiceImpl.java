package com.deliciasmolina.deliciasmolinaapi.service.impl;

import com.deliciasmolina.deliciasmolinaapi.dto.Request.LoginRequestDTO;
import com.deliciasmolina.deliciasmolinaapi.dto.Response.LoginResponseDTO;
import com.deliciasmolina.deliciasmolinaapi.entity.User;
import com.deliciasmolina.deliciasmolinaapi.repository.UserRepository;
import com.deliciasmolina.deliciasmolinaapi.security.jwt.JwtService;
import com.deliciasmolina.deliciasmolinaapi.service.interfaces.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwtService.generateToken(user);

        LoginResponseDTO dto = new LoginResponseDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setUserRole(user.getUserRole());
        dto.setToken(token);

        return dto;
    }
}
