package com.example.kbolog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WatchingRequest {

    // Getter and Setter
    private Long gameId;
    private String title;
    private String content;
    private String location;

    // 기본 생성자
    public WatchingRequest() {}

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
