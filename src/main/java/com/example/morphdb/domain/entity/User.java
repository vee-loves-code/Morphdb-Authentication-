package com.example.morphdb.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "user")
public class User extends BaseClass{

    @Column(nullable = false,columnDefinition = "VARCHAR(100)")
    private String firstName;

    @Column(unique = true,columnDefinition = "VARCHAR(100)")
    private String lastName;

    @Column(unique = true,nullable = false,columnDefinition = "VARCHAR(100)")
    private String email;

    @Column(name = "password",nullable = false)
    private String password;
}
