package com.example.demo.service;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Transactional
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

    @Transactional
    public UserDto getPost(String email){
        log.info("UserService getPost on");
        log.info(email);
        Optional<User> byEmail = userRepository.findByEmail(email);

        log.info("UserService getPost byEmail 완료 byEmail : "+ byEmail.toString() );
        User user = byEmail.get();
        log.info("UserService getPost byEmail로 user생성완료");
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .password(user.getPassword())
                .username(user.getUsername())
                .build();

        return userDto;
    }
}
