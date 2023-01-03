package com.example.demo.controller;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.EmailDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserDtoValidator;
import com.example.demo.validator.updateUserValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserDtoValidator userDtoValidator;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    @GetMapping("/user/login")
    public String loginForm() {

        log.info("get로그인");
        return "user/login";

    }

    @GetMapping("/user/login/fail")
    public String loginFailForm() {
        return "user/loginFail";
    }

    @GetMapping("/user/join")
    public String joinForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        log.info("get회원가입");
        return "user/join";
    }

    @PostMapping("/join")
    public String join(@Valid UserDto userDto, BindingResult bindingResult, Model model) {

        //유저 양식 검사
        if (bindingResult.hasErrors()) {
            return "user/join";
        }
        //중복검사
        userDtoValidator.validate(userDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user/join";
        }

        try {
            userService.saveUser(userDto);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "user/join";
        }
        return "redirect:/user/login";
    }

    @GetMapping("/user/update")
    public String updateForm(Authentication authentication, Model model) {

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = userService.getInfoByEmail(principal);
        model.addAttribute("userDto", user);
        return "user/update";
    }

    @PutMapping("/user/update")
    public String update(@Validated(updateUserValidation.class) UserDto userDto, BindingResult bindingResult) {
        log.info("Controller update 들어옴");
        //유저 양식 검사
        if (bindingResult.hasFieldErrors()) {
            log.info("bindingResult 실행됨");
            return "user/update";
        }
        log.info("userService update 전");
        userService.updateUser(userDto);
        log.info("userService update 후");
        return "index";
    }

    @GetMapping("/user/findPwd")
    public String findPwd(Model model) {
        model.addAttribute("emailDto", new EmailDto());

        model.addAttribute("codeConfirm",false);
        return "/user/findPwd";
    }
}




