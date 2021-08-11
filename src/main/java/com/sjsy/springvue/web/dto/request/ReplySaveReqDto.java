package com.sjsy.springvue.web.dto.request;

import com.sjsy.springvue.domain.post.Post;
import com.sjsy.springvue.domain.board.Reply;
import com.sjsy.springvue.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReplySaveReqDto {

    private String userId;
    private String postId;
    private String content;

    @Builder
    public ReplySaveReqDto(String userId, String postId, String content) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }

    public Reply toEntity(User user, Post post) {
        return Reply.builder()
                .user(user)
                .post(post)
                .content(content)
                .build();
    }
}
