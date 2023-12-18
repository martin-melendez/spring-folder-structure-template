package com.example.springfolderstructuretemplate.services.impl;

import com.example.springfolderstructuretemplate.dto.user.UserRequest;
import com.example.springfolderstructuretemplate.dto.user.UserResponse;
import com.example.springfolderstructuretemplate.entities.User;
import com.example.springfolderstructuretemplate.mappers.UserMapper;
import com.example.springfolderstructuretemplate.repositories.IUserRepository;
import com.example.springfolderstructuretemplate.services.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository _userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
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
    public UserResponse saveUser(UserRequest request) {
        User savedUser = _userRepository.save(UserMapper.toEntity(request));
        return UserMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(long id, UserRequest request) {
        Optional<User> optionalUser = _userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            existingUser.setFirstname(request.getFirstname());
            existingUser.setLastname(request.getLastname());
            existingUser.setEmail(request.getEmail());
            existingUser.setPassword(request.getPassword());

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
