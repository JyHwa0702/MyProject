package com.example.demo.web.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@RequiredArgsConstructor
@Component
public class CheckUsernameValidator extends AbstractValidator<UserDto> {

    private final UserRepository userRepository;

    @Override
    protected void doValidate(UserDto dto, Errors errors){
            if (userRepository.existsByUsername(dto.toEntity().getUsername())){

                /* 중복인 경우 */
                errors.rejectValue("username","아이디 중복오류","이미 사용중인 아이디입니다.");
            }
    }
}
