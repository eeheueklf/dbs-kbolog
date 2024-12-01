package com.example.kbolog.controller;

import com.example.kbolog.dto.PlayerDTO;
import com.example.kbolog.entity.*;
import com.example.kbolog.repository.*;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PlayerController {
    private final MemberRepository memberRepository;
    private final PlayerRepository playerRepository;
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
    public ResponseEntity<PlayerDTO> getPlayerDetails(@PathVariable Integer pId) {
        Object[] result = playerRepository.findPlayerDetailsById(pId);
        if (result == null || result.length == 0 || result[0] == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);}
        PlayerDTO playerDTO = new PlayerDTO();
        Object[] playerData = (Object[]) result[0];
        try {
            playerDTO.setPlayerName((String) playerData[0]);
            playerDTO.setPlayerNumber((Integer) playerData[1]);
            playerDTO.setPlayerPosition((Integer) playerData[2]);
            playerDTO.setTeamSponsor((String) playerData[3]);
            playerDTO.setBattingHand((String) playerData[4]);
            playerDTO.setHander((String) playerData[5]);

            String playerType = (String) playerData[6];
            playerDTO.setPlayerType(playerType);

            if ("Hitter".equals(playerType)) {
                playerDTO.setAvg(playerData[7] != null ? (Double) playerData[7] : null);
                playerDTO.setOps(playerData[8] != null ? (Double) playerData[8] : null);
                playerDTO.setWar(playerData[9] != null ? (Double) playerData[9] : null);
            } else if ("Pitcher".equals(playerType)) {
                playerDTO.setIp(playerData[10] != null ? (Double) playerData[10] : null);
                playerDTO.setEra(playerData[11] != null ? (Double) playerData[11] : null);
                playerDTO.setWhip(playerData[12] != null ? (Double) playerData[12] : null);
            }

        } catch (ArrayIndexOutOfBoundsException | ClassCastException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(playerDTO);
    }





    @GetMapping("/api/player")
    public ResponseEntity<List<Map<String, Object>>> getAllPlayers() {
        List<Object[]> results = playerRepository.findAllPlayers();

        List<Map<String, Object>> players = results.stream().map(row -> {
            Map<String, Object> player = new HashMap<>();
            player.put("playerId", row[0]);
            player.put("playerName", row[1]);
            player.put("playerNumber", row[2]);
            player.put("playerPosition", row[3]);
            player.put("playerType", row[4]);

            if ("Hitter".equals(row[4])) {
                player.put("avg", row[5]);
                player.put("ops", row[6]);
                player.put("war", row[7]);
            } else if ("Pitcher".equals(row[4])) {
                player.put("ip", row[8]);
                player.put("era", row[9]);
                player.put("whip", row[10]);
            }
            player.put("teamName", row[11]);
            player.put("sponsor", row[12]);

            return player;
        }).toList();

        return ResponseEntity.ok(players);
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/api/player/isFav/{pId}")
    public ResponseEntity<Boolean> isFavPlayer(@PathVariable Integer pId, HttpSession session) {
        String username = (String) session.getAttribute("username");
        logger.info("Received playerId: {}", pId);
        Player player = playerRepository.findByPlayerId(pId);
        Member member = memberRepository.findByUsername(username);

        Integer isFavorite = cheerPlayerRepository.existsByMemberAndPlayer(member.getId(), player.getPlayerId());

        return ResponseEntity.ok(isFavorite == 1);
    }


    @PostMapping("/api/player/select/{pId}")
    public void addFavorite(@PathVariable Integer pId, HttpSession session) {

        String username = (String) session.getAttribute("username");

        Member member = memberRepository.findByUsername(username);
        Player player = playerRepository.findByPlayerId(pId);

        if(member != null && player != null) {
            cheerPlayerRepository.addFav(player.getPlayerId(), member.getId());
        }
    }

    @DeleteMapping("/api/player/select/{pId}")
    public void removeFavorite(@PathVariable Integer pId, HttpSession session) {
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

    @GetMapping("/api/player/rank")
    public ResponseEntity<List<PlayerDTO>> getPlayerRank() {

        try {
            // Native Query 결과 가져오기
            List<Object[]> results = playerRepository.findPlayerRank();

            // 결과를 PlayerDTO로 변환
            List<PlayerDTO> playerRank = results.stream()
                    .map(row -> new PlayerDTO(
                            (Integer) row[0],
                            (Long) row[1],
                            (String) row[2],
                            (Integer) row[3],
                            (String) row[4],
                            (String) row[5]
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(playerRank);
        } catch (Exception e) {
            e.printStackTrace(); // 로그 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

