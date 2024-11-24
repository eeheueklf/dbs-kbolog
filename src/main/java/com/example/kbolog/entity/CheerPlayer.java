package com.example.kbolog.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class CheerPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="player_id")
    private Player player;

    @Column
    private LocalDate cheerDate;
}
