package com.example.kbolog.repository;

import com.example.kbolog.entity.Game;
import com.example.kbolog.entity.Player;
import com.example.kbolog.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    //    아이디로 Player 찾기
    @Query(value = "SELECT * FROM player p WHERE p.player_id = :playerId", nativeQuery = true)
    Player findByPlayerId(@Param("playerId") Integer playerId);

    //    players - 플레이어 리스트
    @Query(value = "SELECT * FROM player", nativeQuery = true)
    List<Player> findAllPlayers();

    @Query(value = "SELECT p.player_id, COUNT(cp.member_id) AS fan_count, p.player_name, p.player_position, p.team_id, t.team_name, t.sponsor " +
            "FROM cheer_player cp " +
            "JOIN player p ON cp.player_id = p.player_id " +
            "JOIN team t ON p.team_id = t.team_id " +  // team 테이블을 조인
            "GROUP BY p.player_id " +
            "ORDER BY fan_count DESC " +
            "LIMIT 10",
            nativeQuery = true)
    List<Object[]> findPlayerRank();

}
