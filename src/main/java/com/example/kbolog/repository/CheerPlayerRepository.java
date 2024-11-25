package com.example.kbolog.repository;

import com.example.kbolog.entity.CheerPlayer;
import com.example.kbolog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheerPlayerRepository extends JpaRepository<CheerPlayer, Long> {
    List<CheerPlayer> findByMember(Member member);
}
