package com.sjsy.springvue.web.dto.request;

import com.sjsy.springvue.domain.board.Board;
import com.sjsy.springvue.domain.post.Post;
import com.sjsy.springvue.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostSaveReqDto {

    private String userId;
    private String boardId;
    private String subject;
    private String content;
    private int ref;
    private int depth;

    @Builder
    public PostSaveReqDto(String userId, String boardId, String subject, String content, int ref, int depth) {
        this.userId = userId;
        this.boardId = boardId;
        this.subject = subject;
        this.content = content;
        this.ref = ref;
        this.depth = depth;
    }

    //원본 글쓰기의 toEntity, ref값 파라미터로 받음
    public Post toEntity(User user, Board board, int ref) {
        return Post.builder()
                .user(user)
                .board(board)
                .subject(subject)
                .content(content)
                .ref(ref)
                .build();
    }

    //답변 글쓰기의 toEntity, ref값은 원본글과 같고 depth는 1 증가함
    public Post toEntity(User user, Board board) {
        return Post.builder()
                .user(user)
                .board(board)
                .subject(subject)
                .content(content)
                .ref(ref)
                .depth(depth + 1)
                .build();
    }


}
