package com.sjsy.springvue.web.dto;

import com.sjsy.springvue.domain.main.Content;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MainSaveReqDto {

    private Long id;
    private String content;

    @Builder
    public MainSaveReqDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Content toEntity(MainSaveReqDto mainSaveReqDto) {
        return Content.builder()
                .id(mainSaveReqDto.getId())
                .content(mainSaveReqDto.getContent())
                .build();
    }
}
