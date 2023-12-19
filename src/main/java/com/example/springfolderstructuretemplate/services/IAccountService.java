package com.example.springfolderstructuretemplate.services;

import com.example.springfolderstructuretemplate.dto.account.ChangePasswordRequest;
import com.example.springfolderstructuretemplate.dto.account.LoginRequest;
import com.example.springfolderstructuretemplate.dto.user.UserRequest;

public interface IAccountService {

    String register(UserRequest request);

    String login(LoginRequest request);

    boolean changePassword(long id, ChangePasswordRequest request);
}
