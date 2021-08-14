package com.sjsy.springvue.web.dto.request;

import com.sjsy.springvue.domain.main.Title;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSidebarFileReqDto {

    private String fileOriginName;
    private String fileName;
    private String filePath;

    @Builder
    public UserSidebarFileReqDto(String fileOriginName, String fileName, String filePath) {
        this.fileOriginName = fileOriginName;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public Title toEntity() {
        return Title.builder()
                .fileOriginName(fileOriginName)
                .fileName(fileName)
                .filePath(filePath)
                .build();
    }

}
