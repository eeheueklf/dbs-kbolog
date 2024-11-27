package com.example.kbolog.controller;

import com.example.kbolog.Request.WatchingRequest;
import com.example.kbolog.dto.WatchingDTO;
import com.example.kbolog.entity.Game;
import com.example.kbolog.entity.Member;
import com.example.kbolog.entity.Watching;
import com.example.kbolog.repository.GameRepository;
import com.example.kbolog.repository.MemberRepository;
import com.example.kbolog.repository.WatchingRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@AllArgsConstructor
public class WatchingController {

    private final WatchingRepository watchingRepository;
    private final MemberRepository memberRepository;
    private final GameRepository gameRepository;

    @GetMapping(value="/api/watching")
    public ResponseEntity<List<WatchingDTO>> getWatchingList(HttpSession session) {
        String username = (String) session.getAttribute("username");
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

        // Watching 리스트를 WatchingDTO 리스트로 변환하여 반환
        List<WatchingDTO> watchingDTOs = records.stream()
                .map(WatchingDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(watchingDTOs);
    }

    @GetMapping(value="/api/log/{id}")
    public ResponseEntity<WatchingDTO> getWatchingContent(@PathVariable Long id){
        Watching watching = watchingRepository.findById(id).orElse(null);

        if(watching == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(new WatchingDTO(watching));
    }

    @PostMapping("/api/log/write")
    public ResponseEntity<String> logWrite(@RequestBody WatchingRequest request, HttpSession session) {
        String username = (String) session.getAttribute("username");

        Game game = gameRepository.findById(request.getGameId()).orElse(null);
        Member user = memberRepository.findByUsername(username);

        if (game != null && user != null) {

            if(watchingRepository.existsByGameAndUser(game, user)){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Log already exists for this game and user.");
            }
            Watching watching = new Watching();
            watching.setGame(game);
            watching.setUser(user);
            watching.setTitle(request.getTitle());
            watching.setContent(request.getContent());
            watching.setLocation(request.getLocation());

            watchingRepository.save(watching);
            return ResponseEntity.ok("Log created successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid game or user");
    }

    @PutMapping("/api/log/edit/{id}")
    public ResponseEntity<String> logEdit(@PathVariable Long id, @RequestBody WatchingRequest request, HttpSession session) {
        String username = (String) session.getAttribute("username");

        Game game = gameRepository.findById(request.getGameId()).orElse(null);
        Member user = memberRepository.findByUsername(username);

        if (game != null && user != null) {
            // 수정할 Watching 객체 찾아오기
            Watching existingWatching = watchingRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Log not found"));

            boolean existsDuplicateLog = watchingRepository.existsByGameAndUser(game, user);
            if (existsDuplicateLog && !existingWatching.getGame().equals(game) && !existingWatching.getUser().equals(user)) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("A log with the same game and user already exists.");
            }

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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid game or user");
        }
    }

    @DeleteMapping("/api/log/delete/{id}")
    public ResponseEntity<String> logDelete(@PathVariable Long id, HttpSession session) {
        String username = (String) session.getAttribute("username");

        Watching existingWatching = watchingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log not found"));

        if (existingWatching.getUser().getUsername().equals(username)) {
            watchingRepository.delete(existingWatching);
            return ResponseEntity.ok("Log deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to delete this log");
        }
    }

}