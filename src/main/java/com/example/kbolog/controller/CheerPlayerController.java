package com.example.kbolog.controller;


import com.example.kbolog.dto.CheerPlayerDTO;
import com.example.kbolog.entity.CheerPlayer;
import com.example.kbolog.entity.Member;
import com.example.kbolog.entity.Player;
import com.example.kbolog.repository.CheerPlayerRepository;
import com.example.kbolog.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@AllArgsConstructor
public class CheerPlayerController {

    private final MemberRepository memberRepository;
    private final CheerPlayerRepository cheerPlayerRepository;

    @GetMapping("/api/player/cheer")
    public ResponseEntity<List<CheerPlayerDTO>> getCheerPlayer(HttpSession session) {
        String username = (String) session.getAttribute("username");
        Member member = memberRepository.findByUsername(username);
        if(member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<CheerPlayer> cheerPlayer = cheerPlayerRepository.findByMember(member);

        if(cheerPlayer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<CheerPlayerDTO> response  = cheerPlayer.stream()
                .map(CheerPlayerDTO::new)
                .toList();

        return ResponseEntity.ok(response);
    }
}
