package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;


    /* 회원가입 */

    public Long userJoin(UserDto dto){
        dto.encryptPassword(encoder.encode(dto.getPassword()));

        User user = dto.toEntity();
        userRepository.save(user);
        log.info("DB에 회원가입 유저 저장성공");

        return user.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    /* 아이디, 닉네임, 이메일 중복여부 확인 */

    @Transactional
    @Override
    public boolean checkUsernameDuplication(String username){
        boolean usernameDuplicate = userRepository.existsByUsername(username);
        return usernameDuplicate;
    }

    @Transactional
    @Override
    public boolean checkNicknameDuplication(String nickname){
        boolean nicknameDuplicate   = userRepository.existsByNickname(nickname);
        return nicknameDuplicate;
    }

    @Transactional
    @Override
    public boolean checkEmailDuplication(String email){
        boolean emailDuplicate  = userRepository.existsByEmail(email);
        return emailDuplicate;
    }
}
