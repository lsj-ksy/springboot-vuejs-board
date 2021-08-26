package com.sjsy.springvue.web.dto.response;

import com.sjsy.springvue.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostDetailResDto {

    private Long id;
    private UserSimpleResDto user;
    private String subject;
    private String content;
    private LocalDateTime modifiedDate;
    private List<Long> postFileList = new ArrayList<>();

    @Builder
    public PostDetailResDto(Post entity) {
        this.id = entity.getId();
        this.user = new UserSimpleResDto(entity.getUser());
        this.subject = entity.getSubject();
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedDate();

        entity.getPostFileList().forEach(postFile -> this.postFileList.add(postFile.getId()));

    }

}
