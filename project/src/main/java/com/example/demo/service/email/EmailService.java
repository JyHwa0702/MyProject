package com.example.demo.service.email;

import com.example.demo.dto.EmailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;


    /*
    * 이메일 전송
    * */

    @Transactional
    public void authEmail(EmailDto emailDto){

        //임의의 authKey 생성
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(888888)+111111);

        //이메일 발송
        sendAuthEmail(emailDto.getEmail(),authKey);
    }

    private void sendAuthEmail(String email,String authKey){
        String subject = "제목";
        String text = "당신의 인증번호는 "+authKey+ "입니다.<br/>";

        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(text,true); //포함된 텍스트가 HTML이라는 의미로 true
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

