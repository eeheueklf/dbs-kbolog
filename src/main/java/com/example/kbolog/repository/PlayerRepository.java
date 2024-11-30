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
}
