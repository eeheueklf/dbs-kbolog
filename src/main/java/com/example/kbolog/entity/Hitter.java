package com.example.kbolog.entity;

import jakarta.persistence.*;

@Entity
public class Hitter {

    @Id
    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(nullable = false)
    private Double avg; // 타율

    @Column(nullable = false)
    private Double obp; // 출루율

    @Column(nullable = false)
    private Double slg; // 장타율
}

