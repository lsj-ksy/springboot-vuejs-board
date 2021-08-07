package com.sjsy.springvue.domain.main;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "main_content_file")
@Entity
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

}
