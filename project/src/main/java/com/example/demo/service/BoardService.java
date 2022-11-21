package com.example.demo.service;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {

    private BoardRepository boardRepository;

    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4; // 한 페이지에 존재하는 게시글 수

    //Entity ->Dto 로 변환
    private BoardDto convertEntityToDto(Board board){
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdDate(board.getCreatedDate())
                .modifiedDate(board.getModifiedDate())
                .build();
    }


    @Transactional
    public List<BoardDto> getBoardlist(Integer pageNum){
        Page<Board> page = boardRepository.findAll(PageRequest.of(
                pageNum-1,PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC,"createdDate")));

        List<Board> boardEntities = page.getContent();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boardEntities){
            boardDtoList.add(this.convertEntityToDto(board));
        }
        return boardDtoList;
    }

    @Transactional
    public BoardDto getPost(Long id){
        //Optional : NPE(NullPointerException)방지
        Optional<Board> boardWrapper =boardRepository.findById(id);
        boardWrapper.get()
    }
}
