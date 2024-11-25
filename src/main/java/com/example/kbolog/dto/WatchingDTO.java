package com.example.kbolog.dto;

import com.example.kbolog.entity.Watching;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WatchingDTO {

    private Long watchingId;
    private String homeTeam;
    private String awayTeam;
    private String homeTeamSponsor;
    private String awayTeamSponsor;
    private String title;
    private String content;
    private String location;
    private LocalDate watchingDate;

    public WatchingDTO(Watching watching) {
        this.watchingId = watching.getWatchingId();
        this.homeTeam = watching.getGame().getHomeTeam().getTeamName() ;
        this.awayTeam = watching.getGame().getAwayTeam().getTeamName() ;
        this.homeTeamSponsor = watching.getGame().getHomeTeam().getSponsor() ;
        this.awayTeamSponsor = watching.getGame().getAwayTeam().getSponsor() ;
        this.title = watching.getTitle();
        this.content = watching.getContent();
        this.location = watching.getLocation();
        this.watchingDate = watching.getGame().getGameDate();
    }
}
