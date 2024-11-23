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
    public Long gameId;

    @Column(nullable = false)
    public LocalDate gameDate;

    @ManyToOne
    @JoinColumn(name = "home_team", nullable = false)  // 홈팀 외래키
    public Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team", nullable = false)  // 원정팀 외래키
    public Team awayTeam;


}
