package com.example.kbolog.controller;

import com.example.kbolog.entity.Member;
import com.example.kbolog.entity.Team;
import com.example.kbolog.repository.MemberRepository;
import com.example.kbolog.repository.TeamRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @PostMapping("/api/signup")
    public void signup(@RequestBody Member member) {
        memberRepository.save(member);
    }

    @PostMapping("/api/signin")
    public ResponseEntity<Map<String, String>> signin(@RequestBody Member loginRequest, HttpSession session) {
        // 1. 데이터베이스에서 사용자 조회
        Member member = memberRepository.findByUsername(loginRequest.getUsername());
        if (member == null) {
            // 2. 사용자가 없으면 404 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found"));
        }

        // 3. 비밀번호 확인
        if (!member.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid password"));
        }

        session.setAttribute("username", member.getUsername());

        // 로그인 성공 시 200 반환, 응답에 loginUser 포함
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("username", member.getUsername());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/my")
    public ResponseEntity<Member> getMyMember(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        Member member = memberRepository.findByUsername(username);
        return ResponseEntity.ok(member);
    }
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);


    // 응원 팀 선택/수정
    @PostMapping("/api/my/selectTeam")
    public ResponseEntity<?> selectTeam(@RequestBody String sponsor, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        Member member = memberRepository.findByUsername(username);
        if (member == null) {
            return ResponseEntity.status(404).body("User not found");
        }
        Team team = teamRepository.findBySponsor(sponsor);
        if (team == null) {
            return ResponseEntity.status(404).body("Team not found");
        }
        member.setRootTeam(team);
        member.setRootdate(LocalDate.now());
        memberRepository.save(member);
        return ResponseEntity.ok("Good");  // 정상적으로 OK 응답
    }




}