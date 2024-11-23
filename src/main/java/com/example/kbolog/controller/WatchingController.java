package com.example.kbolog.controller;

import com.example.kbolog.entity.Member;
import com.example.kbolog.entity.Watching;
import com.example.kbolog.repository.MemberRepository;
import com.example.kbolog.repository.WatchingRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.config.ResourceReaderRepositoryPopulatorBeanDefinitionParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@AllArgsConstructor
public class WatchingController {

    private final WatchingRepository watchingRepository;
    private final MemberRepository memberRepository;

    @GetMapping(value="/api/watching")
    public ResponseEntity<List<Watching>> getWatchingList(HttpSession session) {
        String username = (String) session.getAttribute("username");
        System.out.println("Session username: " + username);  // 콘솔에 출력
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Member member = memberRepository.findByUsername(username);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 사용자 없으면 404 반환
        }

        // Member 객체를 기준으로 Watching 리스트 조회
        List<Watching> records = watchingRepository.findByUser(member);

        if (records == null || records.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(records);
    }

    @GetMapping(value="/api/log/{id}")
    public ResponseEntity<Watching> getWatchingContent(@PathVariable Long id){
        Watching watching = watchingRepository.findById(id).orElse(null);

        if(watching == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(watching);
    }


}
