package com.example.demo.dto;

import com.example.demo.entity.Board;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardDto {

    private Long id;

    private String writer;
    private String title;
    private String content;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity(){
        Board board = Board.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        return board;
    }

    @Builder
    public BoardDto(Long id, String writer, String title, String content,
                    LocalDateTime createdDate,LocalDateTime modifiedDate){
        this.id=id;
        this.writer=writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;


    }
}
