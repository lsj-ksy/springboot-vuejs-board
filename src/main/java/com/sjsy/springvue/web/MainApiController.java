package com.sjsy.springvue.web;

import com.sjsy.springvue.service.main.MainService;
import com.sjsy.springvue.web.dto.MainResDto;
import com.sjsy.springvue.web.dto.MainTitleResDto;
import com.sjsy.springvue.web.dto.PostsListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
//대문 Api controller
public class MainApiController {

    private final MainService mainService;

    //타이틀 사진 api data
    @GetMapping("/api/v1/title")
    public MainTitleResDto mainTitle() {
        return mainService.mainTitle();
    }

    //대문 api data
    @GetMapping("/api/v1/main")
    public MainResDto main() {
        return mainService.mainContent();
    }

    //대문 전체글보기 api data
    @GetMapping("/api/v1/mainlist")
    public List<PostsListResDto> mainList() {
        return mainService.findAllByEnabled();
    }



}
