package com.example.springfolderstructuretemplate.services;

import com.example.springfolderstructuretemplate.dto.authentication.LoginRequest;
import com.example.springfolderstructuretemplate.dto.authentication.RegisterRequest;

public interface IAuthenticationService {

    String register(RegisterRequest request);

    String login(LoginRequest request);
}
