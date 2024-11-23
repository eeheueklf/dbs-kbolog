package com.example.kbolog.DTO;

public class WatchingRequest {

    private Long gameId;
    private String title;
    private String content;
    private String location;

    // 기본 생성자
    public WatchingRequest() {}

    // Getter and Setter
    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
