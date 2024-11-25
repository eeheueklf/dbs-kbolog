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
    public Long id;

    @Column(nullable = false, unique = true)
    public String username;

    @Column(nullable = false)
    public  String password;

    @ManyToOne
    @JoinColumn(name = "team_id")
    public Team rootTeam;

    @Column
    public LocalDate rootdate;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private Set<CheerPlayer> cheerPlayers;

}
