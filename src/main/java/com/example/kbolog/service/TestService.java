package com.example.kbolog.service;

import java.util.List;

import com.example.kbolog.entity.Stadium;
import com.example.kbolog.entity.Watching;
import com.example.kbolog.repository.StadiumRepository;
import com.example.kbolog.repository.WatchingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TestService {

    private StadiumRepository stadiumRepository;
    private WatchingRepository watchingRepository;

    public List<Stadium> getStadiumList() {
        return stadiumRepository.findAll();
    }

    public List<Watching> getWatchingList() { return watchingRepository.findAll(); }
}
