package com.sjsy.springvue.domain.abuse;

import com.sjsy.springvue.domain.BaseTimeEntity;
import com.sjsy.springvue.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ban_list")
@Entity
public class BanUser extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String comment;

    @ColumnDefault("1")
    private int enabled;

    @Builder
    public BanUser(User user, String comment) {
        this.user = user;
        this.comment = comment;
    }

    //enabled default 1
    @PrePersist
    public void defaultEnabled() { //글작성시 enabled default 값은 1
        this.enabled = this.enabled == 0 ? 1 : this.enabled;
    }

}
