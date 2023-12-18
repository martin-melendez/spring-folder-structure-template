package com.example.springfolderstructuretemplate.repositories;

import com.example.springfolderstructuretemplate.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
