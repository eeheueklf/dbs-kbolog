package com.example.kbolog.repository;

import com.example.kbolog.entity.Pitcher;
import com.example.kbolog.entity.Player;
import com.example.kbolog.entity.Hitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HitterRepository extends JpaRepository<Hitter, Long> {

    //    players - 타자 목록
    @Query(value = "SELECT * FROM hitter h WHERE h.player_id = :playerId", nativeQuery = true)
    Optional<Hitter> findByPlayer(@Param("playerId") Long playerId);

}


