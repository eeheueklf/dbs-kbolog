package com.example.kbolog.service;

import com.example.kbolog.dto.PlayerDTO;
import com.example.kbolog.entity.Player;
import com.example.kbolog.entity.Member;
import com.example.kbolog.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public List<PlayerDTO> getCheeredPlayersByMember(Member member) {
        return member.getCheerPlayers().stream()
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
    }
}

