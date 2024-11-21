package com.example.kbolog.entity;

import jakarta.persistence.*;
import java.time.Year;

@Entity
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    public Long teamId;

    @Column(nullable = false, unique = true, length = 20)
    public String teamName;

    @Column(nullable = false)
    public Year foundedYear;

    @ManyToOne
    @JoinColumn(name = "stadiumId", nullable = false)
    public Stadium stadium;


}
