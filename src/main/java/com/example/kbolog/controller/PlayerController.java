package com.example.kbolog.controller;

import com.example.kbolog.dto.PlayerDTO;
import com.example.kbolog.dto.WatchingDTO;
import com.example.kbolog.entity.Member;
import com.example.kbolog.entity.Player;
import com.example.kbolog.entity.Watching;
import com.example.kbolog.repository.MemberRepository;
import com.example.kbolog.repository.PlayerRepository;
import com.example.kbolog.service.PlayerService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final MemberRepository memberRepository;
    private final PlayerRepository playerRepository;

    @GetMapping("/api/player/cheer")
    public ResponseEntity<List<PlayerDTO>> getCheeredPlayers(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Member member = memberRepository.findByUsername(username);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<PlayerDTO> playerDTOs = playerService.getCheeredPlayersByMember(member);
        return ResponseEntity.ok(playerDTOs);
    }

    @GetMapping(value="/api/player/{pId}")
    public ResponseEntity<PlayerDTO> getWatchingContent(@PathVariable Long pId){
        Player player = playerRepository.findById(pId).orElse(null);

        if(player == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(new PlayerDTO(player));
    }
}

