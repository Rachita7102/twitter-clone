package com.example.twitter.service;

import com.example.twitter.dao.TweetResponse;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.mapper.TweetMapper;
import com.example.twitter.repository.TweetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TweetService {
    private TweetRepository tweetRepository;
    private TweetMapper tweetMapper;

    public TweetService(TweetRepository tweetRepository,TweetMapper tweetMapper) {
        this.tweetRepository = tweetRepository;
        this.tweetMapper = tweetMapper;
    };

    public TweetResponse createTweet(Tweet tweet) {
        tweetRepository.save(tweet);
        return tweetMapper.toResponse(tweet);
    }

    public String deleteTweet(Long id, String username) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tweet not found with id: " + id));

        if (!tweet.getUser().getUsername().equals(username)) {
            return "You are not authorized to delete this tweet.";
        }

        tweetRepository.delete(tweet);
        return "Tweet with id " + id + " deleted successfully.";
    }

    public Page<TweetResponse> getTweetsByUser(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"createdAt"));
        return tweetRepository.findAllByUserId(userId, pageable)
                .map(tweetMapper::toResponse);
    }
}
