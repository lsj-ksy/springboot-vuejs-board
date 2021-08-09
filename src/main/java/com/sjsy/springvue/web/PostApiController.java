package com.sjsy.springvue.web;

import com.sjsy.springvue.service.post.PostService;
import com.sjsy.springvue.web.dto.PostsListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    //사이트 내 게시글 검색 api data
    @GetMapping("/api/v1/serach/{search}")
    public List<PostsListResDto> findPostsBySearch(@PathVariable String search) {
        return postService.findPostsBySearch(search);
    }
}
