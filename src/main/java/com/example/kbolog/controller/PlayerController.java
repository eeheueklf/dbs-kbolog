package com.example.kbolog.controller;

import com.example.kbolog.Request.WatchingRequest;
import com.example.kbolog.dto.PlayerDTO;
import com.example.kbolog.dto.WatchingDTO;
import com.example.kbolog.entity.*;
import com.example.kbolog.repository.*;
import com.example.kbolog.service.PlayerService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final MemberRepository memberRepository;
    private final PlayerRepository playerRepository;
    private final PitcherRepository pitcherRepository;
    private final HitterRepository hitterRepository;
    private final CheerPlayerRepository cheerPlayerRepository;

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

    @GetMapping(value = "/api/player/{pId}")
    public ResponseEntity<PlayerDTO> getPlayerDetails(@PathVariable Long pId) {
        Player player = playerRepository.findById(pId).orElse(null);

        if (player == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Pitcher pitcher = pitcherRepository.findByPlayer(player).orElse(null);
        Hitter hitter = hitterRepository.findByPlayer(player).orElse(null);

        PlayerDTO playerDTO = new PlayerDTO(player);
        if (pitcher != null) {
            playerDTO.setPitcherStats(pitcher.getIp(), pitcher.getEra(), pitcher.getWhip());
        }
        if (hitter != null) {
            playerDTO.setHitterStats(hitter.getAvg(), hitter.getOps(), hitter.getWar());
        }

        return ResponseEntity.ok(playerDTO);
    }

    @GetMapping("/api/player")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        List<PlayerDTO> playerDTOs = players.stream().map(player -> {
            PlayerDTO playerDTO = new PlayerDTO(player);

            // 투수 데이터 확인
            Pitcher pitcher = pitcherRepository.findByPlayer(player).orElse(null);
            if (pitcher != null) {
                playerDTO.setPitcherStats(pitcher.getIp(), pitcher.getEra(), pitcher.getWhip());
            }

            // 타자 데이터 확인
            Hitter hitter = hitterRepository.findByPlayer(player).orElse(null);
            if (hitter != null) {
                playerDTO.setHitterStats(hitter.getAvg(), hitter.getOps(), hitter.getWar());
            }

            return playerDTO;
        }).toList();

        return ResponseEntity.ok(playerDTOs);
    }

    @GetMapping("/api/player/isFav/{pId}")
    public ResponseEntity<Boolean> isFavPlayer(@PathVariable Long pId, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Player player = playerRepository.findById(pId).orElse(null);
        Member member = memberRepository.findByUsername(username);
        boolean isFavorite = cheerPlayerRepository.existsByMemberAndPlayer(member,player);
        return ResponseEntity.ok(isFavorite);
    }

    @PostMapping("/api/player/select/{pId}")
    public void addFavorite(@PathVariable Long pId, HttpSession session) {

        String username = (String) session.getAttribute("username");

        Member member = memberRepository.findByUsername(username);
        Player player = playerRepository.findById(pId).orElse(null);

        if(member != null && player != null) {
            CheerPlayer cheerPlayer = new CheerPlayer();
            cheerPlayer.setPlayer(player);
            cheerPlayer.setMember(member);
            cheerPlayer.setCheerDate(LocalDate.now());
            cheerPlayerRepository.save(cheerPlayer);
        }
    }

    @DeleteMapping("/api/player/select/{pId}")
    public void removeFavorite(@PathVariable Long pId, HttpSession session) {

        // 세션에서 사용자 정보 가져오기
        String username = (String) session.getAttribute("username");

        // 사용자가 존재하는지 확인
        Member member = memberRepository.findByUsername(username);
        Player player = playerRepository.findById(pId).orElse(null);

        if (member != null && player != null) {
            // CheerPlayer가 존재하는지 확인하여 삭제
            CheerPlayer cheerPlayer = cheerPlayerRepository.findByMemberAndPlayer(member, player);

            if (cheerPlayer != null) {
                cheerPlayerRepository.delete(cheerPlayer);  // 관심 선수 해제
            }
        }
    }


}

