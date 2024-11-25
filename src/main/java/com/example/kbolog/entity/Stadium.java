package com.example.kbolog.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Stadium {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    public Long stadiumId;

    @Column(nullable = false, unique = true, length = 20)
    public String stadiumName;

    @Column(nullable = false, length = 4)
    public String location;

}
