package com.sjsy.springvue.domain.main;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "main_content")
@Entity
@ToString(exclude={"contentFilelist"})
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column
    @ColumnDefault("1")
    private int enabled;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    private List<ContentFile> contentFileList = new ArrayList<>();

    @Builder
    public Content(String content) {
        this.content = content;
    }

}
