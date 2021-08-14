package com.sjsy.springvue.web.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDetailResDto {

    private Long id;                   //글번호
    private LocalDateTime createdDate; //쓴날짜
    private LocalDateTime modefiedDate;//수정한날짜(수정했을경우)
    private String subject;            //제목
    private String content;            //내용
    private int likecount;             //좋아요 수수
    private int readcount;             //조회수

    @Builder
    public PostDetailResDto(Long id, LocalDateTime createdDate, LocalDateTime modefiedDate, String subject, String content, int likecount, int readcount) {
        this.id = id;
        this.createdDate = createdDate;
        this.modefiedDate = modefiedDate;
        this.subject = subject;
        this.content = content;
        this.likecount = likecount;
        this.readcount = readcount;
    }
}
