package com.example.kbolog.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
public class Pitcher {

    @Id
    private Long playerId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "player_id")
    public Player player;

    @Column(nullable = false)
    public Double ip; // 이닝

    @Column(nullable = false)
    public Double era; // 평균자책점

    @Column(nullable = false)
    public Double whip; // 이닝당 출루 허용률

}
