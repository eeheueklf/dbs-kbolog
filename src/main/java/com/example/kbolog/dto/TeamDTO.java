package com.example.kbolog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Getter
@Setter
@AllArgsConstructor
public class TeamDTO {

    private Integer teamId;
    private String teamName;
    private String sponsor;
    private Year foundedYear;
    private String stadiumName;


    public TeamDTO(String teamName, String sponsor) {
        this.teamName = teamName;
        this.sponsor = sponsor;
    }
}
