package com.example.demo.dto;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
@Builder
@Data

public class UserDto {

    private Long id;
    private String password;
    private String username;
    private String email;
    private String picture;
    private Role role;


    public UserDto update(String username, String picture){
        this.username = username;
        return this;
    }
}
