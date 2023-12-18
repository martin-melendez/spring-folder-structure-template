package com.example.springfolderstructuretemplate.services;

import com.example.springfolderstructuretemplate.dto.user.UserRequest;
import com.example.springfolderstructuretemplate.dto.user.UserResponse;

import java.util.List;

public interface IUserService {

    List<UserResponse> findUsers();

    UserResponse findUserById(long id);

    UserResponse saveUser(UserRequest userRequest);

    UserResponse updateUser(long id, UserRequest userRequest);

    boolean deleteUser(long id);
}
