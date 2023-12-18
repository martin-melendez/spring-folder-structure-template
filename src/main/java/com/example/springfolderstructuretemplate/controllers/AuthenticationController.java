package com.example.springfolderstructuretemplate.controllers;

import com.example.springfolderstructuretemplate.dto.authentication.AuthenticationRequestDto;
import com.example.springfolderstructuretemplate.dto.authentication.AuthenticationResponseDto;
import com.example.springfolderstructuretemplate.dto.authentication.RegisterRequestDto;
import com.example.springfolderstructuretemplate.services.IAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/authentication")
public class AuthenticationController {

    private final IAuthenticationService _authenticationService;

    public AuthenticationController(IAuthenticationService authenticationService) {
        this._authenticationService = authenticationService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegisterRequestDto request) {
        return new ResponseEntity<>(_authenticationService.register(request), HttpStatus.OK);
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto request) {
        return new ResponseEntity<>(_authenticationService.authenticate(request), HttpStatus.OK);
    }
}
