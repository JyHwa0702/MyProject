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

    @PostMapping("/emailConfirm")
    public String emailConfirm(@Valid EmailDto emailDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "/user/findPwd";
        }
        String email = emailDto.getEmail();
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            emailService.sendSimpleMessage(emailDto);
            model.addAttribute("Message","인증코드를 해당 메일로 보냈습니다.");
            model.addAttribute("codeConfirm",true);


            model.addAttribute("email",email); // 입력한 email input 히든으로 넘겨줄 예
            return "/user/findPwd";
        } else if (byEmail.isEmpty()) {
            model.addAttribute("Message","해당 이메일 유저가 존재하지 않습니다.");
            model.addAttribute("codeConfirm",false);
            return "/user/findPwd";
        }
        return "/";
    }

    @ResponseBody
    @PostMapping("/emailConfirm/code")
    public String emailConfirmCode(EmailDto emailDto,Model model,String authKey) throws ChangeSetPersister.NotFoundException {

        Optional<User> byEmail = emailService.getUser(emailDto,model, authKey);
        String email = emailDto.getEmail();
        boolean equalsEmail = byEmail.get().equals(email);
        log.info(String.valueOf(equalsEmail));
        if (equalsEmail){
            return "비밀번호 변경창으로 이동";
        }
        return "마지막 리턴";
    }



//    private final EmailService emailService;
//
//    @PostMapping("/emailConfirm")
//    public ResponseEntity<Void> mailConfirm( @Valid EmailDto emailDto) throws MessagingException, UnsupportedEncodingException{
//        log.info("emailDto.getEmail = "+emailDto.getEmail());
//        emailService.authEmail(emailDto);
//        return ResponseEntity.ok().build();

}
