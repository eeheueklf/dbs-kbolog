package com.example.kbolog.repository;

import com.example.kbolog.entity.Pitcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PitcherRepository extends JpaRepository<Pitcher, Long> {

//    players - 투수 목록
@Query(value = "SELECT * FROM pitcher p WHERE p.player_id = :playerId", nativeQuery = true)
Optional<Pitcher> findByPlayer(@Param("playerId") Long playerId);
}
