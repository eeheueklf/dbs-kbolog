package com.example.kbolog.controller;

import com.example.kbolog.dto.TeamDTO;
import com.example.kbolog.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@AllArgsConstructor
public class TeamController {

    private final TeamRepository teamRepository;

    @GetMapping(value = "/api/my/team")
    public List<TeamDTO> getTeamList() {
        return teamRepository.findAllTeams().stream()
                .map(team -> new TeamDTO(
                        team.getTeamName(),
                        team.getSponsor()))
                .collect(Collectors.toList());
    }

}
