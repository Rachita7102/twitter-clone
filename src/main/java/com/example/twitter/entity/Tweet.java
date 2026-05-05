package com.example.twitter.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id") // foreign key
    private User user;
}