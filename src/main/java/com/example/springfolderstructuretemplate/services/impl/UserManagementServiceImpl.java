package com.example.springfolderstructuretemplate.services.impl;

import com.example.springfolderstructuretemplate.dto.user.UserRequest;
import com.example.springfolderstructuretemplate.dto.user.UserResponse;
import com.example.springfolderstructuretemplate.entities.User;
import com.example.springfolderstructuretemplate.mappers.UserMapper;
import com.example.springfolderstructuretemplate.repositories.UserRepository;
import com.example.springfolderstructuretemplate.services.UserManagementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    private final UserRepository _userRepository;

    public UserManagementServiceImpl(UserRepository userRepository) {
        this._userRepository = userRepository;
    }

    @Override
    public List<UserResponse> findUsers() {
        return _userRepository.findAll().stream().map(UserMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse findUserById(long id) {
        return _userRepository.findById(id).map(UserMapper::toResponse).orElse(null);
    }

    @Override
    public UserResponse updateUser(long id, UserRequest request) {
        Optional<User> optionalUser = _userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setFirstname(request.getFirstname());
            existingUser.setLastname(request.getLastname());
            existingUser.setUsername(request.getUsername());

            User updatedUser = _userRepository.save(existingUser);
            return UserMapper.toResponse(updatedUser);
        }

        return null;
    }

    @Override
    public boolean deleteUser(long id) {
        if (_userRepository.existsById(id)) {
            _userRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
