package com.example.kbolog.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Hitter {

    @Id
    private Long playerId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "player_id")
    public Player player;

    @Column(nullable = false)
    public Double avg; // 타율

    @Column(nullable = false)
    public Double obp; // 출루율

    @Column(nullable = false)
    public Double slg; // 장타율
}

