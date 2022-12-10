package com.example.demo.controller;

import com.example.demo.dto.BoardDto;
import com.example.demo.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String write(){
        return "board/write";
    }

    //글을 쓴 뒤 Post로 글쓴 내용 db에 저장함
    //이후에 /list 경로로 리다이렉트 해준다

    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/board/list";
    }

    //게시물 상세 페이지,{no}로 페이지 넘버를 받는다.
    //PathVariable 애너테이션 통해서 no받음.

    @GetMapping("/post/{no}")
    public String detail(@PathVariable Long no, Model model){
        BoardDto boardDto = boardService.getPost(no);

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
    public String update(BoardDto boardDto){
        boardService.savePost(boardDto);

        return "redirect:/board/list";
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

    @GetMapping("/board/search")
    public String search(@RequestParam String keyword,Model model){
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList", boardDtoList);

        return "board/list";
    }

}
