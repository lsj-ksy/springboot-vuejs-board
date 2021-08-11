package com.sjsy.springvue.web;

import com.sjsy.springvue.service.post.PostService;

import com.sjsy.springvue.web.dto.response.PostsListResDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.sjsy.springvue.web.dto.request.PostSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostService postService;

    //사이트 내 게시글 검색 api data
    @GetMapping("/api/v1/serach/{search}")
    public List<PostsListResDto> findPostsBySearch(@PathVariable String search) {
        return postService.findPostsBySearch(search);
    }

    //게시물 등록
    @PostMapping("/api/v1/post/save")
    public Long postSave(@RequestParam(value = "files", required = false) Optional<List<MultipartFile>> fileList,
                         //required = false 필수 파라미터 아니라고 체크
                         //Optional로 받는 이유는 필수 파라미터 아니므로 나중에 .isPresent()로 Null 여부 체크하여 fileList 있는 경우에만 파일 저장하려고
                         PostSaveReqDto postSaveReqDto) throws Exception {
        return postService.postSave(fileList, postSaveReqDto);
    }

}
