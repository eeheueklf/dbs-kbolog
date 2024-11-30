package com.example.kbolog.repository;

import com.example.kbolog.entity.Watching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface WatchingRepository extends JpaRepository<Watching, Integer> {

// dashboard - 사용자 관람 기록(로그) ✅
    @Query(value = "SELECT * FROM watching WHERE user_id = :userId", nativeQuery = true)
    List<Watching> findByUser(@Param("userId") Integer userId);

// log - 중복 로그 있나 확인 ✅
    @Query(value = "SELECT EXISTS (SELECT 1 FROM watching WHERE game_id = :gameId AND user_id = :userId)",
            nativeQuery = true)
    Integer existsByGameAndUser(@Param("gameId") Integer gameId, @Param("userId") Integer userId);



    @Query(value = "SELECT * FROM watching WHERE watching_id = :id", nativeQuery = true)
    Optional<Watching> findByWatchingId(@Param("id") Integer id);

//    log - 로그 작성 ✅
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO watching (user_id, game_id, title, content, location) VALUES (:userId, :gameId, :title, :content, :location)", nativeQuery = true)
    void write(@Param("userId") Integer userId,
              @Param("gameId") Integer gameId,
              @Param("title") String title,
              @Param("content") String content,
              @Param("location") String location
                );

// log - 로그 수정 ✅
    @Transactional
    @Modifying
    @Query(value = "UPDATE watching SET game_id = :gameId, title = :title, content = :content, location = :location WHERE watching_id = :id", nativeQuery = true)
    void edit(@Param("id") Integer id,
              @Param("gameId") Integer gameId,
              @Param("title") String title,
              @Param("content") String content,
              @Param("location") String location
    );

// log - 로그 삭제 ✅
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM watching WHERE watching_id = :id", nativeQuery = true)
    void delete(@Param("id") Integer id);
}
