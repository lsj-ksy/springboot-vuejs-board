package com.sjsy.springvue.web.dto.response;

import com.sjsy.springvue.domain.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;

//게시글 Dto
@Getter
public class PostsListResDto {

    private Long id;    //post id
    private String nickname; //닉네임
    private String subject;  //제목
    private LocalDateTime createdDate;  //만든날짜
    private int readCount;   //조회수
    private int likeCount;   //좋아요 수


    public PostsListResDto(Post entity) {
        this.id = entity.getId();
        this.nickname = entity.getUser().getNickname();
        this.subject = entity.getSubject();
        this.createdDate = entity.getCreatedDate();
        this.readCount = entity.getReadcount();
        this.likeCount = entity.getLikecount();
    }

}
