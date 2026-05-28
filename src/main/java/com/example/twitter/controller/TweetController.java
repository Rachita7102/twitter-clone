package com.example.twitter.controller;

import com.example.twitter.dao.TweetResponse;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.service.TweetService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/users/tweet")
public class TweetController {

    private TweetService tweetService;

    public TweetController(TweetService tweetService) {
            this.tweetService = tweetService;
        }

    @PostMapping
    public ResponseEntity<TweetResponse> createTweet(@RequestBody Tweet tweet) {
        return ResponseEntity.ok(tweetService.createTweet(tweet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTweet(@PathVariable Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(tweetService.deleteTweet(id, username));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<TweetResponse>> getTweetsByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                tweetService.getTweetsByUser(userId, page, size)
        );
    }
}
