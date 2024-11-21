package com.example.kbolog.controller;

import com.example.kbolog.entity.Watching;
import com.example.kbolog.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@AllArgsConstructor
public class WatchingController {

    private final TestService testService;

    @GetMapping(value="/api/watching")
    public List<Watching> getStadiumList(){
        return testService.getWatchingList();
    }


}
