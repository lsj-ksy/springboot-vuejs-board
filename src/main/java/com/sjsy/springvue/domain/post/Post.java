package com.sjsy.springvue.domain.post;

import com.sjsy.springvue.domain.BaseTimeEntity;
import com.sjsy.springvue.domain.board.Board;
import com.sjsy.springvue.domain.board.Reply;
import com.sjsy.springvue.domain.user.User;
import com.sjsy.springvue.web.dto.response.PostDetailResDto;
import lombok.*;
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

    //PostFile ????????????
    public void addPostFile(PostFile postFile) {
        this.postFileList.add(postFile);

        //??????????????? ????????? postFile??? Post ?????? ??????(this) Post??? ????????????
        if(postFile.getPost() != this)
            postFile.setPostInfo(this);
    }

    //Reply ????????????
    public void addReply(Reply reply) {
        this.replyList.add(reply);

        //??????????????? ????????? reply??? Post ?????? ??????(this) Post??? ????????????
        if(reply.getPost() != this)
            reply.setPostInfo(this);
    }

    public void clearFiles() {
        postFileList.clear();
    }

    //???????????? setter
    public void setSubject(String subject) {
        this.subject = subject;
    }
    //???????????? setter
    public void setContent(String content) {
        this.content = content;
    }
    //???????????? ?????? setter
    public void setBoard(Board board) {
        this.board = board;
    }

    //?????? setter
    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public List<Reply> getReplyListByEnalbed() {

        List<Reply> replyListByEnalbed = new ArrayList<>();

        this.replyList.forEach(
                reply -> {
                    if (reply.getEnabled() != 0) {
                        replyListByEnalbed.add(reply);
                    }
                }
        );

        return replyListByEnalbed;
    }

    //enabled default 1
    @PrePersist
    public void defaultEnabled() { //???????????? enabled default ?????? 1
        this.enabled = this.enabled == 0 ? 1 : this.enabled;
    }

}
