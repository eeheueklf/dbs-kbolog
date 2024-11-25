package com.example.kbolog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class CheerPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference // 직렬화에서 제외
    private Member member;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column
    private LocalDate cheerDate;
}

