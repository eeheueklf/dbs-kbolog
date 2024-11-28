package com.example.kbolog.repository;

import com.example.kbolog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

//    username으로 member찾기
    @Query(value = "SELECT * FROM member u WHERE u.username = :username", nativeQuery = true)
    Member findByUsername(@Param("username") String username);

}
