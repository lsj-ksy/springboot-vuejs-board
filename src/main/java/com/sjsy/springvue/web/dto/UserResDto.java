package com.sjsy.springvue.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

//사이드바 나의활동 api data
@Getter
public class UserResDto {

    private Long id; // user_id
    private String profileImg; //프로필사진
    private String nickname; //닉네임
    private LocalDateTime createdDate; //가입일
    private String socialType; //가입유형

    private int postCount; //내가 쓴 글 갯수
    private int replyCount; //내가 쓴 댓글 갯수
    private int scrapCount; // 내가 스크랩한 글 갯수

    //UserResDto Api Data Builder
    @Builder
    public UserResDto(Long id, String profileImg, String nickname, LocalDateTime createdDate, String socialType, int postCount, int replyCount, int scrapCount) {
        this.id = id;
        this.profileImg = profileImg;
        this.nickname = nickname;
        this.createdDate = createdDate;
        this.socialType = socialType;
        this.postCount = postCount;
        this.replyCount = replyCount;
        this.scrapCount = scrapCount;
    }


}
