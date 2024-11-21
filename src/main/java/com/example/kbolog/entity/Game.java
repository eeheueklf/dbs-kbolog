package com.example.kbolog.entity;

import jakarta.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    @Column(nullable = false)
    private String gameDate;

    @ManyToOne
    @JoinColumn(name = "home_team", nullable = false)  // 홈팀 외래키
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team", nullable = false)  // 원정팀 외래키
    private Team awayTeam;


}
