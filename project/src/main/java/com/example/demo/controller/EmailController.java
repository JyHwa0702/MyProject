package com.example.demo.controller;

import com.example.demo.dto.EmailDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmailController {

    private final EmailService emailService;
    private final UserRepository userRepository;

    @PostMapping("/emailConfirm")
    public String home3(@Valid EmailDto emailDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "/user/findPwd";
        }
        Optional<User> byEmail = userRepository.findByEmail(emailDto.getEmail());
        if (byEmail.isPresent()) {
            emailService.sendSimpleMessage(emailDto);
            model.addAttribute("Message","인증코드를 해당 메일로 보냈습니다.");
            return "/user/findPwd";
        } else if (byEmail.isEmpty()) {
            model.addAttribute("Message","해당 이메일 유저가 존재하지 않습니다.");
            return "/user/findPwd";
        }
        return "/";
    }

//    private final EmailService emailService;
//
//    @PostMapping("/emailConfirm")
//    public ResponseEntity<Void> mailConfirm( @Valid EmailDto emailDto) throws MessagingException, UnsupportedEncodingException{
//        log.info("emailDto.getEmail = "+emailDto.getEmail());
//        emailService.authEmail(emailDto);
//        return ResponseEntity.ok().build();

}
