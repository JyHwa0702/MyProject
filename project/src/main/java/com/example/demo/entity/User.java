package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

//Google Oauth2 로그인 한 사용자들에 대한 정보를 저장하기 위한 User테이블
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS")
public class User extends Time{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String username, String email, String password,Role role){
        this.username = username;
        this.email = email;
        this.password =password;
        this.role = role;
    }

    public User update(String name, String picture){
        this.username = username;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
