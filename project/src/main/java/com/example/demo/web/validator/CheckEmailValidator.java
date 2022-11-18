package com.example.demo.web.validator;

import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@RequiredArgsConstructor
@Component
public class CheckEmailValidator extends AbstractValidator<UserDto> {

    private final UserRepository userRepository;

    @Override
    protected void doValidate(UserDto dto, Errors errors){
            if (userRepository.existsByEmail(dto.toEntity().getEmail())){

                /* 중복인 경우 */
                errors.rejectValue("email","이메일 중복오류","이미 사용중인 이메일입니다.");
            }
    }
}
