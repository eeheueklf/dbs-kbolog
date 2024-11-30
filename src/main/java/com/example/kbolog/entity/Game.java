package com.example.kbolog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameId;

    @Column(nullable = false)
    private LocalDate gameDate;

    @ManyToOne
    @JoinColumn(name = "home_team", nullable = false)  // 홈팀 외래키
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team", nullable = false)  // 원정팀 외래키
    private Team awayTeam;


}
