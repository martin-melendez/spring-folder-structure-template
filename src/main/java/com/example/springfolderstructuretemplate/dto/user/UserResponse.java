package com.example.springfolderstructuretemplate.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String firstname;
    private String lastname;
    private String email;

    private Date createdAt;
    private Date modifiedAt;
}
