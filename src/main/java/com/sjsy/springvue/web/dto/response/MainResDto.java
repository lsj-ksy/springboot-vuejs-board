package com.sjsy.springvue.web.dto.response;

import com.sjsy.springvue.domain.main.Content;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


//대문 ResponseDto
@Getter
public class MainResDto {

    private String content; //main 내용
    private List<MainFileResDto> mainFileResDtoList; //main files

    @Builder
    public MainResDto(Content content, List<MainFileResDto> mainFileResDtoList) {
        this.content = content.getContent();
        this.mainFileResDtoList = mainFileResDtoList;
    }
}
