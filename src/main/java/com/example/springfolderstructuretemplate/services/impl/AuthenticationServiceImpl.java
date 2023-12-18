package com.example.springfolderstructuretemplate.services.impl;

import com.example.springfolderstructuretemplate.config.JwtService;
import com.example.springfolderstructuretemplate.dto.authentication.LoginRequest;
import com.example.springfolderstructuretemplate.dto.user.UserRequest;
import com.example.springfolderstructuretemplate.entities.Role;
import com.example.springfolderstructuretemplate.entities.User;
import com.example.springfolderstructuretemplate.mappers.UserMapper;
import com.example.springfolderstructuretemplate.repositories.IUserRepository;
import com.example.springfolderstructuretemplate.services.IAuthenticationService;
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
    public String register(UserRequest request) {
        User user = UserMapper.toEntity(request);
        user.setPassword(_passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.REGULAR_USER);

        _userRepository.save(user);
        return _jwtService.generateToken(user);
    }

    @Override
    public String login(LoginRequest request) {
        _authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = _userRepository.findByUsername(request.getUsername()).orElseThrow();
        return _jwtService.generateToken(user);
    }
}
