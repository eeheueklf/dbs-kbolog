package com.example.kbolog.controller;

import com.example.kbolog.DTO.WatchingRequest;
import com.example.kbolog.entity.Game;
import com.example.kbolog.entity.Member;
import com.example.kbolog.entity.Watching;
import com.example.kbolog.repository.GameRepository;
import com.example.kbolog.repository.MemberRepository;
import com.example.kbolog.repository.WatchingRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.config.ResourceReaderRepositoryPopulatorBeanDefinitionParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@AllArgsConstructor
public class WatchingController {

    private final WatchingRepository watchingRepository;
    private final MemberRepository memberRepository;
    private final GameRepository gameRepository;

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

    @PostMapping("/api/log/write")
    public void logWrite(@RequestBody WatchingRequest request, HttpSession session) {
        String username = (String) session.getAttribute("username");

        Game game = gameRepository.findById(request.getGameId()).orElse(null);
        Member user = memberRepository.findByUsername(username);

        if (game != null && user != null) {
            // Watching 객체 생성 및 설정
            Watching watching = new Watching();
            watching.setGame(game);
            watching.setUser(user);
            watching.setTitle(request.getTitle());
            watching.setContent(request.getContent());
            watching.setLocation(request.getLocation());

            watchingRepository.save(watching);
        }
    }
    @PutMapping("/api/log/edit/{id}")
    public ResponseEntity<String> logEdit(
            @PathVariable Long id, // 수정할 로그의 ID
            @RequestBody WatchingRequest request, // 클라이언트로부터 받은 수정 데이터
            HttpSession session) {

        String username = (String) session.getAttribute("username");

        Game game = gameRepository.findById(request.getGameId()).orElse(null);
        Member user = memberRepository.findByUsername(username);

        // 로그가 존재하고, 게임과 사용자가 유효한 경우에만 처리
        if (game != null && user != null) {
            // 수정할 Watching 객체 찾아오기
            Watching existingWatching = watchingRepository.findByWatchingId(id)
                    .orElseThrow(() -> new RuntimeException("Log not found"));

            // 기존 데이터 수정
            existingWatching.setGame(game);
            existingWatching.setUser(user);
            existingWatching.setTitle(request.getTitle());
            existingWatching.setContent(request.getContent());
            existingWatching.setLocation(request.getLocation());

            // 수정된 데이터를 저장
            watchingRepository.save(existingWatching);

            return ResponseEntity.ok("Log updated successfully");
        } else {
            return ResponseEntity.status(400).body("Invalid game or user");
        }
    }


}
