package com.example.springfolderstructuretemplate.services;

import com.example.springfolderstructuretemplate.dto.user.UserRequest;
import com.example.springfolderstructuretemplate.dto.user.UserResponse;

import java.util.List;

public interface IUserManagementService {

    List<UserResponse> findUsers();

    UserResponse findUserById(long id);

    UserResponse updateUser(long id, UserRequest request);

    boolean deleteUser(long id);
}
