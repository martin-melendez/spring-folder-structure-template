package com.example.springfolderstructuretemplate.repositories;

import com.example.springfolderstructuretemplate.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
