package com.sjsy.springvue.web.dto;

import com.sjsy.springvue.domain.board.PostFile;
import com.sjsy.springvue.domain.main.Content;
import com.sjsy.springvue.domain.main.ContentFile;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MainFileSaveDto {

    private String fileOriginName;
    private String fileName;
    private String filePath;

    @Builder
    public MainFileSaveDto(String fileOriginName, String fileName, String filePath) {
        this.fileOriginName = fileOriginName;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public ContentFile toEntity() {
        return ContentFile.builder()
                .fileOriginName(fileOriginName)
                .fileName(fileName)
                .filePath(filePath)
                .build();
    }

}
