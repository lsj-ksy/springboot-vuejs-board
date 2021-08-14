package com.sjsy.springvue.domain.post;

import com.sjsy.springvue.domain.BaseTimeEntity;
import com.sjsy.springvue.domain.board.Board;
import com.sjsy.springvue.domain.board.Reply;
import com.sjsy.springvue.domain.user.User;
import com.sjsy.springvue.web.dto.response.PostDetailResDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board_post")
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, length = 3000)
    private String content;

    @Column
    @ColumnDefault("0")
    private int readcount;

    @Column
    @ColumnDefault("0")
    private int likecount;

    @Column
    @ColumnDefault("0")
    private int ref;

    @Column
    @ColumnDefault("0")
    private int depth;

    @ColumnDefault("1")
    private int enabled;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostFile> postFileList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostScrap> postScrapList = new ArrayList<>();

    @Builder
    public Post(Board board, User user, String subject, String content, int readcount, int likecount, int
                ref, int depth) {
        this.board = board;
        this.user = user;
        this.subject = subject;
        this.content = content;
        this.readcount = readcount;
        this.likecount = likecount;
        this.ref = ref;
        this.depth = depth;
    }

    //PostFile 추가하기
    public void addPostFile(PostFile postFile) {
        this.postFileList.add(postFile);

        //파라미터로 들어온 postFile의 Post 값이 해당(this) Post가 아니라면
        if(postFile.getPost() != this)
            postFile.setPostInfo(this);
    }

    //Reply 추가하기
    public void addReply(Reply reply) {
        this.replyList.add(reply);

        //파라미터로 들어온 reply의 Post 값이 해당(this) Post가 아니라면
        if(reply.getPost() != this)
            reply.setPostInfo(this);
    }

    //enabled default 1
    @PrePersist
    public void defaultEnabled() { //글작성시 enabled default 값은 1
        this.enabled = this.enabled == 0 ? 1 : this.enabled;
    }

}
