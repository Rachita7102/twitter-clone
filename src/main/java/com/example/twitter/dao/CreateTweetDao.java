package com.example.twitter.dao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTweetDao {

    @NotBlank(message = "Content cannot be blank")
    @Size(max = 50, message = "Content cannot exceed 50 characters")
    private String content;
}
