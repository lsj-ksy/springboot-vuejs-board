package com.sjsy.springvue.domain.user;

import com.sjsy.springvue.domain.BaseTimeEntity;
import com.sjsy.springvue.domain.abuse.BanUser;
import com.sjsy.springvue.domain.abuse.StopUser;
import com.sjsy.springvue.domain.post.Post;
import com.sjsy.springvue.domain.post.PostScrap;
import com.sjsy.springvue.domain.board.Reply;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //무분별한 객체 생성에 대한 체크 한 번 더
@Table( name = "user_info",
        uniqueConstraints = { @UniqueConstraint( columnNames = {"socialKey", "nickname"} ) } )
@Entity
@ToString
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private SocialType socialType;

    @NotNull
    private String socialKey;

    @NotNull
    private String username;

    @NotNull
    private String nickname;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;

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
    public User(SocialType socialType, String socialKey, String username, String nickname, RoleType roleType, String cellNumber, String email, Gender gender, String profileImg) {
        this.socialType = socialType;
        this.socialKey = socialKey;
        this.username = username;
        this.nickname = nickname;
        this.roleType = roleType;
        this.cellNumber = cellNumber;
        this.email = email;
        this.gender = gender;
        this.profileImg = profileImg;
    }

    public User update(String name, String picture, String email) {
        this.username = name;
        this.profileImg = picture;
        this.email = email;

        return this;
    }

    //enabled default 1
    @PrePersist
    public void defaultEnabled() { //글작성시 enabled default 값은 1
        this.enabled = this.enabled == 0 ? 1 : this.enabled;
    }


    //닉네임변동에 사용되는 setter
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


}
