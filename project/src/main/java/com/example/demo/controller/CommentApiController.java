package com.example.demo.controller;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.BoardDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.User;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
//@RequestMapping("/api")
public class CommentApiController {
    private final CommentService commentService;

    /* CREATE */
    @PostMapping("/board/{id}/comments")
    public String commentSave(@PathVariable Long id, CommentDto commentDto,
                              Authentication authentication){
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User nowUser = principal.getUser();
//        return ResponseEntity.ok(commentService.commentSave(nowUser.getEmail(),id,commentDto));
        Long aLong = commentService.commentSave(nowUser.getEmail(), id, commentDto);
        return "board/detail";
    }
}
