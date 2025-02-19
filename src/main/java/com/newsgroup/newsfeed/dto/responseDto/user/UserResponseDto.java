package com.newsgroup.newsfeed.dto.responseDto.user;

import com.newsgroup.newsfeed.entity.Users;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String email;
    private String nickname;

    public UserResponseDto(Users user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}