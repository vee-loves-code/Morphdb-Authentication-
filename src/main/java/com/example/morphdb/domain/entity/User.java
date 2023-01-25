package com.example.morphdb.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
