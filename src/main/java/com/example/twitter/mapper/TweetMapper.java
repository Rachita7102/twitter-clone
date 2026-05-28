package com.example.twitter.mapper;

import com.example.twitter.dao.TweetResponse;
import com.example.twitter.entity.Tweet;
import org.mapstruct.Mapper;

@Mapper
public interface TweetMapper {
    TweetResponse toResponse(Tweet tweet);
}
