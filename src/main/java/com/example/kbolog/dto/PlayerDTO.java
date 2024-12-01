package com.example.kbolog.dto;

import com.example.kbolog.entity.Player;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PlayerDTO {
    private Integer playerId;
    private String playerName;
    private Integer playerNumber;
    private Integer playerPosition;
    private String teamName;
    private String teamSponsor;
    private LocalDate cheerDate;

    private String hander;
    private String battingHand;

    private String playerType;
    private Double ip;
    private Double era;
    private Double whip;

    private Double avg;
    private Double ops;
    private Double war;

    private Long fanCount;

    public PlayerDTO(Integer playerId, Long fanCount, String playerName, Integer playerPosition, String teamName, String teamSponsor) {
        this.playerId = playerId;
        this.fanCount = fanCount;
        this.playerName = playerName;
        this.playerPosition = playerPosition;
        this.teamName = teamName;
        this.teamSponsor = teamSponsor;
    }

    public PlayerDTO(Integer playerId, String playerName, Integer playerNumber, Integer playerPosition,String teamName, String teamSponsor, LocalDate cheerDate) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerNumber = playerNumber;
        this.playerPosition = playerPosition;
        this.teamName = teamName;
        this.teamSponsor = teamSponsor;
        this.cheerDate = cheerDate;
    }

    public PlayerDTO() {
        this.playerName = playerName;
        this.playerNumber = playerNumber;
        this.playerPosition = playerPosition;
        this.teamSponsor = teamSponsor;
        this.battingHand = battingHand;
        this.hander = hander;
        this.playerType = playerType;
        this.avg = avg;
        this.ops = ops;
        this.war = war;
        this.ip = ip;
        this.era = era;
        this.whip = whip;
    }
}

