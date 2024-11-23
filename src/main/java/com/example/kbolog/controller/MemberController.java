package com.example.kbolog.controller;

import com.example.kbolog.entity.Member;
import com.example.kbolog.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

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

        // 세션에 사용자 정보 저장
        session.setAttribute("username", member.getUsername());

        // 로그인 성공 시 200 반환, 응답에 loginUser 포함
        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("username", member.getUsername());

        return ResponseEntity.ok(response);
    }




}