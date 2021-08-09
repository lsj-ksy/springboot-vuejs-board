package com.sjsy.springvue.web.dto;

import com.sjsy.springvue.domain.board.Board;
import com.sjsy.springvue.domain.board.Post;
import com.sjsy.springvue.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class PostSaveReqDto {

    private String userId;
    private String boardId;
    private String subject;
    private String content;

    @Builder
    public PostSaveReqDto(String userId, String boardId, String subject, String content) {
        this.userId = userId;
        this.boardId = boardId;
        this.subject = subject;
        this.content = content;
    }

    public Post toEntity(User user, Board board) {
        return Post.builder()
                .user(user)
                .board(board)
                .subject(subject)
                .content(content)
                .build();
    }
}
