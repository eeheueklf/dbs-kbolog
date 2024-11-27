package com.example.kbolog.repository;

import com.example.kbolog.entity.Game;
import com.example.kbolog.entity.Member;
import com.example.kbolog.entity.Watching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WatchingRepository extends JpaRepository<Watching, Long> {
    List<Watching> findByUser(Member user);
    Optional<Watching> findByWatchingId(Long id);
    boolean existsByGameAndUser(Game game, Member user);
}
