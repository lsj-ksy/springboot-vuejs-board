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
public class UserApiController {

    private final PostService postService;

    //
    @GetMapping("/api/v1/postcount/{id}")
    public int postcount(@PathVariable Long id) {
        return postService.count(id);
    }

    @GetMapping("/api/v1/postlist/{id}")
    public List<PostsListResDto> postlist(@PathVariable Long id) {
        return postService.find(id);
    }

}
