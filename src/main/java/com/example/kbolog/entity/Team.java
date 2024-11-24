package com.example.kbolog.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Year;

@Entity
@Getter
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    public Long teamId;

    @Column(nullable = false, unique = true, length = 20)
    public String teamName;
    public String sponsor;

    @Column(nullable = false)
    public Year foundedYear;

    @ManyToOne
    @JoinColumn(name = "stadium_id", nullable = false)
    public Stadium stadium;


}
