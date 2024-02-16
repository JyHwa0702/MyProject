package com.example.myBoard.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id",nullable = false)
    private Long id;

    @Column(name = "board_title", nullable = false)
    private String title;

    @Column(name = "board_content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "board_created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "board_update_at",nullable = false)
    private LocalDateTime updateAt;

    @Column(name = "board_admin_views",nullable = false)
    private int adminViews;

    @Column(name = "board_user_views",nullable = false)
    private int userViews;

    @Column(name = "board_likes",nullable = false)
    private int likes;

    @Enumerated(EnumType.STRING)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;
}
