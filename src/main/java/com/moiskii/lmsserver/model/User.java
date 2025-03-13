package com.moiskii.lmsserver.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "username", unique = true, nullable = false)
    protected String username;

    @Column(name = "email", unique = true, nullable = false)
    protected String email;

    @Column(name = "password", nullable = false)
    protected String password;
}
