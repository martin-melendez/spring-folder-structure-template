package com.example.springfolderstructuretemplate.services;

import com.example.springfolderstructuretemplate.dto.authentication.AuthenticationRequestDto;
import com.example.springfolderstructuretemplate.dto.authentication.AuthenticationResponseDto;
import com.example.springfolderstructuretemplate.dto.authentication.RegisterRequestDto;

public interface IAuthenticationService {

    AuthenticationResponseDto register(RegisterRequestDto request);

    AuthenticationResponseDto authenticate(AuthenticationRequestDto request);
}
