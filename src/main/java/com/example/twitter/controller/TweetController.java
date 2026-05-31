package com.example.twitter.controller;

import com.example.twitter.dao.CreateTweetDao;
import com.example.twitter.dao.TweetResponse;
import com.example.twitter.service.TweetService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/tweet")
public class TweetController {

    private TweetService tweetService;

    public TweetController(TweetService tweetService) {
            this.tweetService = tweetService;
        }

    @PostMapping
    public ResponseEntity<TweetResponse> createTweet(@Valid @RequestBody CreateTweetDao tweet) {
        System.out.println("Received tweet content: " + tweet.getContent());

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(tweetService.createTweet(tweet,username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTweet(@PathVariable Long id) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        tweetService.deleteTweet(id, username);

        return ResponseEntity.ok("Tweet deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<TweetResponse> updateTweet(@PathVariable Long id, @Valid @RequestBody CreateTweetDao updatedTweet) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return ResponseEntity.ok(tweetService.updateTweet(id,username,updatedTweet));
    };

    @GetMapping("/{userId}")
    public ResponseEntity<Page<TweetResponse>> getTweetsByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(
                tweetService.getTweetsByUser(userId, page, size)
        );
    }
}
