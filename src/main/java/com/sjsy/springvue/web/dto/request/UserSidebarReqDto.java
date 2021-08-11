package com.sjsy.springvue.web.dto.request;

import com.sjsy.springvue.domain.board.Board;
import com.sjsy.springvue.domain.post.Post;
import com.sjsy.springvue.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserSidebarReqDto {

    private Long id;         //바꿀 유저의 id
    private String nickname; //바꿀 닉네임

    @Builder
    public UserSidebarReqDto(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

}

