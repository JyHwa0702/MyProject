package com.example.demo.controller;

import com.example.demo.dto.EmailDto;
import com.example.demo.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/emailConfirm")
    public ResponseEntity<Void> mailConfirm( @Valid EmailDto emailDto) throws MessagingException, UnsupportedEncodingException{
        log.info("emailDto.getEmail = "+emailDto.getEmail());
        emailService.authEmail(emailDto);
        return ResponseEntity.ok().build();
    }
}
