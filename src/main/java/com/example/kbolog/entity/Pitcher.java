package com.example.kbolog.entity;

import jakarta.persistence.*;


@Entity
public class Pitcher {

    @Id
    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(nullable = false)
    private Double bb; // 볼넷

    @Column(nullable = false)
    private Double era; // 평균자책
}
