package com.example.kbolog.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Hitter {

    @Id
    private Integer playerId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(nullable = false)
    private Double avg; // 타율

    @Column(nullable = false)
    private Double ops; // 출루율 + 장타율

    @Column(nullable = false)
    private Double war; // 대체 선수 대비 승리 기여도
}

