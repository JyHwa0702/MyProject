package com.example.demo.service;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.config.auth.userinfo.GoogleUserInfo;
import com.example.demo.config.auth.userinfo.KakaoUserInfo;
import com.example.demo.config.auth.userinfo.OAuth2UserInfo;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)throws OAuth2AuthenticationException{
        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId(); //
        if (provider.equals("google")){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if(provider.equals("kakao")){
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }

        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider+"_"+providerId;

        String uuid = UUID.randomUUID().toString().substring(0,6);
        String password = bCryptPasswordEncoder.encode(uuid);

        String email = oAuth2UserInfo.getEmail();
        Role role = Role.USER;

        Optional<User> byEmail = userRepository.findByEmail(email);

        //DB에 없는 사용자라면 회원가입처리
        if(byEmail.isEmpty()){
            byEmail = Optional.ofNullable(User.oauth2Register()
                    .email(email).username(username).password(password).role(role)
                    .provider(provider).providerId(providerId)
                    .build());

            userRepository.save(byEmail.get());
        }
        return new PrincipalDetails(byEmail.get(),oAuth2UserInfo);
    }
}
