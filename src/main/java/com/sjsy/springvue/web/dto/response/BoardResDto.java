package com.sjsy.springvue.web.dto.response;

import com.sjsy.springvue.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

//게시글 Dto
@Getter
public class BoardResDto {

    private String categoryName;
    private String boardName;
    private List<PostsListResDto> postList;

    @Builder
    public BoardResDto(String categoryName, String boardName, List<PostsListResDto> postList ) {
        this.categoryName = categoryName;
        this.boardName = boardName;
        this.postList = postList;
    }

}
