package com.example.kbolog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
public class Watching {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    public Long watchingId;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    public Game game;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public Member user;

    @Column(nullable = false, length = 255)
    public String title;

    @Column(nullable = false, length = 500)
    public String content;

    @Column(nullable = false)
    public String location;

}

