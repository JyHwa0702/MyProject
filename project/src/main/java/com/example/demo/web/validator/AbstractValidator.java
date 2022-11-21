package com.example.demo.web.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Slf4j
public abstract class AbstractValidator<T> implements Validator {

    @Override
    public boolean supports(Class<?> clazz){
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void validate(Object target, Errors errors){
        try{
            doValidate((T) target,errors);
        } catch (IllegalStateException e){
            log.error("중복 검증에러",e);
            throw e;
        }
    }

    /* 유효성 검증 로직 */
    private void doValidate(final T dto, Errors errors) {
    }

    protected abstract void doValidate(UserDto dto, Errors errors);
}
