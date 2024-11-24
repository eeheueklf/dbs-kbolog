package com.example.kbolog.entity;
import jakarta.persistence.*;

import java.util.Set;


@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long playerId;

    @Column(nullable = false, length = 20)
    public String playerName;

    @Column(nullable = false)
    public Integer playerNumber;

    @Column(nullable = false)
    public Integer playerPosition;

    @Column(nullable = false)
    public String hander;  // '좌투', '우투', '스위치'

    @Column(nullable = false)
    public String battingHand;  // '좌타', '우타', '스위치'

    @ManyToOne
    @JoinColumn(name = "team_id")
    public Team team;

    @OneToMany(mappedBy = "player")
    private Set<CheerPlayer> cheeredMember;
}
