package com.example.springfolderstructuretemplate.controllers;

import com.example.springfolderstructuretemplate.dto.authentication.LoginRequest;
import com.example.springfolderstructuretemplate.dto.user.UserRequest;
import com.example.springfolderstructuretemplate.services.IAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthenticationController {

    private final IAuthenticationService _authenticationService;

    public AuthenticationController(IAuthenticationService authenticationService) {
        this._authenticationService = authenticationService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody UserRequest request) {
        return new ResponseEntity<>(_authenticationService.register(request), HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(_authenticationService.login(request), HttpStatus.OK);
    }
}
