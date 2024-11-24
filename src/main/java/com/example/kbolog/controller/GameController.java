package com.example.kbolog.controller;

import com.example.kbolog.entity.Game;
import com.example.kbolog.entity.Team;
import com.example.kbolog.repository.GameRepository;
import com.example.kbolog.repository.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@AllArgsConstructor
public class GameController {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    @GetMapping("/api/game")
    public List<Game> getGamesByDate(@RequestParam("date")LocalDate date){
        return gameRepository.findByGameDate(date);
    }
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @PostMapping("/api/game/team")
    public List<Game> getGamesByTeamAndMonth(@RequestBody GameRequest request) {
        return gameRepository.findGamesByTeamAndDateRange(request.teamId, request.start, request.end);
    }
    @Getter
    @Setter
    public static class GameRequest {
        private Long teamId;
        private LocalDate start;
        private LocalDate end;

        // Getters and Setters
        public Long getTeamId() {
            return teamId;
        }

        public void setTeamId(Long teamId) {
            this.teamId = teamId;
        }

        public LocalDate getStart() {
            return start;
        }

        public void setStart(LocalDate start) {
            this.start = start;
        }

        public LocalDate getEnd() {
            return end;
        }

        public void setEnd(LocalDate end) {
            this.end = end;
        }
}
}
