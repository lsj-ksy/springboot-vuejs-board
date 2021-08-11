package com.sjsy.springvue.domain.post;

import com.sjsy.springvue.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "post_scrap")
@Entity
public class PostScrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @Column
    @ColumnDefault("1")
    private int enabled;

    @Builder
    public PostScrap(User user, Post post) {
        this.user = user;
        this.post = post;
    }

}
