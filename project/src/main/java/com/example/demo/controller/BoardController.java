package com.example.demo.controller;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.dto.BoardDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.User;
import com.example.demo.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {
    private BoardService boardService;


    //게시판

    //게시글 목록

    @GetMapping({"","/list"})
    public String list(Model model, @RequestParam(value = "page",defaultValue = "1")Integer pageNum){
        log.info("board/list getmapping");
        List<BoardDto> boardList =boardService.getBoardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList",boardList);
        model.addAttribute("pageList",pageList);

        return "board/list";
    }

    //글쓰는 페이지

    @GetMapping("/post")
    public String write(Authentication authentication,Model model){
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Long id = principal.getUser().getId();
        model.addAttribute("userId",id);
        model.addAttribute("boardDto",new BoardDto());
        return "board/write";
    }

    //글을 쓴 뒤 Post로 글쓴 내용 db에 저장함
    //이후에 /list 경로로 리다이렉트 해준다

    @PostMapping("/post")
    public String write(@Valid BoardDto boardDto, BindingResult bindingResult, Authentication authentication){//@RequestParam("userId") Long userId

        //게시판 양식 검사
        if (bindingResult.hasErrors()){
            log.info("board writing PostMapping Errors");
            return "/board/write";
        }
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        boardDto.setUser(user);
        boardService.savePost(boardDto);
        return "redirect:/board/list";
    }

    //게시물 상세 페이지,{no}로 페이지 넘버를 받는다.
    //PathVariable 애너테이션 통해서 no받음.

    @GetMapping("/post/{no}")
    public String detail(@PathVariable Long no, Model model, Authentication authentication){
        BoardDto boardDto = boardService.getPost(no);
        List<CommentDto> comments = boardDto.getComments();

        log.info("해당 게시물 comments들 : "+comments.toString());

        /*댓글 관련*/
        if(comments != null && !comments.isEmpty()){
            model.addAttribute("comments",comments);

        }
        /*사용자 관련*/
        PrincipalDetails nowUser = (PrincipalDetails) authentication.getPrincipal();
        User user = nowUser.getUser();

        if (boardDto.getUser().getId().equals(user.getId())){
            model.addAttribute("writter",true);
        }

        model.addAttribute("boardDto",boardDto);
        return "board/detail";
    }

    //게시물 수정 페이지이고, {no}로 페이지 넘버를 받는다.

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable Long no, Model model){
        BoardDto boardDto = boardService.getPost(no);

        model.addAttribute("boardDto",boardDto);
        return "board/update";
    }


    //PutMapping을 통해서 게시물 수정부분 적용
    @PutMapping("/post/edit/{no}")
    public String update(@Valid BoardDto boardDto,BindingResult bindingResult,Authentication authentication){

        if(bindingResult.hasErrors()){
            log.info("board 수정폼에서 Putmapping시 binddingResult 발생");
            return "board/update";
        }

            PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
            User user = principal.getUser();
            boardDto.setUser(user);

        boardService.savePost(boardDto);

        return "redirect:/board/post/{no}";
    }

    //게시물 삭제는 deletePost 메서드를 사용하여 간단하게 삭제할 수 있다.
    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable Long no){
        boardService.deletePost(no);

        return "redirect:/board/list";
    }

    //검색
    //keyword를 view로 부터 전달받는다.
    //Service로 부터 받은 boardDtoList를 model로 전달해준다.

    @GetMapping("/search")
    public String search(@RequestParam String keyword,Model model){
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList", boardDtoList);

        return "board/list";
    }

}
