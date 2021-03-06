package com.sjsy.springvue.web;

import com.sjsy.springvue.service.post.PostService;

import com.sjsy.springvue.web.dto.response.BoardResDto;
import com.sjsy.springvue.web.dto.response.PostDetailResDto;
import com.sjsy.springvue.web.dto.request.PostUpdateReqDto;
import com.sjsy.springvue.web.dto.response.PostWriteResDto;
import com.sjsy.springvue.web.dto.response.PostsListResDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sjsy.springvue.web.dto.request.PostSaveReqDto;
import lombok.RequiredArgsConstructor;

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

    //게시물 상세보기
    @GetMapping("/api/v1/post/{id}")
    public ResponseEntity<PostDetailResDto> getPost(@PathVariable Long id) {
        return new ResponseEntity<PostDetailResDto>(postService.getPost(id), HttpStatus.OK);
    }

    //글 등록 request
    @PostMapping("/api/v1/post/{type}")
    public Long postSave(@PathVariable String type, //글쓰기(write), 답글쓰기(reply) type
                         @RequestParam(value = "files", required = false) Optional<List<MultipartFile>> fileList,
                         //required = false 필수 파라미터 아니라고 체크
                         //Optional로 받는 이유는 필수 파라미터 아니므로 나중에 .isPresent()로 Null 여부 체크하여 fileList 있는 경우에만 파일 저장하려고
                         PostSaveReqDto postSaveReqDto) throws Exception {
        return postService.postSave(type, fileList, postSaveReqDto);
    }

    //항목별 게시글 리스트 api data response
    @GetMapping("/api/v1/post/list")
    public BoardResDto postList(@RequestParam("board_id") Long boardId,
                                @RequestParam("page") int page,
                                @RequestParam("per_page") int perPage) {
        return postService.findAllByBoardId(boardId, page, perPage);
    }

    //항목별 게시글 수 api data response
    @GetMapping("/api/v1/post/totalCount")
    public int mainListTotalCount(@RequestParam("board_id") Long boardId) {
        return postService.findTotalCount(boardId);
    }

    //게시물 수정
    @PatchMapping("/api/v1/post/update/{id}")
    public Long update(@PathVariable Long id, //글번호
                       @RequestParam(value = "files", required = false) Optional<List<MultipartFile>> fileList,
                       PostUpdateReqDto postUpdateReqDto) throws Exception {

        return postService.postUpdate(id,fileList,postUpdateReqDto);
    }

    //게시물 삭제
    @PatchMapping("/api/v1/post/delete/{post_id}/{user_id}")
    public ResponseEntity<String> delete(@PathVariable("post_id") Long postId,
                                        @PathVariable("user_id") Long userId,
                                         @RequestParam(value = "current_path") String currentPath) {

        Long userCurrentView = Long.valueOf(currentPath.substring(currentPath.lastIndexOf("/") + 1));
        if (!postId.equals(userCurrentView)) {
            return new ResponseEntity<String>("Exception", HttpStatus.BAD_REQUEST);
        }

        int postEnabled = postService.postDelete(postId, userId);
        if (postEnabled != 1) {
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Fail", HttpStatus.BAD_REQUEST);
        }
    }

}
