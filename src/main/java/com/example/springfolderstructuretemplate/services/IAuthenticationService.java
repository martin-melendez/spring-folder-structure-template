package com.example.springfolderstructuretemplate.services;

import com.example.springfolderstructuretemplate.dto.authentication.LoginRequest;
import com.example.springfolderstructuretemplate.dto.user.UserRequest;

public interface IAuthenticationService {

    String register(UserRequest request);

    String login(LoginRequest request);
}
