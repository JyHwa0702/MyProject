package com.example.demo.dto;

import com.example.demo.entity.Board;
import com.example.demo.entity.User;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class BoardDto {

    private Long id;

    private String writer;
    private String title;
    private String content;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private int view;
    private User user;
    private List<CommentDto> comments;

    public Board toEntity(){
        Board board = Board.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .user(user)
                .build();
        return board;
    }

    @Builder
    public BoardDto(Board board){
        this.id=board.getId();
        this.writer= board.getWriter();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
        this.view = board.getView();
        this.user = board.getUser();
        this.comments = board.getComments().stream().map(CommentDto::new).collect(Collectors.toList());


    }
}
