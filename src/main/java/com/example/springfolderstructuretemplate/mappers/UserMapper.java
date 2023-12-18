package com.example.springfolderstructuretemplate.mappers;

import com.example.springfolderstructuretemplate.dto.user.UserRequest;
import com.example.springfolderstructuretemplate.dto.user.UserResponse;
import com.example.springfolderstructuretemplate.entities.User;

public class UserMapper {
    public static User toEntity(UserRequest userRequest) {
        return User.builder()
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .build();
    }

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .createdAt(user.getCreatedAt())
                .modifiedAt(user.getModifiedAt())
                .build();
    }
}
