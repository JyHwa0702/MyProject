package com.example.demo.service;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.entity.User;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;


    //Create
    public void commentWrite(CommentDto commentDto,User user,Long board_id){
        Optional<Board> byId = boardRepository.findById(board_id);
        Board board = byId.get();
        BoardDto.BoardDtoBuilder boardDto = BoardDto.builder()
                .board(board);


        Optional<User> findUser = userRepository.findById(user.getId());
        Optional<Board> findBoard = boardRepository.findById(board_id);

        commentDto.setBoard(findBoard.get());
        commentDto.setUser(findUser.get());
        Comment comment = commentDto.toEntity();
        commentRepository.save(comment);





//        return "/board/detail";
    }

    //Delete
    public void commentDelete(Comment comment){
        commentRepository.delete(comment);

//        return "/board/detail";
    }

    /* CREATE */
//    @Transactional
//    public Long commentSave(String email, Long id, CommentDto commentDto){
//        Optional<User> user = userRepository.findByEmail(email);
//        Board board = boardRepository.findById(id).orElseThrow(() ->
//                new IllegalArgumentException("댓글 쓰기 실패 : 해당 게시글이 존재하지 않습니다." + id));
//
//        commentDto.setUser(user.get());
//        commentDto.setBoard(board);
//
//        Comment comment = commentDto.toEntity();
//        commentRepository.save(comment);
//
//        return commentDto.getId();
//
//    }

}
