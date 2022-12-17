package com.example.demo.controller;

import com.example.demo.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/emailConfirm")
    public String emailConfirm(@RequestParam String email) throws Exception{
        String confirm = emailService.sendSimpleMessage(email);

        return confirm;
    }
}
