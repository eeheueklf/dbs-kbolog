package com.example.kbolog.repository;

import com.example.kbolog.entity.CheerPlayer;
import com.example.kbolog.entity.Member;
import com.example.kbolog.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CheerPlayerRepository extends JpaRepository<CheerPlayer, Long> {

//    선수 멤버 관심 연결 존재 확인
    @Query(value = "SELECT EXISTS (SELECT 1 FROM cheer_player cp WHERE cp.member_id = :memberId AND cp.player_id = :playerId)", nativeQuery = true)
    Integer existsByMemberAndPlayer(@Param("memberId") Long memberId, @Param("playerId") Long playerId);

//    player - 관심 선수 리스트
    @Query(value = "SELECT * FROM cheer_player cp WHERE cp.member_id = :memberId AND cp.player_id = :playerId", nativeQuery = true)
    CheerPlayer findByMemberAndPlayer(@Param("memberId") Long memberId, @Param("playerId") Long playerId);

//    //    player - 관심 선수 등록
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO cheer_player (player_id, member_id, cheer_date) VALUES (:playerId, :memberId, CURRENT_DATE)", nativeQuery = true)
    void addFav(@Param("playerId") Long playerId,
            @Param("memberId") Long memberId
            );

    // player - 관심 선수 해제
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cheer_player WHERE id = :id", nativeQuery = true)
    void delFav(@Param("id") Long id);

}
