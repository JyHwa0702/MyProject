package com.example.demo.entity;

import com.example.demo.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "USERS")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* 로그인할 회원 아이디 */
    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(nullable = false,length = 20, unique = true)
    private String nickname;

    @Column(nullable = false, length = 30, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
