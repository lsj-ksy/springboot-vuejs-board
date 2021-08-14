package com.sjsy.springvue.web.dto.response;

import com.sjsy.springvue.domain.post.PostFile;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostFileDownloadResDto {

    private Long id;
    private String fileName;
    private String fileOriginName;
    private String filePath;

    @Builder
    public PostFileDownloadResDto(PostFile entity) {
        this.id = entity.getId();
        this.fileName = getFileName();
        this.fileOriginName = entity.getFileOriginName();
        this.filePath = entity.getFilePath();
    }

}
