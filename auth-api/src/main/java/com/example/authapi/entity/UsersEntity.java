package com.example.authapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class UsersEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email",  unique = true)
    private String email;

    @Column(name = "password")
    private String password;
}
