package com.sjsy.springvue.domain.abuse;

import com.sjsy.springvue.domain.BaseTimeEntity;
import com.sjsy.springvue.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "stop_list")
@Entity
public class StopUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime startDate;

    @Enumerated(EnumType.STRING)
    private StopPeriod stopPeriod;

    private LocalDateTime endDate;

    private String comment;

    @Column
    @ColumnDefault("1")
    private int enabled;

    @Builder
    public StopUser(User user, StopPeriod stopPeriod, String comment) {
        this.user = user;
        this.stopPeriod = stopPeriod;
        this.endDate = extendExprirationDate();
        this.comment = comment;
    }

    private LocalDateTime extendExprirationDate() {
        return LocalDateTime.now().plusSeconds(stopPeriod.getSec());
    }

}