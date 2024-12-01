package com.example.kbolog.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;


@Entity
@Getter
@JsonIgnoreProperties({"cheeredMember"})
@Inheritance(strategy = InheritanceType.JOINED)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;

    @Column(nullable = false, length = 20)
    private String playerName;

    @Column(nullable = false)
    private Integer playerNumber;

    @Column(nullable = false)
    private Integer playerPosition;

    @Column(nullable = false)
    private String hander;  // '좌투', '우투', '스위치'

    @Column(nullable = false)
    private String battingHand;  // '좌타', '우타', '스위치'

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @OneToMany(mappedBy = "player")
    private Set<CheerPlayer> cheeredMember;
}
