package com.example.kbolog.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GameRequest {

    private Long teamId;
    private LocalDate start;
    private LocalDate end;

    public GameRequest(Long teamId, LocalDate start, LocalDate end) {
        this.teamId = teamId;
        this.start = start;
        this.end = end;
    }
}