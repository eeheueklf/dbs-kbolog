package com.example.kbolog.controller;

import com.example.kbolog.entity.Member;
import com.example.kbolog.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @PostMapping("/api/signup")
    public void signup(@RequestBody Member member) {
        memberRepository.save(member);
    }

    @PostMapping("/api/signin")
    public ResponseEntity<String> signin(@RequestBody Member loginRequest) {
        // 1. 데이터베이스에서 사용자 조회
        Member member = memberRepository.findByUsername(loginRequest.getUsername());
        if (member == null) {
            // 2. 사용자가 없으면 404 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // 3. 비밀번호 확인
        if (!member.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        // 4. 로그인 성공 시 200 반환
        return ResponseEntity.ok("Login successful");
    }
}
