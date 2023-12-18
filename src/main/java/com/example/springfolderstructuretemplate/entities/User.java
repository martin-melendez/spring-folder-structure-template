package com.example.springfolderstructuretemplate.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @Column(length = 25, name = "firstname", nullable = false)
    private String firstname;

    @Column(length = 25, name = "lastname", nullable = false)
    private String lastname;

    @Column(length = 255, name = "email", nullable = false)
    private String email;

    @Column(length = 255, name = "password", nullable = false)
    private String password;


    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private Date modifiedAt;
}
