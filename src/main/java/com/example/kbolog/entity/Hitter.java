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
    public Double ops; // 출루율 + 장타율

    @Column(nullable = false)
    public Double war; // 대체 선수 대비 승리 기여도
}

