package com.example.demo.service.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Random;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender emailSender;

    public static final String ePw = createKey();

    @Transactional
    private MimeMessage createdMessage(String to) throws  Exception{
        log.info("보내는 대상 : "+to);
        log.info("인증번호 : "+ePw);
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO,to); //보내는 대상
        message.setSubject("이메일 인증 테스트"); //제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요 정영화입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 복사해 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg,"utf-8","html"); //내용
        message.setFrom(new InternetAddress("JyHwa0702@gmail.com","JYoungHwa")); //보내는 사람

        return message;
    }

    @Transactional
    private static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for(int i =0; i<8; i++){
            int index = rnd.nextInt(3); //0~2까지 랜덤 숫자

            switch (index){
                case 0:
                    key.append((char) (rnd.nextInt(26)+97));
                    // a~z , (char)98 = 'b'
                    break;
                case 1:
                    key.append((char) (rnd.nextInt(26)+65));
                    //A~Z
                case 2:
                    key.append(rnd.nextInt(10));
                    //0~9
                    break;
            }
        }
        return key.toString();
    }

    @Override
    public String sendSimpleMessage(String to) throws Exception {

        MimeMessage message = createdMessage(to);
        try{
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }
}
