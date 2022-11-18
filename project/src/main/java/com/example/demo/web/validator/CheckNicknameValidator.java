package com.example.demo.web.validator;

import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@RequiredArgsConstructor
@Component
public class CheckNicknameValidator extends AbstractValidator<UserDto> {

    private final UserRepository userRepository;

    @Override
    protected void doValidate(UserDto dto, Errors errors){
            if (userRepository.existsByNickname(dto.toEntity().getNickname())){

                /* 중복인 경우 */
                errors.rejectValue("nickname","닉네임 중복오류","이미 사용중인 닉네임입니다.");
            }
    }
}
