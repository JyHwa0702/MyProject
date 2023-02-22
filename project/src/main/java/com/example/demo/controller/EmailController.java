package com.example.demo.controller;

import com.example.demo.dto.EmailDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class EmailController {

    private final EmailService emailService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/emailConfirm")
    public String emailConfirm(@Valid EmailDto emailDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "/user/findPwd";
        }
        String email = emailDto.getEmail();
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            emailService.sendSimpleMessage(email);
            model.addAttribute("Message","인증코드를 해당 메일로 보냈습니다.");
            model.addAttribute("codeConfirm",true);


            model.addAttribute("email",email); // 입력한 email input 히든으로 넘겨줄 예
            return "/user/findPwd";
        } else if (byEmail.isEmpty()) {
            model.addAttribute("Message","해당 이메일 유저가 존재하지 않습니다.");
//            model.addAttribute("codeConfirm",false);
            return "/user/findPwd";
        }
        return "/";
    }


    @PostMapping("/emailConfirm/code")
    public String emailConfirmCode(String inputEmail,String authKey,Model model) throws ChangeSetPersister.NotFoundException {

        Optional<User> byEmail = emailService.getUser(inputEmail,authKey,model);
        log.info("email Controller에서의 byemail : " + byEmail.get());
        String findEmail = byEmail.get().getEmail();
        log.info("findEmail = "+ findEmail);

        log.info("requestEmail = "+ inputEmail);


        boolean equalsEmail = findEmail.equals(inputEmail);
        log.info("equalsEmail : "+String.valueOf(equalsEmail));
        if (equalsEmail){
            model.addAttribute("email",findEmail);
            return "/user/OkFindPwd";
        }

        return "/user/findPwd";
    }

    @PutMapping("/emailConfirm/code/ok")
    public String OkfindPwd(String email, String password,String password2,Model model){
        Optional<User> byEmail = userRepository.findByEmail(email);
        boolean comparePwd = password.equals(password2);
        log.info("비밀번호 1,2번 비교 값 : "+comparePwd);
        if (comparePwd){ //비밀번호 1,2번 일치로 변경할예정.
            log.info("비밀번호 변경할 비밀번호 : "+password);
            String encodePwd = bCryptPasswordEncoder.encode(password);
            User user = byEmail.get().changePwd(encodePwd);
            userRepository.save(user);
            return "redirect:/user/login";
        } else{
            model.addAttribute("email",email);
            model.addAttribute("message","비밀번호 확인이 일치하지 않습니다!");
            return "/emailConfirm/code/ok";
        }
    }



//    private final EmailService emailService;
//
//    @PostMapping("/emailConfirm")
//    public ResponseEntity<Void> mailConfirm( @Valid EmailDto emailDto) throws MessagingException, UnsupportedEncodingException{
//        log.info("emailDto.getEmail = "+emailDto.getEmail());
//        emailService.authEmail(emailDto);
//        return ResponseEntity.ok().build();

}
