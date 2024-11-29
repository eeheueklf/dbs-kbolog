package com.example.kbolog.repository;

import com.example.kbolog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

//    username으로 member찾기
    @Query(value = "SELECT * FROM member u WHERE u.username = :username", nativeQuery = true)
    Member findByUsername(@Param("username") String username);

//    중복 username 체크
    @Query(value = "SELECT EXISTS (SELECT 1 FROM member WHERE username = :username)",
            nativeQuery = true)
    Integer existsByUsername(@Param("username") String username);


    // 회원가입
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO member (username, password) VALUES (:username, :password)", nativeQuery = true)
    void signup(@Param("username") String username,
               @Param("password") String password);

    // 회원정보수정 - 팀 설정
    @Transactional
    @Modifying
    @Query(value = "UPDATE member SET team_id = :teamId, rootdate = CURRENT_DATE WHERE id = :id", nativeQuery = true)
    void edit(@Param("id") Long id,
              @Param("teamId") Long teamId
    );

    // 탈퇴
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM member WHERE id = :id", nativeQuery = true)
    void resign(@Param("id") Long id);

}
