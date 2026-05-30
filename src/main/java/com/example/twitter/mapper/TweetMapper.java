package com.example.twitter.mapper;

import com.example.twitter.dao.CreateTweetDao;
import com.example.twitter.dao.TweetResponse;
import com.example.twitter.entity.Tweet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TweetMapper {

    @Mapping(source = "user.username", target = "username")
    TweetResponse toResponse(Tweet tweet);
    Tweet toEntity(CreateTweetDao tweet);
}
