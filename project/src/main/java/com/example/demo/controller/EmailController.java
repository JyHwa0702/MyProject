package com.example.demo.controller;


import com.example.demo.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class EmailController{
    private final EmailService emailService;

    @PostMapping("/emailConfirm")
    public String mailConfirm(EmailAuthDto emailDto, BindingResult bindingResult) throws MessagingException, UnsupportedEncodingException{
        String authCode = emailService.sendEmail(emailDto.getEmail());
        return authCode;

    }

}