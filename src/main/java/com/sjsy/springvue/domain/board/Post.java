package com.sjsy.springvue.domain.board;

import com.sjsy.springvue.domain.BaseTimeEntity;
import com.sjsy.springvue.domain.user.User;
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

    @Column
    @ColumnDefault("1")
    private int enabled;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostFile> postFileList = new ArrayList<>();

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

}