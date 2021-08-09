package com.sjsy.springvue.web;

import com.sjsy.springvue.service.main.MainService;
import com.sjsy.springvue.web.dto.MainResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
//대문 Api controller
public class MainApiController {

    private final MainService mainService;

    //대문 api data
    @GetMapping("/api/v1/main")
    public MainResDto main() {

        mainService.mainContent();

        //return null;
        return mainService.mainContent();
    }

    //대문 전체글보기 api data
    @GetMapping("/api/v1/mainlist")
    public MainResDto useruseruser(@PathVariable Long id) {
        return null;
    }

}
