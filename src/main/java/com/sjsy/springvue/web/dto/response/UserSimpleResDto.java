package com.sjsy.springvue.web.dto.response;

import com.sjsy.springvue.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
// 글내용, 덧글내용 등 유저의 심플정보 response Dto
public class UserSimpleResDto {

    private Long userId;
    private String nickname;
    private String profileImg;

    @Builder
    public UserSimpleResDto(User entity) {
        this.userId = entity.getId();
        this.nickname = entity.getNickname();
        this.profileImg = entity.getProfileImg();
    }

}
