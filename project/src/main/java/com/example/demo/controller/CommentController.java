package com.example.demo.controller;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.BoardDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.entity.User;
import com.example.demo.repository.BoardRepository;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private final BoardRepository boardRepository;


    //댓글 저장
    @PostMapping("/post/{board_id}/comment/write")
    public String commentWrite(CommentDto commentDto, Model model, @PathVariable Long board_id
            , @AuthenticationPrincipal PrincipalDetails principalDetails){

        Optional<Board> byId = boardRepository.findById(board_id);

        Board board = byId.get();
        BoardDto boardDto = BoardDto.builder()
                .board(board)
                .build();

        List<CommentDto> comments = boardDto.getComments();

        model.addAttribute("boardDto",boardDto);
        model.addAttribute("comments",comments);

        User user = principalDetails.getUser();
        String email = user.getEmail();

        log.info("CommentController 내의 commentWrite부분에서의 현재 로그인된 유저의 Email값, 저장 전 : "+email);
        commentDto.setUser(user);
        commentService.commentWrite(commentDto,board_id);
        log.info("CommentController 내 댓글 저장로직 후");

        if (boardDto.getUser().getId().equals(user.getId())){
            model.addAttribute("writter",true);
            log.info("commentController commentWrite 부분 게시글 유저 id if문 실행됐을때.");
        }


        return "redirect:/board/post/"+boardDto.getId();
    }

    @PostMapping("/post/{board_id}/comment/delete")
    public String commentDelete(Comment comment,Model model,@PathVariable Long board_id
            ,@AuthenticationPrincipal PrincipalDetails principalDetails){

        commentService.commentDelete(comment);
//        User user = principalDetails.getUser();
//        String email = user.getEmail();
//        log.info("CommentController 내의 commentDelete부분에서의 현재 로그인된 유저의 email값 : "+email);
//        commentService.commentDelete(comment);
//
//        Optional<Board> byId = boardRepository.findById(board_id);
//        Board board = byId.get();
//        BoardDto boardDto = BoardDto.builder()
//                .board(board)
//                .build();
//
//        if (boardDto.getUser().getId().equals(user.getId())){
//            model.addAttribute("writter",true);
//        }
        return "redirect:/board/post/"+board_id.toString();
    }
}
