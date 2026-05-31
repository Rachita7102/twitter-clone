package com.example.twitter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tweets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime createdAt;

    private  LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id") // foreign key
    private User user;
}