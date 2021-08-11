package com.sjsy.springvue.web;

import com.sjsy.springvue.service.reply.ReplyService;
import com.sjsy.springvue.web.dto.ReplyListResDto;
import com.sjsy.springvue.web.dto.ReplySaveReqDto;
import com.sjsy.springvue.web.dto.ReplyUpdateReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {

    private final ReplyService replyService;

    //댓글 등록
    @PostMapping("/api/v1/replysave")
    public Long replySave(ReplySaveReqDto replySaveReqDto) {
        return replyService.replySave(replySaveReqDto);
    }

    //게시물 번호 > 댓글 리스트 가져오기
    @GetMapping("/api/v1/post/{id}/replylist")
    public ResponseEntity<?> getReplyList(@PathVariable Long id) {
        List<ReplyListResDto> replyListResDtoList = replyService.getReplyListByPostId(id);
        if(!replyListResDtoList.isEmpty()) {
            return new ResponseEntity<List<ReplyListResDto>>(replyListResDtoList, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("Not Found replyList", HttpStatus.BAD_REQUEST);
        }
    }

    //댓글 수정
    @PutMapping("/api/v1/reply/update/{id}")
    public Long replyUpdate(@PathVariable Long id, ReplyUpdateReqDto replyUpdateReqDto) {
        return replyService.update(id, replyUpdateReqDto);
    }

}
