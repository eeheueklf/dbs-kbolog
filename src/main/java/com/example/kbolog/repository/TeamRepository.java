package com.example.kbolog.repository;

import com.example.kbolog.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

//    setting - 모든 팀 리스트
    @Query(value = "SELECT * FROM team", nativeQuery = true)
    List<Team> findAllTeams();

    @Query(value = "SELECT * FROM Team t WHERE t.sponsor = :sponsor", nativeQuery = true)
    Team findBySponsor(@Param("sponsor") String sponsor);
}
