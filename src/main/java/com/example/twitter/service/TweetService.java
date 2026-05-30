package com.example.twitter.service;

import com.example.twitter.dao.CreateTweetDao;
import com.example.twitter.dao.TweetResponse;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.mapper.TweetMapper;
import com.example.twitter.repository.TweetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.twitter.repository.AuthRepository;

import java.time.LocalDateTime;

@Service
public class TweetService {
    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    private final AuthRepository userRepository;

    public TweetService(TweetRepository tweetRepository,TweetMapper tweetMapper, AuthRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.tweetMapper = tweetMapper;
        this.userRepository = userRepository;
    };

    public TweetResponse createTweet(CreateTweetDao createTweetDao, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Tweet tweet = tweetMapper.toEntity(createTweetDao);
        tweet.setUser(user);
        tweet.setCreatedAt(LocalDateTime.now());

        Tweet savedTweet = tweetRepository.save(tweet);
        return tweetMapper.toResponse(savedTweet);
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
