package com.example.kbolog.repository;

import com.example.kbolog.entity.Pitcher;
import com.example.kbolog.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PitcherRepository extends JpaRepository<Pitcher, Long> {
    Optional<Pitcher> findByPlayer(Player player);
}
