package com.example.kbolog.controller;

import com.example.kbolog.dto.GameDTO;
import com.example.kbolog.Request.GameRequest;
import com.example.kbolog.entity.Game;
import com.example.kbolog.repository.GameRepository;
import com.example.kbolog.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public List<Map<String, Object>> getGamesByTeamAndMonth(@RequestBody GameRequest request) {
        // 요청 데이터에 맞는 게임 목록을 가져옵니다.
        List<Object[]> gamesData = gameRepository.findGamesByTeamAndDateRange(request.getTeamId(), request.getStart(), request.getEnd());

        // Object[] 배열을 Map으로 변환하여 리스트로 반환
        return gamesData.stream()
                .map(gameData -> {
                    Map<String, Object> gameMap = new HashMap<>();
                    gameMap.put("gameId", gameData[0]);
                    gameMap.put("gameDate", gameData[1]);
                    gameMap.put("homeTeamName", gameData[2]);
                    gameMap.put("awayTeamName", gameData[3]);
                    gameMap.put("homeTeamSponsor", gameData[4]);
                    gameMap.put("awayTeamSponsor", gameData[5]);
                    return gameMap;
                })
                .collect(Collectors.toList());
    }
}
