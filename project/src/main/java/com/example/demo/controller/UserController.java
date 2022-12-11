package com.example.demo.controller;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    @GetMapping("/user/login")
    public String loginForm(){
        log.info("get로그인");
        return "user/login";

    }

    @GetMapping("/user/join")
    public String joinForm(){
        log.info("get회원가입");
        return "user/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute UserDto userDto){

        String encodePwd = bCryptPasswordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodePwd);
        User user = User.userDetailRegister()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .role(Role.ROLE_USER)
                .password(encodePwd)
                .build();

        userRepository.save(user);
        return "redirect:/user/login";
    }
//    @GetMapping("/user/updateForm")
//    public String updateForm(Authentication authentication, Model model){
//
//        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
//        User user = userService.getInfoByEmail(principal);
//        model.addAttribute("user",user);
//        return "user/update";
//    }
    @GetMapping("/user/updateForm/{email}")
    public String updateForm(@PathVariable String email,Model model){
        log.info("컨트롤러에서 받는 email "+email);
        UserDto userDto = userService.getPost(email);
        model.addAttribute("userDto",userDto);
        return "user/update";


//    @PutMapping("/user/update/{id}")
//    public String update(@PathVariable User updatedUser) {


//        return "redirect:/";
    }


    @GetMapping("/user")
    @ResponseBody
    public String user(){
        return "user";
    }

    @GetMapping("/manager")
    @ResponseBody
    public String manager(){
        return "manager";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin(){
        return "admin";
    }

    //!!! OAuth로 로그인시 이방식대로 하면 CastException이 발생한다.
    @GetMapping("/form/loginInfo")
    @ResponseBody
    public String formLoginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        System.out.println(user);

        User user1 = principalDetails.getUser();
        System.out.println(user1);

        return user.toString();
    }

    @GetMapping("/oauth/loginInfo")
    @ResponseBody
    public String oauthLoginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String result = "";

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        if(principal.getUser().getProvider() == null){
            result = result + "Form 로그인 : "+principal;
        } else {
            result = result + "OAuth2 로그인 : "+ principal;
        }
        return result;
    }
}
