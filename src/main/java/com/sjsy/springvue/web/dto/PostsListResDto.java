package com.sjsy.springvue.web.dto;

import com.sjsy.springvue.domain.board.Post;
import lombok.Getter;

@Getter
public class PostsListResDto {

    private Long id;
    private String subject;

    public PostsListResDto(Post entity) {
        this.id = entity.getId();
        this.subject = entity.getSubject();
    }

}
