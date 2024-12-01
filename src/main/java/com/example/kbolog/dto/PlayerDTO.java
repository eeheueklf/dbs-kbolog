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

    private Double ip;
    private Double era;
    private Double whip;

    private Double avg;
    private Double ops;
    private Double war;

    private Integer teamId;
    private Long fanCount;

    public PlayerDTO(Integer playerId, Long fanCount, String playerName, Integer playerPosition, Integer teamId, String teamName, String teamSponsor) {
        this.playerId = playerId;
        this.fanCount = fanCount;
        this.playerName = playerName;
        this.playerPosition = playerPosition;
        this.teamId = teamId;
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
    public void setPitcherStats(Double ip, Double era, Double whip) {
        this.ip = ip;
        this.era = era;
        this.whip = whip;
    }

    public void setHitterStats(Double avg, Double ops, Double war) {
        this.avg = avg;
        this.ops = ops;
        this.war = war;
    }

    public PlayerDTO(Player player) {
        this.playerId = player.getPlayerId();
        this.playerName = player.getPlayerName();
        this.playerNumber = player.getPlayerNumber();
        this.playerPosition = player.getPlayerPosition();
        this.teamName = player.getTeam().getTeamName();
        this.teamSponsor = player.getTeam().getSponsor();
        this.battingHand = player.getBattingHand();
        this.hander = player.getHander();
    }
}

