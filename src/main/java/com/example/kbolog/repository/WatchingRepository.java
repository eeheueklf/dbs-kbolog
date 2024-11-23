package com.example.kbolog.repository;

import com.example.kbolog.entity.Member;
import com.example.kbolog.entity.Watching;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchingRepository extends JpaRepository<Watching, Long> {
    List<Watching> findByUser(Member user);
}
