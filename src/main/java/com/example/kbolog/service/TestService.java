package com.example.kbolog.service;

import java.util.List;

import com.example.kbolog.entity.Stadium;
import com.example.kbolog.entity.Team;
import com.example.kbolog.entity.Watching;
import com.example.kbolog.repository.StadiumRepository;
import com.example.kbolog.repository.TeamRepository;
import com.example.kbolog.repository.WatchingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TestService {

    private StadiumRepository stadiumRepository;

    public List<Stadium> getStadiumList() {
        return stadiumRepository.findAll();
    }

    private WatchingRepository watchingRepository;

    public List<Watching> getWatchingList() { return watchingRepository.findAll(); }

    private TeamRepository teamRepository;

    public List<Team> getTeamList() { return teamRepository.findAll(); }

}
