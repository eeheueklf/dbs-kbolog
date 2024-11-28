package com.example.kbolog.dto;

import com.example.kbolog.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
@AllArgsConstructor
public class TeamDTO {

    private Long teamId;
    private String teamName;
    private String sponsor;
    private Year foundedYear;
    private String stadiumName;

//
//    public TeamDTO(Long teamId, String teamName, String sponsor, Year foundedYear, String stadiumName) {
//        this.teamId = teamId;
//        this.teamName = teamName;
//        this.sponsor = sponsor;
//        this.foundedYear = foundedYear;
//        this.stadiumName = stadiumName;
//    }

    public TeamDTO(String teamName, String sponsor) {
        this.teamName = teamName;
        this.sponsor = sponsor;
    }
}
