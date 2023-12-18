package com.example.springfolderstructuretemplate.services.impl;

import com.example.springfolderstructuretemplate.dto.user.UserRequest;
import com.example.springfolderstructuretemplate.dto.user.UserResponse;
import com.example.springfolderstructuretemplate.entities.User;
import com.example.springfolderstructuretemplate.mappers.UserMapper;
import com.example.springfolderstructuretemplate.repositories.IUserRepository;
import com.example.springfolderstructuretemplate.services.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository _userRepository;
    private final PasswordEncoder _passwordEncoder;

    public UserServiceImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this._userRepository = userRepository;
        this._passwordEncoder = passwordEncoder;
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
    public UserResponse saveUser(UserRequest request) {
        User savedUser = _userRepository.save(UserMapper.toEntity(request));
        return UserMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(long id, UserRequest request) {
        Optional<User> optionalUser = _userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            if (!request.getFirstname().equals(existingUser.getFirstname())) {
                existingUser.setFirstname(request.getFirstname());
            }

            if (!request.getLastname().equals(existingUser.getLastname())) {
                existingUser.setLastname(request.getLastname());
            }

            if (!request.getUsername().equals(existingUser.getUsername())) {
                existingUser.setUsername(request.getUsername());
            }

            if (!_passwordEncoder.matches(request.getPassword(), existingUser.getPassword())) {
                existingUser.setPassword(_passwordEncoder.encode(request.getPassword()));
            }

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
