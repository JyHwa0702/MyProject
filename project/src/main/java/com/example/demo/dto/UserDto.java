package com.example.demo.dto;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.transaction.Transactional;

@Builder
@Data
public class UserDto {

    private Long id;
    private String password;
    private String username;
    private String email;
    private String picture;
    private Role role;

    @Transactional
    public UserDto update(String username, String picture){
        this.username = username;
        return this;
    }
    public User toEntity(){
        return User.userDetailRegister()
                .username(username)
                .email(email)
                .password(toEntity().getPassword())
                .role(Role.ROLE_USER)
                .build();
    }
}
