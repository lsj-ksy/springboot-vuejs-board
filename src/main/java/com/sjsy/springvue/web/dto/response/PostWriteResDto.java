package com.sjsy.springvue.web.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostWriteResDto { //글쓰기 요청 dto

    private String recentCategory; //글쓰기 누른 category
    private SidebarResDto categories; //카테고리, 게시판종류

    @Builder
    public PostWriteResDto(String recentCategory, SidebarResDto categories) {
        this.recentCategory = recentCategory;
        this.categories = categories;
    }

}
