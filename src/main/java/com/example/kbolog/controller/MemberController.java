package com.example.kbolog.controller;

import com.example.kbolog.dto.MemberDTO;
import com.example.kbolog.entity.Member;
import com.example.kbolog.entity.Team;
import com.example.kbolog.repository.MemberRepository;
import com.example.kbolog.repository.TeamRepository;
import com.example.kbolog.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
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
    private final UserService userService;

    @PostMapping("/api/signup")
    public ResponseEntity<?> signup(@RequestBody Member member) {
        try{
            userService.validateUsername(member.getUsername());
            userService.validatePassword(member.getPassword());
            memberRepository.signup(member.getUsername(), member.getPassword());
            return ResponseEntity.ok("회원가입 성공");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/api/checkDupUser")
    public ResponseEntity<Map<String, Boolean>> checkDupUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");

        if (username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("isAvailable", false));
        }
        Integer exists = memberRepository.existsByUsername(username);
        return ResponseEntity.ok(Map.of("isAvailable", exists!=1));
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

    @PostMapping("/api/signout")
    public ResponseEntity<Map<String, String>> signout(HttpSession session) {
        session.invalidate();;
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/resign")
    public ResponseEntity<Map<String, String>> deleteAccount(HttpSession session) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "로그인 상태가 아닙니다."));
        }

        Member member = memberRepository.findByUsername(username);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "사용자를 찾을 수 없습니다."));
        }

        memberRepository.resign(member.getId());

        session.invalidate();

        return ResponseEntity.ok(Map.of("message", "회원 탈퇴가 완료되었습니다."));
    }

    @GetMapping("/api/my")
    public ResponseEntity<MemberDTO> getMyMember(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        Member member = memberRepository.findByUsername(username);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        // Convert Member to MemberDTO
        MemberDTO memberDTO = new MemberDTO(member);
        return ResponseEntity.ok(memberDTO);
    }


    // 응원 팀 선택/수정
    @PostMapping("/api/my/selectTeam")
    public ResponseEntity<String> selectTeam(@RequestBody String sponsor, HttpSession session) {
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
        memberRepository.edit(member.getId(),team.getTeamId());
        return ResponseEntity.ok("Good");  // 정상적으로 OK 응답
    }




}