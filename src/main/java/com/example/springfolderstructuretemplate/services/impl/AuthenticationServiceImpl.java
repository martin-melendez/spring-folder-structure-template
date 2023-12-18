package com.example.springfolderstructuretemplate.services.impl;

import com.example.springfolderstructuretemplate.config.JwtService;
import com.example.springfolderstructuretemplate.dto.authentication.AuthenticationRequestDto;
import com.example.springfolderstructuretemplate.dto.authentication.AuthenticationResponseDto;
import com.example.springfolderstructuretemplate.dto.authentication.RegisterRequestDto;
import com.example.springfolderstructuretemplate.entities.Role;
import com.example.springfolderstructuretemplate.entities.User;
import com.example.springfolderstructuretemplate.repositories.IUserRepository;
import com.example.springfolderstructuretemplate.services.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final IUserRepository _userRepository;
    private final PasswordEncoder _passwordEncoder;
    private final JwtService _jwtService;
    private final AuthenticationManager _authenticationManager;

    public AuthenticationServiceImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this._userRepository = userRepository;
        this._passwordEncoder = passwordEncoder;
        this._jwtService = jwtService;
        this._authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponseDto register(RegisterRequestDto request) {
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(_passwordEncoder.encode(request.getPassword()))
                .role(Role.REGULAR_USER)
                .build();

        _userRepository.save(user);

        String jwtToken = _jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .jwtToken(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        _authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = _userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String jwtToken = _jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .jwtToken(jwtToken)
                .build();
    }
}
