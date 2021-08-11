package com.sjsy.springvue.domain.main;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "main_content")
@Entity
@ToString(exclude={"contentFilelist"})
@DynamicInsert
@DynamicUpdate
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ColumnDefault("1")
    private int enabled;

    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
    private List<ContentFile> contentFileList = new ArrayList<>();

    @Builder
    public Content(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    //PostFile 추가하기
    public void addContentFile(ContentFile contentFile) {
        this.contentFileList.add(contentFile);

        //파라미터로 들어온 postFile의 Post 값이 해당(this) Post가 아니라면
        if(contentFile.getContent() != this)
            contentFile.setContentInfo(this);
    }

    //enabled default 1
    @PrePersist
    public void defaultEnabled() { //글작성시 enabled default 값은 1
        this.enabled = this.enabled == 0 ? 1 : this.enabled;
    }

}
