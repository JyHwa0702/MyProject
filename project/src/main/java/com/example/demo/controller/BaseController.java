package com.example.demo.controller;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.config.auth.SessionUser;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
@Slf4j
public class BaseController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, Authentication authentication){
        SessionUser user = (SessionUser)httpSession.getAttribute("user");
        if (authentication != null){
            PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
            log.info(principal.toString());
            User user1 = principal.getUser();
            log.info(user1.toString());
            UserDto userDto = UserDto.builder()
                    .id(user1.getId())
                    .email(user1.getEmail())
                    .role(user1.getRole())
                    .username(user1.getUsername())
                    .build();

            model.addAttribute("userDto",userDto);
        }
        if(user != null){

            model.addAttribute("userName",user.getName());
            model.addAttribute("userImg",user.getPicture());
        }

        return "index";
    }
}
