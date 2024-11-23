package com.example.kbolog.repository;

import com.example.kbolog.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByGameDate(LocalDate gameDate);
}
