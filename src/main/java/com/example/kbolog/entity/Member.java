package com.example.kbolog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    public Long id;

    @Column(nullable = false, unique = true)
    public String username;

    @Column(nullable = false)
    public  String password;

    @ManyToOne
    @JoinColumn(name = "teamId")
    public Team rootTeam;

    @Column
    public LocalDate rootdate;

}
