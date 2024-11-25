package com.example.kbolog.dto;

import com.example.kbolog.entity.Player;
import com.example.kbolog.entity.Watching;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PlayerDTO {
    private Long playerId;
    private String playerName;
    private Integer playerNumber;
    private Integer playerPosition;
    private String teamName;
    private String teamSponsor;
    private LocalDate cheerDate;

    public PlayerDTO(Long playerId, String playerName, Integer playerNumber, Integer playerPosition,String teamName, String teamSponsor, LocalDate cheerDate) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerNumber = playerNumber;
        this.playerPosition = playerPosition;
        this.teamName = teamName;
        this.teamSponsor = teamSponsor;
        this.cheerDate = cheerDate;
    }
    public PlayerDTO(Player player) {
        this.playerId = player.getPlayerId();
        this.playerName = player.getPlayerName();
        this.playerNumber = player.getPlayerNumber();
        this.playerPosition = player.getPlayerPosition();
    }
}

