package com.example.kbolog.dto;

import com.example.kbolog.entity.CheerPlayer;
import com.example.kbolog.entity.Member;
import com.example.kbolog.entity.Player;

import java.time.LocalDate;

public class CheerPlayerDTO {
    private Long playerId;
    private String playerName;
    private Integer playerNumber;
    private Integer playerPosition;
    private String hander;
    private String battingHand;
    private String teamName; // Team 이름 (옵션)
    private String sponsor;
    private LocalDate cheerDate;

    public CheerPlayerDTO(CheerPlayer cheerPlayer) {
        Player player = cheerPlayer.getPlayer();
        this.playerId = player.getPlayerId();
        this.playerName = player.getPlayerName();
        this.playerNumber = player.getPlayerNumber();
        this.playerPosition = player.getPlayerPosition();
        this.hander = player.getHander();
        this.battingHand = player.getBattingHand();
        this.teamName = player.getTeam().getTeamName(); // 필요시 가져옴
        this.sponsor = player.getTeam().getSponsor();
        this.cheerDate = cheerPlayer.getCheerDate();
    }

}

