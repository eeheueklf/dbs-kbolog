package com.example.kbolog.repository;

import com.example.kbolog.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    //    아이디로 Player 찾기
    @Query(value = "SELECT p FROM Player p WHERE p.playerId = :playerId")
    Player findByPlayerId(@Param("playerId") Integer playerId);


    //    players - 플레이어 리스트
    @Query(value = "SELECT p.player_id, p.player_name, p.player_number, p.player_position, " +
            "CASE WHEN h.avg IS NOT NULL THEN 'Hitter' ELSE 'Pitcher' END AS player_type, " +
            "h.avg, h.ops, h.war, pi.ip, pi.era, pi.whip, " +
            "t.team_name, t.sponsor " +
            "FROM player p " +
            "LEFT JOIN hitter h ON p.player_id = h.player_id " +
            "LEFT JOIN pitcher pi ON p.player_id = pi.player_id " +
            "JOIN team t ON p.team_id = t.team_id", nativeQuery = true)
    List<Object[]> findAllPlayers();

    @Query(value = "SELECT " +
            "p.player_name, p.player_number, p.player_position, " +
            "t.sponsor , p.batting_hand, p.hander, " +
            "CASE WHEN h.avg IS NOT NULL THEN 'Hitter' ELSE 'Pitcher' END AS player_type, " +
            "h.avg, h.ops, h.war, pi.ip, pi.era, pi.whip " +
            "FROM player p " +
            "JOIN team t ON p.team_id = t.team_id " +
            "LEFT JOIN hitter h ON p.player_id = h.player_id " +
            "LEFT JOIN pitcher pi ON p.player_id = pi.player_id " +
            "WHERE p.player_id = :pId", nativeQuery = true)
    Object[] findPlayerDetailsById(@Param("pId") Integer pId);


    @Query(value = "SELECT p.player_id, COUNT(cp.member_id) AS fan_count, p.player_name, p.player_position, t.team_name, t.sponsor " +
            "FROM cheer_player cp " +
            "JOIN player p ON cp.player_id = p.player_id " +
            "JOIN team t ON p.team_id = t.team_id " +  // team 테이블을 조인
            "GROUP BY p.player_id " +
            "ORDER BY fan_count DESC " +
            "LIMIT 10",
            nativeQuery = true)
    List<Object[]> findPlayerRank();

}
