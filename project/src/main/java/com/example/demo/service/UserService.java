package com.example.demo.service;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getInfoByEmail(PrincipalDetails principalDetails){
        String userEmail = null;
        if(principalDetails.getUser() != null){
            userEmail = principalDetails.getUser().getEmail();
        }else if (principalDetails.getOAuth2UserInfo() != null){
            userEmail = principalDetails.getOAuth2UserInfo().getEmail();
        }
        Optional<User> byEmail = userRepository.findByEmail(userEmail);
        return byEmail.get();
    }
}
