package com.example.springfolderstructuretemplate.controllers;

import com.example.springfolderstructuretemplate.dto.account.ChangePasswordRequest;
import com.example.springfolderstructuretemplate.dto.account.LoginRequest;
import com.example.springfolderstructuretemplate.dto.user.UserRequest;
import com.example.springfolderstructuretemplate.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/account")
public class AccountController {

    private final AccountService _accountService;

    public AccountController(AccountService accountService) {
        this._accountService = accountService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody UserRequest request) {
        return new ResponseEntity<>(_accountService.register(request), HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(_accountService.login(request), HttpStatus.OK);
    }

    @PutMapping(path = "/password/{id}")
    public ResponseEntity<Boolean> changePassword(@PathVariable(name = "id") long id, @RequestBody ChangePasswordRequest request) {
        boolean updatedUser = _accountService.changePassword(id, request);

        if (updatedUser) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
