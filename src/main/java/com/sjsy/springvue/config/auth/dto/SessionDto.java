package com.sjsy.springvue.config.auth.dto;

import com.sjsy.springvue.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionDto implements Serializable {

    private String id;
    private String nickname;
    private String profileImg;

    public SessionDto(User user) {
        this.id = user.getId().toString();
        this.nickname = user.getNickname();
        this.profileImg = user.getProfileImg();
    }

}
