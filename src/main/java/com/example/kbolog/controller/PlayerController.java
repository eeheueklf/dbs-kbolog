package com.example.kbolog.controller;

import com.example.kbolog.dto.PlayerDTO;
import com.example.kbolog.entity.*;
import com.example.kbolog.repository.*;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PlayerController {
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

        List<PlayerDTO> playerDTOs = member.getCheerPlayers().stream()
                .map(cheerPlayer -> {
                    Player player = cheerPlayer.getPlayer();
                    return new PlayerDTO(
                            player.getPlayerId(),
                            player.getPlayerName(),
                            player.getPlayerNumber(),
                            player.getPlayerPosition(),
                            player.getTeam().getTeamName(),
                            player.getTeam().getSponsor(),
                            cheerPlayer.getCheerDate()
                    );
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(playerDTOs);
    }


    @GetMapping(value = "/api/player/{pId}")
    public ResponseEntity<PlayerDTO> getPlayerDetails(@PathVariable Long pId) {
        Player player = playerRepository.findByPlayerId(pId);

        if (player == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Pitcher pitcher = pitcherRepository.findByPlayer(player.getPlayerId()).orElse(null);
        Hitter hitter = hitterRepository.findByPlayer(player.getPlayerId()).orElse(null);

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
        List<Player> players = playerRepository.findAllPlayers();
        List<PlayerDTO> playerDTOs = players.stream().map(player -> {
            PlayerDTO playerDTO = new PlayerDTO(player);

            // 투수 데이터 확인
            Pitcher pitcher = pitcherRepository.findByPlayer(player.getPlayerId()).orElse(null);
            if (pitcher != null) {
                playerDTO.setPitcherStats(pitcher.getIp(), pitcher.getEra(), pitcher.getWhip());
            }

            // 타자 데이터 확인
            Hitter hitter = hitterRepository.findByPlayer(player.getPlayerId()).orElse(null);
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

        Player player = playerRepository.findByPlayerId(pId);
        Member member = memberRepository.findByUsername(username);

        Integer isFavorite = cheerPlayerRepository.existsByMemberAndPlayer(member.getId(), player.getPlayerId());

        return ResponseEntity.ok(isFavorite == 1);
    }


    @PostMapping("/api/player/select/{pId}")
    public void addFavorite(@PathVariable Long pId, HttpSession session) {

        String username = (String) session.getAttribute("username");

        Member member = memberRepository.findByUsername(username);
        Player player = playerRepository.findByPlayerId(pId);

        if(member != null && player != null) {
            cheerPlayerRepository.addFav(player.getPlayerId(), member.getId());
        }
    }

    @DeleteMapping("/api/player/select/{pId}")
    public void removeFavorite(@PathVariable Long pId, HttpSession session) {
        String username = (String) session.getAttribute("username");

        Member member = memberRepository.findByUsername(username);
        Player player = playerRepository.findByPlayerId(pId);

        if (member != null && player != null) {
            // CheerPlayer 존재 여부 확인 후 삭제
            CheerPlayer cheerPlayer = cheerPlayerRepository.findByMemberAndPlayer(member.getId(), player.getPlayerId());

            if (cheerPlayer != null) {
                cheerPlayerRepository.delFav(cheerPlayer.getId());
            }
        }
    }


}

