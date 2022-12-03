package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)throws OAuth2AuthenticationException{
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId(); //google
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider+"_"+providerId;

        String uuid = UUID.randomUUID().toString().substring(0,6);
        String password = bCryptPasswordEncoder.encode("패스워드"+uuid);

        String email = oAuth2User.getAttribute("email");
        Role role = Role.USER;

        Optional<User> byEmail = userRepository.findByEmail(email);

        //DB에 없는 사용자라면 회원가입처리
        if(byEmail.isPresent()){
            byEmail = Optional.ofNullable(User.oauth2Register()
                    .email(email).username(username).password(password).role(role)
                    .provider(provider).providerId(providerId)
                    .build());
            userRepository.save(byEmail);

        }
    }
}
