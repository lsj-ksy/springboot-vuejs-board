package com.sjsy.springvue.domain.board;

import com.sjsy.springvue.domain.post.Post;
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
@Table(name = "board_info")
@Entity
//게시판
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    private String boardName;

    @NotNull
    @ColumnDefault("1")
    private int enabled;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @Builder
    public Board(Category category, String boardName) {
        this.category = category;
        this.boardName = boardName;
    }

    //enabled default 1
    @PrePersist
    public void defaultEnabled() { //글작성시 enabled default 값은 1
        this.enabled = this.enabled == 0 ? 1 : this.enabled;
    }


}
