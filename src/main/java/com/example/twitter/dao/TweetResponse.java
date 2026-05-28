package com.example.twitter.dao;

import com.example.twitter.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetResponse {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private String username;

}
