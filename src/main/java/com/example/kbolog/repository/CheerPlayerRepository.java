package com.example.kbolog.repository;

import com.example.kbolog.entity.CheerPlayer;
import com.example.kbolog.entity.Member;
import com.example.kbolog.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CheerPlayerRepository extends JpaRepository<CheerPlayer, Long> {

//    선수 멤버 관심 연결 존재 확인
    @Query(value = "SELECT EXISTS (SELECT 1 FROM cheer_player cp WHERE cp.member_id = :memberId AND cp.player_id = :playerId)", nativeQuery = true)
    Integer existsByMemberAndPlayer(@Param("memberId") Long memberId, @Param("playerId") Long playerId);

//    player - 관심 선수 리스트
    @Query(value = "SELECT * FROM cheer_player cp WHERE cp.member_id = :memberId AND cp.player_id = :playerId", nativeQuery = true)
    CheerPlayer findByMemberAndPlayer(@Param("memberId") Long memberId, @Param("playerId") Long playerId);


}
