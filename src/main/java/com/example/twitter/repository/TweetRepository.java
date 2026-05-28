package com.example.twitter.repository;

import com.example.twitter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    public Page<Tweet> findAllByUserId(Long userId, Pageable pageable);
}
