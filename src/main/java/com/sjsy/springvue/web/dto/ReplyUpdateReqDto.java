package com.sjsy.springvue.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReplyUpdateReqDto {

    private String userId;
    private String content;

    @Builder
    public ReplyUpdateReqDto(String userId, String content) {
        this.userId = userId;
        this.content = content;
    }

}
