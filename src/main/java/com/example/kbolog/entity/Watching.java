package com.example.kbolog.entity;

import jakarta.persistence.*;


@Entity
public class Watching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchingId;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Member user;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

}

