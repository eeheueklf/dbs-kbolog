package com.example.kbolog.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
public class Pitcher {

    @Id
    private Integer playerId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column(nullable = false)
    private Double ip; // 이닝

    @Column(nullable = false)
    private Double era; // 평균자책점

    @Column(nullable = false)
    private Double whip; // 이닝당 출루 허용률

}
