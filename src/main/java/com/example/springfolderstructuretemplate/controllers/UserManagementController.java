package com.example.springfolderstructuretemplate.controllers;

import com.example.springfolderstructuretemplate.dto.user.UserRequest;
import com.example.springfolderstructuretemplate.dto.user.UserResponse;
import com.example.springfolderstructuretemplate.services.IUserManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UserManagementController {

    private final IUserManagementService _userManagementService;

    public UserManagementController(IUserManagementService userManagementService) {
        this._userManagementService = userManagementService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> users = _userManagementService.findUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(name = "id") long id) {
        UserResponse user = _userManagementService.findUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable(name = "id") long id, @RequestBody UserRequest request) {
        UserResponse updatedUser = _userManagementService.updateUser(id, request);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable(name = "id") long id) {
        boolean isDeleted = _userManagementService.deleteUser(id);
        if (isDeleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
