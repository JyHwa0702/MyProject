package com.example.demo.controller;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserDtoValidator;
import com.example.demo.validator.updateUserValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String loginForm(){

        log.info("get로그인");
        return "user/login";

    }
    @GetMapping("/user/login/fail")
    public String loginFailForm(){
        return "user/loginFail";
    }

    @GetMapping("/user/join")
    public String joinForm(Model model){
        model.addAttribute("userDto",new UserDto());
        log.info("get회원가입");
        return "user/join";
    }

    @PostMapping("/join")
    public String join(@Valid UserDto userDto, BindingResult bindingResult, Model model){

        //유저 양식 검사
        if (bindingResult.hasErrors()){
            return "user/join";
        }
        //중복검사
        userDtoValidator.validate(userDto,bindingResult);
        if (bindingResult.hasErrors()){
            return "user/join";
        }

        try{
            userService.saveUser(userDto);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage",e.getMessage());
            return "user/join";
        }
        return "redirect:/user/login";
    }
    @GetMapping("/user/update")
    public String updateForm( Authentication authentication, Model model){

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = userService.getInfoByEmail(principal);
        model.addAttribute("userDto",user);
        return "user/update";
    }

    @PutMapping("/user/update")
    public String update(@Validated(updateUserValidation.class) UserDto userDto, BindingResult bindingResult) {
        log.info("Controller update 들어옴");
        //유저 양식 검사
        if (bindingResult.hasFieldErrors()){
            log.info("bindingResult 실행됨");
            return "user/update";
        }
        log.info("userService update 전");
        userService.updateUser(userDto);
        log.info("userService update 후");
        return "index";
    }

    @GetMapping("/user/findPwd")
    public String findPwd(){
        return "/user/findPwd";
    }

    @GetMapping("/mailCheck")
    @ResponseBody
    public void mailCheckGet(String email) throws Exception{
        //뷰로부터 넘어온 데이터확인.
        log.info("이메일 데이터 전송 확인.");
        log.info("인증번호 : "+email);

        //난수 생성
        Random random = new Random();
        int checkNum = random.nextInt(888888)+111111;
        log.info("인증번호 "+checkNum);
    }


//    //!!! OAuth로 로그인시 이방식대로 하면 CastException이 발생한다.
//    @GetMapping("/form/loginInfo")
//    @ResponseBody
//    public String formLoginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){
//
//        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
//        User user = principal.getUser();
//        System.out.println(user);
//
//        User user1 = principalDetails.getUser();
//        System.out.println(user1);
//
//        return user.toString();
//    }
//
//    @GetMapping("/oauth/loginInfo")
//    @ResponseBody
//    public String oauthLoginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){
//        String result = "";
//
//        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
//        if(principal.getUser().getProvider() == null){
//            result = result + "Form 로그인 : "+principal;
//        } else {
//            result = result + "OAuth2 로그인 : "+ principal;
//        }
//        return result;
//    }
}
