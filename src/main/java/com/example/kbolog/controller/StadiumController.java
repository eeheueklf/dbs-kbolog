package com.example.kbolog.controller;

import com.example.kbolog.entity.Stadium;
import com.example.kbolog.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@AllArgsConstructor
public class StadiumController {

    private final TestService testService;

    @GetMapping(value="/api/stadium")
    public List<Stadium> getStadiumList(){
        return testService.getStadiumList();
    }

}
