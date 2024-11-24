package com.example.kbolog.repository;

import com.example.kbolog.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM Game g WHERE (g.homeTeam.teamId = :teamId OR g.awayTeam.teamId = :teamId) " +
            "AND g.gameDate BETWEEN :start AND :end")
    List<Game> findGamesByTeamAndDateRange(@Param("teamId") Long teamId,
                                           @Param("start") LocalDate startDate,
                                           @Param("end") LocalDate endDate);
    List<Game> findByGameDate(LocalDate gameDate);

}

