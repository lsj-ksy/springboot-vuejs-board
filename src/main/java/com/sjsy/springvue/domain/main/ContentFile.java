package com.sjsy.springvue.domain.main;

import com.sjsy.springvue.domain.board.Post;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "main_content_file")
@Entity
@ToString(exclude={"content"})
public class ContentFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contentId")
    private Content content;

    private String fileOriginName;

    private String fileName;

    private String filePath;

    @Builder
    public ContentFile(Content content, String fileOriginName, String fileName, String filePath) {
        this.content = content;
        this.fileOriginName = fileOriginName;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    //Content 설정하기
    public void setContentInfo(Content content) {
        this.content = content;

        //파라미터로 들어온 post의 postFileList에 해당(this) postFile 이 들어있지 않다면
        if(!content.getContentFileList().contains(this))
            content.getContentFileList().add(this);
    }



}
