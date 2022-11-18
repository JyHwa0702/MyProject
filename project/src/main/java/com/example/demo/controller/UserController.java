package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {


    private final UserService userService;


    /* 회원가입 폼으로 이동*/
    @GetMapping("/auth/join")
    public String join(){
        return "/user/user-join";
    }

    /* 회원가입 정보 검증 및 회원가입 실패,완료 처리 */
    @PostMapping("/auth/joinProc")
    public String joinProc(@Valid UserDto.RequestUserDto userDto, BindingResult bindingResult, Model model){

        /* 검증 */
            if(bindingResult.hasErrors()){

                /* 회원가입 실패 시 입력 데이터 값 유지시킴 */
                model.addAttribute("userDto",userDto);

                /* 유효성 검사를 통과하지 못한 필드와 메시지 핸들링 */
                Map<String, String> errorMap = new HashMap<>();

                for(FieldError error : bindingResult.getFieldErrors()) {
                    errorMap.put("valid_" + error.getField(), error.getDefaultMessage());
                    log.info("회원가입 실패 ! error message : " + error.getDefaultMessage());
                }
                    /* model에 담아 view resolve */
                    for (String key : errorMap.keySet()){
                        model.addAttribute(key, errorMap.get(key));
                    }

                    /* 회원가입 페이지로 리터 */
                    return "/user/user-join";
                }

                /* 회원가입 성공시 */
                userService.userJoin(userDto);
                log.info("회원가입 성공");
                return "redirect:/auth/login";
            }

    }

