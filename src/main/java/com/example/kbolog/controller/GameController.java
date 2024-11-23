package com.example.kbolog.controller;

import com.example.kbolog.entity.Game;
import com.example.kbolog.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/game")
@AllArgsConstructor
public class GameController {

    private final GameRepository gameRepository;

    @GetMapping
    public List<Game> getGamesByDate(@RequestParam("date")LocalDate date){
        return gameRepository.findByGameDate(date);
    }

}
