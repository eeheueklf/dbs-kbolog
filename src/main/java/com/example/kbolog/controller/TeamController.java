package com.example.kbolog.controller;

import com.example.kbolog.dto.TeamDTO;
import com.example.kbolog.entity.Team;
import com.example.kbolog.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@AllArgsConstructor
public class TeamController {

    private final TestService testService;

    @GetMapping(value="/api/my/team")
    public List<TeamDTO> getTeamList(){
        // Fetch list of teams from service and map them to TeamDTO
        return testService.getTeamList().stream()
                .map(TeamDTO::fromEntity) // Convert Team entity to TeamDTO
                .collect(Collectors.toList());
    }

}
