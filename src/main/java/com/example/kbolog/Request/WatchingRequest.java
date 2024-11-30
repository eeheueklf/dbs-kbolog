package com.example.kbolog.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WatchingRequest {

    private Integer gameId;
    private String title;
    private String content;
    private String location;

    public WatchingRequest(Integer gameId, String title, String content, String location) {
        this.gameId = gameId;
        this.title = title;
        this.content = content;
        this.location = location;
    }
}
