package com.sjsy.springvue.web.dto;

import com.sjsy.springvue.domain.main.ContentFile;
import lombok.Getter;
import lombok.ToString;

//대문 전체글보기 ResponseDto
@Getter
@ToString
public class MainFileResDto {

    private String fileName;

    public MainFileResDto(ContentFile entity) {
        this.fileName = entity.getFileName();
    }

}
