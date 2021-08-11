package com.sjsy.springvue.web.dto.response;

import com.sjsy.springvue.domain.main.Title;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


//대문 ResponseDto
@Getter
public class MainTitleResDto {

    private String titleFileName; //title filename

    @Builder
    public MainTitleResDto(Title entity) {
        this.titleFileName = entity.getFileName();
    }
}
