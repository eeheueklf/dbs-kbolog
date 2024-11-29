package com.example.kbolog.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Year;

@Entity
@Getter
public class Team {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long teamId;

    @Column(nullable = false, unique = true, length = 20)
    private String teamName;
    private String sponsor;

    @Column(nullable = false)
    private Year foundedYear;
    private String location;

}
