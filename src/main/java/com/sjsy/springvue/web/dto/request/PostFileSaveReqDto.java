package com.sjsy.springvue.web.dto.request;

import com.sjsy.springvue.domain.post.PostFile;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostFileSaveReqDto {

    private String fileOriginName;
    private String fileName;
    private String filePath;

    @Builder
    public PostFileSaveReqDto(String fileOriginName, String fileName, String filePath) {
        this.fileOriginName = fileOriginName;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public PostFile toEntity() {
        return PostFile.builder()
                .fileOriginName(fileOriginName)
                .fileName(fileName)
                .filePath(filePath)
                .build();
    }

}
