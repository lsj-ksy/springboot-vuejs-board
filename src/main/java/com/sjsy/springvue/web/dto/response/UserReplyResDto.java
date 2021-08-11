package com.sjsy.springvue.web.dto.response;

import com.sjsy.springvue.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserReplyResDto {

    private Long userId;
    private String nickname;
    private String profileImg;

    @Builder
    public UserReplyResDto(User entity) {
        this.userId = entity.getId();
        this.nickname = entity.getNickname();
        this.profileImg = entity.getProfileImg();
    }

}
