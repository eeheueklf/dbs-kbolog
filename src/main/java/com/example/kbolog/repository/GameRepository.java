package com.example.kbolog.repository;

import com.example.kbolog.entity.Game;
import com.example.kbolog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {

// 관심 팀 경기 일정 ( 한 달 )
    @Query(value = "SELECT g.* FROM game g " +
            "JOIN team home_team ON g.home_team = home_team.team_id " +
            "JOIN team away_team ON g.away_team = away_team.team_id " +
            "WHERE (home_team.team_id = :teamId OR away_team.team_id = :teamId) " +
            "AND g.game_date BETWEEN :start AND :end", nativeQuery = true)
    List<Game> findGamesByTeamAndDateRange(@Param("teamId") Integer teamId,
                                           @Param("start") LocalDate startDate,
                                           @Param("end") LocalDate endDate);

// 날짜 선택 시 경기 일정 리스트
    @Query(value = "SELECT * FROM game WHERE game_date = :gameDate", nativeQuery = true)
    List<Game> findByGameDate(@Param("gameDate") LocalDate gameDate);


//    아이디로 게임 찾기
    @Query(value = "SELECT * FROM game g WHERE g.game_id = :gameId", nativeQuery = true)
    Game findByGameId(@Param("gameId") Integer gameId);


}
