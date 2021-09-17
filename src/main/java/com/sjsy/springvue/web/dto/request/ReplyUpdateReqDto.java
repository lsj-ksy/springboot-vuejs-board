package com.sjsy.springvue.web.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReplyUpdateReqDto {

    private String userId;
    private String content;

    @Builder
    public ReplyUpdateReqDto(String userId, String content) {
        this.userId = userId;
        this.content = content;
    }

}
