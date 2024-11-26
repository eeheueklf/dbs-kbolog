package com.example.kbolog.repository;

import com.example.kbolog.entity.Player;
import com.example.kbolog.entity.Hitter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HitterRepository extends JpaRepository<Hitter, Long> {
    Optional<Hitter> findByPlayer(Player player);

}


