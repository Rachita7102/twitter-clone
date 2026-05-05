package com.example.twitter.mapper;

import com.example.twitter.entity.User;
import com.example.twitter.dao.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
}
