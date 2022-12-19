package com.example.demo.service.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    /*
    createCod : 인증번호 8자리를 무작위로 생성하는 기능
    createEmailForm: email 전송에 필요한 정보들을 설정하는 기능. 여기서 전송하고자하는 message의 context에 전송하고자하는 html파일을
                타임리프를 이용하여 html의 java 코드가 분리되도록 구성하였다.
    sendEmail : 실제 메일을 전송하는 기능
     */

    //의존성 주입을 통해 필요한 객체 가져오기
    private final JavaMailSender emailSender;

    //타임리프를 사용하기 위한 객체를 의존성 주입 가져오기
    private final SpringTemplateEngine templateEngine;
    private String authNum; // 인증코

    //랜덤 인증코드 생성
    public void createCode(){
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for(int i=0; i<8; i++){
            int index = random.nextInt(3);

            switch (index){
                case 0:
                    key.append((char)((int)random.nextInt(26)+97));
                    break;

                case 1:
                    key.append((char)((int)random.nextInt(26)+65));
                    break;
                case 2:

                    key.append(random.nextInt(10));
                    break;
            }
        }
        authNum = key.toString();
    }

    //메일 양식작성
    public MimeMessage createEmailForm(String email)throws MessagingException, UnsupportedEncodingException{

        createCode(); //인증 코드생성
        String setFrom = "JyHwa0702@gmail.com"; //EmailConfig에 설정한 자신의 이메일주소(보내는 사람)
        String toEmail = email; //받는 사람
        String title = "TRIPVIEW 회원 인증번호"; // 제목

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO,email); //   보낼 이메일 설정
        message.setSubject(title);  //제목 설정
        message.setFrom(setFrom);   //보내는 이메일
        message.setText(setContext(authNum), "utf-8","html");

        return message;
    }

    //실제 메일 전송
    public String sendEmail(String toEmail)throws MessagingException,UnsupportedEncodingException{

        //메일 전송에 필요한 정보설정
        MimeMessage emailForm = createEmailForm(toEmail);

        //실제 메일 전송
        emailSender.send(emailForm);

        return authNum; // 인증코드 반환
    }

    //타임리프를 이용한 context 설정
    public String setContext(String code){
        Context context = new Context();
        context.setVariable("code",code);
        return templateEngine.process("mail",context);  //mail.html
    }

}
