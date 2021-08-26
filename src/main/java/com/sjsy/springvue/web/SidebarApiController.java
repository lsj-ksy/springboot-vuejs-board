package com.sjsy.springvue.web;

import com.sjsy.springvue.service.sidebar.SidebarService;
import com.sjsy.springvue.web.dto.response.SidebarResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SidebarApiController {

    private final SidebarService sidebarService;

    //사이트 내 게시글 검색 api data
    @GetMapping("/api/v1/sidebar/board")
    public List<SidebarResDto> findSidebarBoards() {
        return sidebarService.findSidebarBoards();
    }

}
