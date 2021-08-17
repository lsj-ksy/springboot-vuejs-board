package com.sjsy.springvue.web.dto.response;

import com.sjsy.springvue.domain.board.Reply;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyListResDto {

    private Long id;
    private UserSimpleResDto user;
    private String content;
    private LocalDateTime modifiedDate;

    @Builder
    public ReplyListResDto(Reply entity) {
        this.id = entity.getId();
        this.user = new UserSimpleResDto(entity.getUser());
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedDate();
    }

}
