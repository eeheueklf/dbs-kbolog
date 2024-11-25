package com.example.kbolog.dto;

import com.example.kbolog.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Year;

@Getter
@AllArgsConstructor
public class TeamDTO {

    private Long teamId;
    private String teamName;
    private String sponsor;
    private Year foundedYear;
    private String stadiumName;

    // Static method to convert Team entity to TeamDTO
    public static TeamDTO fromEntity(Team team) {
        return new TeamDTO(
                team.getTeamId(),
                team.getTeamName(),
                team.getSponsor(),
                team.getFoundedYear(),
                team.getStadium() != null ? team.getStadium().getStadiumName() : null // Assuming Stadium entity has 'stadiumName'
        );
    }
}
