package com.example.kbolog.controller;

import com.example.kbolog.dto.GameDTO;
import com.example.kbolog.Request.GameRequest;
import com.example.kbolog.entity.Game;
import com.example.kbolog.repository.GameRepository;
import com.example.kbolog.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@AllArgsConstructor
public class GameController {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    @GetMapping("/api/game")
    public List<GameDTO> getGamesByDate(@RequestParam("date")LocalDate gameDate){
        List<Game> games = gameRepository.findByGameDate(gameDate);
        return games.stream().map(GameDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/api/game/team")
    public List<GameDTO> getGamesByTeamAndMonth(@RequestBody GameRequest request) {
        // 요청 데이터에 맞는 게임 목록을 가져옵니다.
        List<Game> games = gameRepository.findGamesByTeamAndDateRange(request.getTeamId(), request.getStart(), request.getEnd());

        // 반환 값으로 GameDTO를 사용하여 데이터 변환 후 반환
        return games.stream().map(GameDTO::new).collect(Collectors.toList());
    }
}
