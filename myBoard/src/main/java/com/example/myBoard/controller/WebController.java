package com.example.myBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index(){
        return "home";
    }

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","정영화");
        model.addAttribute("image","image/cute.jpg");

        return "/hello";
    }
}
