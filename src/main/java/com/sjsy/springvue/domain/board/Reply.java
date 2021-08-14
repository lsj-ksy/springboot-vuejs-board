package com.sjsy.springvue.domain.board;

import com.sjsy.springvue.domain.BaseTimeEntity;
import com.sjsy.springvue.domain.post.Post;
import com.sjsy.springvue.domain.user.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board_reply")
@Entity
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false)
    private String content;

    @ColumnDefault("1")
    private int enabled;

    @Builder
    public Reply(Post post, User user, String content) {
        this.post = post;
        this.user = user;
        this.content = content;
    }

    //Post 설정하기
    public void setPostInfo(Post post) {
        this.post = post;

        //파라미터로 들어온 post의 replyList에 해당(this) reply 이 들어있지 않다면
        if(!post.getReplyList().contains(this))
            post.getReplyList().add(this);
    }

    //Reply 수정
    public void update(String content) {
        this.content = content;
    }

    //enabled default 1
    @PrePersist
    public void defaultEnabled() { //글작성시 enabled default 값은 1
        this.enabled = this.enabled == 0 ? 1 : this.enabled;
    }

    //delete reply ( enabled change )
    public void setEnabled() {
        this.enabled = this.enabled != 0 ? 0 : 1;
    }

}
