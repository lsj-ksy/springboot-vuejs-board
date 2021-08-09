package com.sjsy.springvue.web;

import com.sjsy.springvue.service.main.MainService;
import com.sjsy.springvue.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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

    //게시물 등록
    @PostMapping("/api/v1/mainsave")
    public Long mainSave(@RequestParam(value = "files", required = false) Optional<List<MultipartFile>> fileList,
                         //required = false 필수 파라미터 아니라고 체크
                         //Optional로 받는 이유는 필수 파라미터 아니므로 나중에 .isPresent()로 Null 여부 체크하여 fileList 있는 경우에만 파일 저장하려고
                         MainSaveReqDto mainSaveReqDto) throws Exception {
        return mainService.mainSave(fileList, mainSaveReqDto);
    }

    //대문 전체글보기 api data
    @GetMapping("/api/v1/mainlist")
    public List<PostsListResDto> mainList() {
        return mainService.findAllByEnabled();
    }



}
