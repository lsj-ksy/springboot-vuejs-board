package com.sjsy.springvue.web.dto.response;

import com.sjsy.springvue.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostDetailResDto {

    private Long postId;    //글의 고유값
    private Long boardId;   //게시판 id
    private Long categoryId;//카테고리 id
    private String boardName;//게시판이름
    private String categoryName;//카테고리이름
    private UserSimpleResDto user;//유저정보
    private String subject; //제목
    private String content; //글내용
    private LocalDateTime modifiedDate; //최종수정일
    private List<Long> postFileList = new ArrayList<>(); //파일리스트

    @Builder
    public PostDetailResDto(Post entity) {
        this.postId = entity.getId();
        this.boardId = entity.getBoard().getId();
        this.categoryId = entity.getBoard().getCategory().getId();
        this.categoryName = entity.getBoard().getCategory().getCategoryName();
        this.boardName = entity.getBoard().getBoardName();
        this.user = new UserSimpleResDto(entity.getUser());
        this.subject = entity.getSubject();
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedDate();

        entity.getPostFileList().forEach(postFile -> this.postFileList.add(postFile.getId()));

    }

}
