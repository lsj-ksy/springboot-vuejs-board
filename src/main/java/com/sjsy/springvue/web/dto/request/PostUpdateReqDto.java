package com.sjsy.springvue.web.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdateReqDto {

    private String userId;
    private String boardId;
    private String subject;
    private String content;

    @Builder
    public PostUpdateReqDto(String userId, String boardId, String subject, String content) {
        this.userId = userId;
        this.boardId = boardId;
        this.subject = subject;
        this.content = content;
    }

}
