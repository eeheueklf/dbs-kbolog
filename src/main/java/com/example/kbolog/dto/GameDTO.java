package com.example.kbolog.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GameDTO {

    private Integer gameId;
    private LocalDate gameDate;
    private String homeTeamName;
    private String awayTeamName;
    private String homeTeamSponsor;
    private String awayTeamSponsor;

    public GameDTO(com.example.kbolog.entity.Game game) {
        this.gameId = game.getGameId();
        this.gameDate = game.getGameDate();
        this.homeTeamName = game.getHomeTeam() != null ? game.getHomeTeam().getTeamName() : null;
        this.awayTeamName = game.getAwayTeam() != null ? game.getAwayTeam().getTeamName() : null;
        this.homeTeamSponsor = game.getHomeTeam() != null ? game.getHomeTeam().getSponsor() : null;
        this.awayTeamSponsor = game.getAwayTeam() != null ? game.getAwayTeam().getSponsor() : null;
    }
}
