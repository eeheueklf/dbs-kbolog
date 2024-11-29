package com.example.kbolog.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private  String password;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team rootTeam;

    @Column
    private LocalDate rootdate;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private Set<CheerPlayer> cheerPlayers;

}
