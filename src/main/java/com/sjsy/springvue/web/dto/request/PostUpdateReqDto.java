package com.sjsy.springvue.web.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdateReqDto {

    private String userId;
    private Long boardId;
    private String subject;
    private String content;

    @Builder
    public PostUpdateReqDto(String userId, Long boardId, String subject, String content) {
        this.userId = userId;
        this.boardId = boardId;
        this.subject = subject;
        this.content = content;
    }

}
