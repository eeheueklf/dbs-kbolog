package com.example.kbolog.controller;

import com.example.kbolog.entity.Team;
import com.example.kbolog.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@AllArgsConstructor
public class TeamController {

    private final TestService testService;

    @GetMapping(value="/api/my/team")
    public List<Team> getTeamList(){
        return testService.getTeamList();
    }


}
