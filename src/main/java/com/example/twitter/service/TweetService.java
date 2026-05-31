package com.example.twitter.service;

import com.example.twitter.dao.CreateTweetDao;
import com.example.twitter.dao.TweetResponse;
import com.example.twitter.entity.Tweet;
import com.example.twitter.entity.User;
import com.example.twitter.exception.TweetNotFoundException;
import com.example.twitter.exception.UnauthorizedTweetAccessException;
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

    public void deleteTweet(Long id, String username) {

        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() ->
                        new TweetNotFoundException("Tweet not found with id: " + id));

        if (!tweet.getUser().getUsername().equals(username)) {
            throw new UnauthorizedTweetAccessException("You are not authorized to delete this tweet");
        }

        tweetRepository.delete(tweet);
    }

    public Page<TweetResponse> getTweetsByUser(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC,"createdAt"));
        return tweetRepository.findAllByUserId(userId, pageable)
                .map(tweetMapper::toResponse);
    }

    public TweetResponse updateTweet(Long id, String username, CreateTweetDao updatedTweet) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() ->
                        new TweetNotFoundException("Tweet not found with id: " + id));

        if (!tweet.getUser().getUsername().equals(username)) {
            throw new UnauthorizedTweetAccessException("You are not authorized to update this tweet");
        }

         tweet.setContent(updatedTweet.getContent());
         tweet.setUpdatedAt(LocalDateTime.now());

        tweetRepository.save(tweet);
        return tweetMapper.toResponse(tweet);
    }
}
