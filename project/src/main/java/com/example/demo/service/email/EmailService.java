package com.example.demo.service.email;

import com.example.demo.config.auth.EmailProperties;
import com.example.demo.dto.EmailDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.redis.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender emailSender;
    private final RedisUtil redisUtil;
    private final UserRepository userRepository;
    private final EmailProperties emailProperties;

    @Transactional
    public void sendSimpleMessage(String email){

        //임의의 authKey 생성
        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(888888)+111111);

//        emailDto.setAuthKey(authKey);
        String subject ="TRIP VIEW 코드인증메일입니다.";
        String text ="코드번호는 "+authKey+" 입니다. 입력칸에 입력해주세요.";
        SimpleMailMessage message = createMessage(email, subject,text);
        redisUtil.setDataExpire(authKey,email,60*3L);

        try{
            emailSender.send(message);
        }catch (MailException es ){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
//        return authKey;
    }

    @Transactional
    private SimpleMailMessage createMessage(String to, String subject,String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        return message;
    }

    @Transactional
    public String verifyEmail(String authKey) throws ChangeSetPersister.NotFoundException{
        String memberEmail = redisUtil.getData(authKey);

        if(memberEmail == null){
            throw new ChangeSetPersister.NotFoundException();
        }
        redisUtil.deleteData(authKey);
//        return authKey;
        return authKey;
    }


    public Optional<User> getUser(String email,Model model, String authKey) {
        String verifyAuthKey = null;
        Optional<User> userByEmail = null;
        try{
            String keyEmail = redisUtil.getData(authKey);
            log.info("try안에서의 keyEmail : "+ keyEmail);
            userByEmail = userRepository.findByEmail(keyEmail);
            log.info("try안에서의 userByEmail : "+ userByEmail);
            verifyAuthKey = verifyEmail(authKey);
        }catch(ChangeSetPersister.NotFoundException e){
            model.addAttribute("잘못된 코드입니다. 코드를 확인해주세요","codeMessage");
        }
        log.info("try,catch이후의 key값 : "+verifyAuthKey);
        log.info("try,catch이후의 key값 : "+verifyAuthKey);

        return userByEmail;
    }



//    private final JavaMailSender javaMailSender;
//
//
//    /*
//    * 이메일 전송
//    * */
//
//    @Transactional
//    public void authEmail(EmailDto emailDto){
//
//        //임의의 authKey 생성
//        Random random = new Random();
//        String authKey = String.valueOf(random.nextInt(888888)+111111);
//
//        //이메일 발송
//        sendAuthEmail(emailDto.getEmail(),authKey);
//    }
//
//    private void sendAuthEmail(String email,String authKey){
//        String subject = "제목";
//        String text = "당신의 인증번호는 "+authKey+ "입니다.<br/>";
//
//        try{
//            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"utf-8");
//            helper.setTo(email);
//            helper.setSubject(subject);
//            helper.setText(text,true); //포함된 텍스트가 HTML이라는 의미로 true
//            javaMailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
}

