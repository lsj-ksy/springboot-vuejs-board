package com.sjsy.springvue.domain.user;

import com.sjsy.springvue.domain.BaseTimeEntity;
import com.sjsy.springvue.domain.abuse.BanUser;
import com.sjsy.springvue.domain.abuse.StopUser;
import com.sjsy.springvue.domain.post.Post;
import com.sjsy.springvue.domain.post.PostScrap;
import com.sjsy.springvue.domain.board.Reply;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED) //무분별한 객체 생성에 대한 체크 한 번 더
@Table(name = "user_info")
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String socialType;

    @NotNull
    @Column(unique = true)
    private String socialKey;

    @NotNull
    private String username;

    @NotNull
    private String nickname;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    private String cellNumber;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String profileImg;

    @Column
    @ColumnDefault("1")
    private int enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BanUser> banUserList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<StopUser> stopUserList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PostScrap> postScrapList = new ArrayList<>();

    @Builder
    public User(String socialType, String socialKey, String username, String nickname, Role role, String cellNumber, String email, Gender gender, String profileImg) {
        this.socialType = socialType;
        this.socialKey = socialKey;
        this.username = username;
        this.nickname = nickname;
        this.role = role;
        this.cellNumber = cellNumber;
        this.email = email;
        this.gender = gender;
        this.profileImg = profileImg;
    }
}
